/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmo_aes;

/**
 *
 * @author Nilton
 */
public class KeyUtils {

    SBox sbox = new SBox();

    public String[] copiarChaveAnterior(RoundKey chaveAnterior) {
        String[] copia = new String[4];
        for (int i = 0; i < 4; i++) {
            //copia[i] = chaveAnterior.getWord(3)[i];
        }
        return copia;
    }

    public String[] rotWord(String[] palavraA) {
        String[] palavraB = new String[4];
        int iAux = 0;
        for (int i = 0; i < palavraA.length; i++) {
            iAux = i;
            if ((iAux + 1) > 3) {
                palavraB[i] = palavraA[0];
            } else {
                palavraB[i] = palavraA[iAux + 1];
            }
        }
        return palavraB;
    }

    public String[] subsWord(String[] palavraA) {
        int numero1, numero2;
        for (int i = 0; i < palavraA.length; i++) {
            numero1 = Integer.parseInt(String.valueOf(palavraA[i].charAt(0)), 16);
            numero2 = Integer.parseInt(String.valueOf(palavraA[i].charAt(1)), 16);
            palavraA[i] = Integer.toHexString(sbox.getValor(numero1, numero2));
        }
        return palavraA;
    }
}
