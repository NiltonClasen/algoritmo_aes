/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmo_aes;

import java.util.ArrayList;

/**
 *
 * @author Pichau
 */
public class GeraChave {

    static final int TAMANHO_MATRIZ = 4;
    //ArrayList<byte> matrizChave = new ArrayList<byte>();

    int[][] matrizChave = new int[TAMANHO_MATRIZ][TAMANHO_MATRIZ];

    int[][] geraChaveMatriz(int[] iTexto) {
        int iSomatorio = 0;
        for (int idxLinha = 0; idxLinha < TAMANHO_MATRIZ; ++idxLinha) {
            for (int idxColuna = 0; idxColuna < TAMANHO_MATRIZ; ++idxColuna) {
                matrizChave[idxColuna][idxLinha] = iTexto[iSomatorio++];
            }
        }

        return matrizChave;
    }
}
