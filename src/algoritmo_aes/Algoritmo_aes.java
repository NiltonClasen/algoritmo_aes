/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmo_aes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Arrays;

import java.util.List;

/**
 *
 * @author Nilton, Gustavo e Kienen
 */
public class Algoritmo_aes {

    private static final int QTD_ROUND_KEY = 10;
    private static final Arquivo arquivo = new Arquivo();
    private static final int DEBUG = 0;
    private static final ArrayList<RoundKey> roundKeys = new ArrayList<>();
    private static RoundKey chaveGerador;
    private static final KeyUtils keyUtils = new KeyUtils();

    public static void main(String[] args) {
        chaveGerador = new RoundKey(arquivo.requisitarChave());
        roundKeys.add(chaveGerador);
        logger(chaveGerador, null, "Chave de entrada");
        for (int idxRoundKey = 0; idxRoundKey < QTD_ROUND_KEY; ++idxRoundKey) {
            String[] a = keyUtils.copiarChaveAnterior(roundKeys.get(idxRoundKey));
            logger(null, a, "Copiar Chave Anterior");
            String[] a2 = keyUtils.rotWord(a);
            logger(null, a2, "rotword");
            String[] a3 = keyUtils.subsWord(a2);
            logger(null, a3, "subsWord");
            String[] a4 = keyUtils.roundConstant(a3, idxRoundKey);
            logger(null, a4, "roundConstant");
            String[] a5 = keyUtils.fazOperacaoXOR(a3, a4);
            logger(null, a5, "fazOperacaoXOR");
            String[] a6 = keyUtils.primeiraPalavra(roundKeys.get(idxRoundKey).getWord(0), a5);
            logger(null, a6, "primeiraPalavra");
            RoundKey RoundKeyInicial = new RoundKey();
            RoundKeyInicial.setWord(a6, 0);
            RoundKeyInicial.setNovaRoundKey(roundKeys.get(idxRoundKey));
            roundKeys.add(RoundKeyInicial);
            logger(RoundKeyInicial, null, "RoundKey adicionada");
        }

        File file = new Arquivo().selecionarArquivo();
        int fileBytesSize = 0;

        try {
            byte[] fileByteContent = Files.readAllBytes(file.toPath());
            fileBytesSize = fileByteContent.length;

            byte[][] blocos = Utils.divideArray(fileByteContent, 16);

            StringBuilder resultadoCriptografia = new StringBuilder();
            String[][] sCriptografado = new String[4][4];
            for (int linha = 0; linha < blocos.length; linha++) {
                //aqui precisa criar uma nova matriz com a linha
                //passa a linha inteira de bytes
                String[][] blocoEmHexa = Utils.criaMatrizDoBloco(blocos[linha]);

                CriptografiaArquivo criptArquivo = new CriptografiaArquivo();
                sCriptografado = criptArquivo.IniciaCriptografia(blocoEmHexa, roundKeys);

                //pega o resultado e concatena numa string
                for (int i = 0; i < sCriptografado.length; i++) {
                    for (int j = 0; j < sCriptografado[i].length; j++) {
                        resultadoCriptografia.append(sCriptografado[i][j]);
                    }
                }
            }
            //quando termina pede a URI para colocar o arquivo
            String nomeArquivo = arquivo.SolicitarNomeArquivo();
            arquivo.EscreverArquivo(nomeArquivo, sCriptografado);

        } catch (IOException ex) {
            Logger.getLogger(Algoritmo_aes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void logger(RoundKey roundkey, String[] word, String etapa) {
        if (DEBUG == 1) {
            System.out.println("\\\\Etapa " + etapa + "////");
            if (roundkey != null) {
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        System.out.print("[" + roundkey.getRoundKey()[i][j] + "]");
                    }
                    System.out.print("\n");
                }
            } else {
                for (int i = 0; i < word.length; i++) {
                    System.out.println("[" + word[i] + "]");
                }
            }
        }
    }
}
