/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmo_aes;

/**
 *
 * @author Pichau
 */
class RoundKey {

    private static final int TAMANHO_MATRIZ = 4;
    private String[][] matrizChave = new String[TAMANHO_MATRIZ][TAMANHO_MATRIZ];

    public RoundKey() {
    }

    public RoundKey(String[] sTexto) {
        int iSomatorio = 0;
        for (int idxLinha = 0; idxLinha < TAMANHO_MATRIZ; ++idxLinha) {
            for (int idxColuna = 0; idxColuna < TAMANHO_MATRIZ; ++idxColuna) {
                matrizChave[idxColuna][idxLinha] = sTexto[iSomatorio++];
            }
        }
    }

    public RoundKey(String[][] chaveCompleta) {
        matrizChave = chaveCompleta;
    }

    public String[][] getRoundKey() {
        return this.matrizChave;
    }

    public void setWord(String[] aWordDesejada, int iColuna) {
        int iSomatorio = 0;
        for (int idx = 0; idx < TAMANHO_MATRIZ; idx++) {
            this.matrizChave[iSomatorio++][iColuna] = aWordDesejada[idx];
        }
    }

    public String[] getWord(String[][] roundKey, int iColuna) {
        String[] aWord = new String[TAMANHO_MATRIZ];

        int iSomatorio = 0;
        for (int idx = 0; idx < TAMANHO_MATRIZ; idx++) {
            aWord[idx] = roundKey[iSomatorio++][iColuna];
        }

        return aWord;
    }

    public void setNovaRoundKey(RoundKey roundKeyAnterior) {
        int iPegaWord = 0;
        int iColuna = 1;
        for (int idx = 0; idx < 3; ++idx) {
            String[] aWord1 = getWord(roundKeyAnterior.getRoundKey(), iPegaWord + 1);
            String[] aWord2 = getWord(this.matrizChave, iPegaWord);
            iPegaWord++;

            String[] aWordXOR = FazOperacaoXOR(aWord1, aWord2);
            PreencheMatriz(iColuna++, aWordXOR);
        }
    }

    public String[] FazOperacaoXOR(String[] aWord1, String[] aWord2) {
        String[] aWordXOR = new String[TAMANHO_MATRIZ];

        for (int idx = 0; idx < TAMANHO_MATRIZ; ++idx) {
            int iHex1 = Integer.parseInt(aWord1[idx], 16);
            int iHex2 = Integer.parseInt(aWord2[idx], 16);
            aWordXOR[idx] = Integer.toHexString(iHex1 ^ iHex2);
        }

        return aWordXOR;
    }

    private void PreencheMatriz(int iColuna, String[] aWordXOR) {
        for (int idx = 0; idx < TAMANHO_MATRIZ; ++idx) {
            this.matrizChave[idx][iColuna] = aWordXOR[idx];
        }
    }
}
