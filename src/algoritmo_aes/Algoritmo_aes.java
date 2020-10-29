/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmo_aes;

import java.util.ArrayList;

/**
 *
 * @author Nilton, Gustavo e Kienen
 */
public class Algoritmo_aes {

    private static final int QTD_ROUND_KEY = 10;
    private static final Arquivo arquivo = new Arquivo();
    private static final int DEBUG = 1;
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
        //Le o arquivo, pega os primeiros 16bytes, transformar os 16bytes
        //em hexadecimal como exemplo abaixo e enviar para o processo de cifragem
        //Fazer este processo até o final do arquivo. No último bloco lembrar de ver o PKCS#5,
        //que é preencher o bloco até ficar com 16bytes. Se o último bloco fechar 16 certinho, vai
        //precisar criar um novo bloco todo preenchido pelo PKCS#5.
        //for(){
        String[][] textoSimples = {{"44", "45", "53", "45"},
                                   {"4e", "56", "4f", "4c"},
                                   {"56", "49", "4d", "45"},
                                   {"4e", "54", "4f", "21"}};
        CriptografiaArquivo criptArquivo = new CriptografiaArquivo();
        String[][] sCriptografado = criptArquivo.IniciaCriptografia(textoSimples, roundKeys);
        //}
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
