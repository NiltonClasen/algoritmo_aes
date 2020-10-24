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
    //ArrayList<byte> matrizChave = new ArrayList<byte>();

    private int[][] matrizChave = new int[TAMANHO_MATRIZ][TAMANHO_MATRIZ];

    public RoundKey(int[] iTexto) {
        int iSomatorio = 0;
        for (int idxLinha = 0; idxLinha < TAMANHO_MATRIZ; ++idxLinha) {
            for (int idxColuna = 0; idxColuna < TAMANHO_MATRIZ; ++idxColuna) {
                matrizChave[idxColuna][idxLinha] = iTexto[iSomatorio++];
            }
        }
    }
    public int[][] getRoundKey(){
        return this.matrizChave;
    }
}
