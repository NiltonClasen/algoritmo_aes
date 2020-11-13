/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmo_aes;

import java.math.BigInteger;
import java.util.Arrays;

/**
 *
 * @author Mateus Kienen
 */
public class Utils {

    public static String getHex(byte byt) {
        String ahaha = String.format("%02x", byt);
        return ahaha;
    }

    public static String[][] criaMatrizDoBloco(byte[] blocoBytes) {
        String[][] matriz = new String[4][4];

        int auxCounter = 0;
        while (auxCounter < 16) {
            for (int linha = 0; linha < 4; linha++) {
                for (int coluna = 0; coluna < 4; coluna++) {
                    matriz[linha][coluna] = getHex(blocoBytes[auxCounter]);
                    auxCounter++;
                }

            }
        }
        return matriz;
    }

    public static byte[][] divideArray(byte[] source, int chunksize) {
        //verifica se precisa de um novo bloco, divide total de bytes por 16, resultando em quantidade de blocos
        boolean novoBloco = source.length % (double) chunksize == 0;
        int qtdLinhas = (int) Math.ceil(source.length / (double) chunksize);
        if (novoBloco) {
            qtdLinhas++;
        }
        byte[][] ret = new byte[qtdLinhas][chunksize];
        int start = 0;

        for (int i = 0; i < ret.length; i++) {
            Integer countZero = 0;
            ret[i] = Arrays.copyOfRange(source, start, start + chunksize);
            start += chunksize;
            //teoricamente aqui é o isso identifica o último bloco
            if (ret.length == i + 1) {
                //percorrer toda a linha e ver quantos zeros existem
                for (int idx = 0; idx < chunksize; idx++) {
                    byte byteIdx = ret[i][idx];
                    if (byteIdx == 0) {
                        countZero++;
                    }
                }
                //percorer novamente toda a linha e setar o pkcs5#
                for (int idx = 0; idx < chunksize; idx++) {
                    byte byteIdx = ret[i][idx];
                    if (byteIdx == 0) {
                        ret[i][idx] = countZero.byteValue();
                    }
                }
            }
        }
        return ret;
    }

    public static byte[] hexToBin(String hex) {
        byte[] val = new byte[hex.length() / 2];
        for (int i = 0; i < val.length; i++) {
            int index = i * 2;
            int j = Integer.parseInt(hex.substring(index, index + 2), 16);
            val[i] = (byte) j;
        }
        return val;
    }

}
