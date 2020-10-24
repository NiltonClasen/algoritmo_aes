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

    Constants constantes = new Constants();

    public String[] copiarChaveAnterior(RoundKey chaveAnterior) {
        String[] copia = new String[4];
        for (int i = 0; i < 4; i++) {
            copia[i] = chaveAnterior.getWord(3)[i];
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
            if (palavraA[i].length() == 1) {
                numero2 = numero1;
                numero1 = 0;
            } else {
                numero2 = Integer.parseInt(String.valueOf(palavraA[i].charAt(1)), 16);
            }
            palavraA[i] = Integer.toHexString(constantes.getValorSBox(numero1, numero2));
        }
        return palavraA;
    }

    public String[] roundConstant(String[] palavraA, int roundAtual) {
        String[] palavraB = new String[4];
        palavraB[0] = Integer.toHexString(constantes.getValorRoundConstant(roundAtual));
        palavraB[1] = "0";
        palavraB[2] = "0";
        palavraB[3] = "0";
        return palavraB;
    }

    public String[] roundConstantXOR(String[] palavraEtapa3, String[] palavraEtapa4) {
        return fazOperacaoXOR(palavraEtapa3, palavraEtapa4);
    }

    public String[] primeiraPalavra(String[] palavraRoundAnterior, String[] palavraEtapa5) {
        return fazOperacaoXOR(palavraRoundAnterior, palavraEtapa5);
    }

    public String[] fazOperacaoXOR(String[] aWord1, String[] aWord2) {
        String[] aWordXOR = new String[4];

        for (int idx = 0; idx < 4; ++idx) {
            int iHex1 = Integer.parseInt(aWord1[idx], 16);
            int iHex2 = Integer.parseInt(aWord2[idx], 16);
            aWordXOR[idx] = Integer.toHexString(iHex1 ^ iHex2);
        }

        return aWordXOR;
    }
}
