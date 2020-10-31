/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmo_aes;

import java.util.Arrays;

/**
 *
 * @author Mateus Kienen
 */
public class Utils {

    public static String getHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte aByte : bytes) {
            result.append(String.format("%02x", aByte));
        }
        return result.toString();
    }

    public static byte[][] divideArray(byte[] source, int chunksize) {
        boolean novoBloco = source.length % (double) chunksize == 0;
        int qtdLinhas = (int) Math.ceil(source.length / (double) chunksize);
        if(novoBloco){
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
                    if(byteIdx == 0){
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
}
