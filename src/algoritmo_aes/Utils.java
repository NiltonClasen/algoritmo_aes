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

        byte[][] ret = new byte[(int) Math.ceil(source.length / (double) chunksize)][chunksize];

        int start = 0;

        for (int i = 0; i < ret.length; i++) {
            ret[i] = Arrays.copyOfRange(source, start, start + chunksize);
            start += chunksize;
            //teoricamente aqui é o isso identifica o último bloco
//            if (ret.length == i + 1) {
//                for (byte[] bs : ret[i]) {
//                    Integer qtdeZeros = 0;
//                    //pegas as posições que é 0 e conta quanto faltou pra completar
//                    for (int j = 0; j < bs.length; j++) {
//                        if (bs[j] == 0) {
//                            qtdeZeros++;
//                        }
//                    }
//                    //agora faz outro for pra substituir o 0 pelo número que falta kkkk
//                    for (int j = 0; j < bs.length; j++) {
//                        bs[j] = qtdeZeros.byteValue();
//                    }    
//                }
//            }
        }

        return ret;
    }
}
