/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmo_aes;

import java.awt.Robot;

/**
 *
 * @author Pichau
 */
public class CriptografiaArquivo {

    String[] sTextoArquivo = new String[16];

    public CriptografiaArquivo(String[] sTextoArquivo) {
        this.sTextoArquivo = sTextoArquivo;
    }

    public void IniciaCriptogrfia(RoundKey primeiraRoundKey) {
        int idxString = 0;

        for (int idxLinha = 0; idxLinha < 4; ++idxLinha) {
            for (int idxColuna = 0; idxColuna < 4; ++idxColuna) {
                int iHex1 = Integer.parseInt(sTextoArquivo[idxString], 16);
                int iHex2 = Integer.parseInt(primeiraRoundKey.getRoundKey()[idxColuna][idxLinha], 16);
                sTextoArquivo[idxString++] = Integer.toHexString(iHex1 ^ iHex2);
            }
        }

        RealizaSubBytes();
        RealizaShiftRows();
        RealizaMixColumns();
    }

    private void RealizaSubBytes() {
        KeyUtils keyUtils = new KeyUtils();
        sTextoArquivo = keyUtils.subsWord(sTextoArquivo);
    }

    private void RealizaShiftRows() {
        String[] sTextoArquivoNovo = new String[16];
        int[] iMovimentacao = {12, 8, 4};
        for (int idx = 0; idx < 16; idx++) {
            String sValor = sTextoArquivo[idx];
            if (idx == 0 || idx % 4 == 0) {
                sTextoArquivoNovo[idx] = sValor;
                continue;
            }

            int iMover = iMovimentacao[(idx % 4) - 1];

            if (iMover + idx > 15) {
                iMover -= 16;
            }

            sTextoArquivoNovo[iMover + idx] = sValor;
        }
        sTextoArquivo = sTextoArquivoNovo;
    }

    private void RealizaMixColumns() {

    }
}
