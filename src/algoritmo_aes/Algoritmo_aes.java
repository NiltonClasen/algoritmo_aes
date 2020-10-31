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
    private static final int DEBUG = 1;
    private static final ArrayList<RoundKey> roundKeys = new ArrayList<>();
    private static RoundKey chaveGerador;
    private static final KeyUtils keyUtils = new KeyUtils();

    public static void main(String[] args) {
        chaveGerador = new RoundKey(arquivo.requisitarChave());
        roundKeys.add(chaveGerador);
        logger(chaveGerador, null, "Chave de entrada");
//        for (int idxRoundKey = 0; idxRoundKey < QTD_ROUND_KEY; ++idxRoundKey) {
//            String[] a = keyUtils.copiarChaveAnterior(roundKeys.get(idxRoundKey));
//            logger(null, a, "Copiar Chave Anterior");
//            String[] a2 = keyUtils.rotWord(a);
//            logger(null, a2, "rotword");
//            String[] a3 = keyUtils.subsWord(a2);
//            logger(null, a3, "subsWord");
//            String[] a4 = keyUtils.roundConstant(a3, idxRoundKey);
//            logger(null, a4, "roundConstant");
//            String[] a5 = keyUtils.fazOperacaoXOR(a3, a4);
//            logger(null, a5, "fazOperacaoXOR");
//            String[] a6 = keyUtils.primeiraPalavra(roundKeys.get(idxRoundKey).getWord(0), a5);
//            logger(null, a6, "primeiraPalavra");
//            RoundKey RoundKeyInicial = new RoundKey();
//            RoundKeyInicial.setWord(a6, 0);
//            RoundKeyInicial.setNovaRoundKey(roundKeys.get(idxRoundKey));
//            roundKeys.add(RoundKeyInicial);
//            logger(RoundKeyInicial, null, "RoundKey adicionada");
//        }

        File file = new Arquivo().selecionarArquivo();
        int fileBytesSize = 0;
        int byteCounter = 0;
        int blocksNumber = 0;
        boolean needNewByteBlock = false;
        ArrayList<Byte> bytsToCripto = new ArrayList<>();
        int pkcs5Num = 0;

        try {
            byte[] fileByteContent = Files.readAllBytes(file.toPath());
            fileBytesSize = fileByteContent.length;

            //aqui pega o número de blocos arrendondando pra mais...
            blocksNumber = (int) Math.round(((int) fileBytesSize / 16) + 0.5d);

            //verifica se terá de criar um novo bloco de bytes no final
            if ((fileBytesSize % 16) == 0) {
                needNewByteBlock = true;
            }

            byte[][] ret = Utils.divideArray(fileByteContent, 16);
            System.out.println(ret);
            //itera sobre os bytes
//            for (int i = 0; i < fileByteContent.length; i++) {
//                //neste momento é o último bloco de bytes
//                if (blocksNumber == i - 15) {
//                    System.out.println("chegou no último bloco");
//                    //ai vê se precisa adicionar um novo bloco ou não
//                    if (needNewByteBlock) {
//                        //adiciona um novo bloco com o número de bytes que faltam
//
//                    }
//
//                    //cria um novo bloco e com 0
//                }
//
//                //se for 16 é pq fechou um bloco
//                if (byteCounter == 16) {
//                    byte[] byteBlock = new byte[16];
//
//                    //convert lista em array
//                    for (int j = 0; j < byteCounter; j++) {
//                        // byteBlock[j] = b;
//                    }
//                    Utils.getHex(byteBlock);
//
//                    //verificar se chegou no último bloco
//                    //CriptografiaArquivo criptArquivo = new CriptografiaArquivo();
//                    //criptArquivo.IniciaCriptografia(textoSimples, roundKeys);
//                    bytsToCripto = new ArrayList();
//                    byteCounter = 0;
//                }
//            }

        } catch (IOException ex) {
            Logger.getLogger(Algoritmo_aes.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Le o arquivo, pega os primeiros 16bytes, 
        //transformar os 16bytes em hexadecimal como exemplo abaixo e 
        //enviar para o processo de cifragem
        //Fazer este processo até o final do arquivo. No último bloco lembra
        //Fazerr de ver o PKCS#5,
        //que é preencher o bloco até ficar com 16bytes. Se o último bloco fechar 16 certinho, vai
        //precisar criar um novo bloco todo preenchido pelo PKCS#5.
        //for(){
        String[][] textoSimples = {{"44", "45", "53", "45"},
                                   {"4e", "56", "4f", "4c"},
                                   {"56", "49", "4d", "45"},
                                   {"4e", "54", "4f", "21"}};
        CriptografiaArquivo criptArquivo = new CriptografiaArquivo();
        String[][] sCriptografado = criptArquivo.IniciaCriptografia(textoSimples, roundKeys);
        //CriptografiaArquivo criptArquivo = new CriptografiaArquivo();
        //criptArquivo.IniciaCriptografia(textoSimples, roundKeys);
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
