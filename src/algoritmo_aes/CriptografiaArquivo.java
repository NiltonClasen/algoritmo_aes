/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmo_aes;

import java.awt.Robot;
import java.util.ArrayList;
import sun.security.util.KeyUtil;

/**
 *
 * @author Pichau
 */
public class CriptografiaArquivo {

    public void IniciaCriptogrfia(String[][] sTextoSimples, ArrayList<RoundKey> roundKeys) {
        String[][] sCriptografado = new String[4][4];

        for (int idxLinha = 0; idxLinha < 4; ++idxLinha) {
            for (int idxColuna = 0; idxColuna < 4; ++idxColuna) {
                int iHex1 = Integer.parseInt(sTextoSimples[idxLinha][idxColuna], 16);
                int iHex2 = Integer.parseInt(roundKeys.get(0).getRoundKey()[idxColuna][idxLinha], 16);
                sCriptografado[idxColuna][idxLinha] = Integer.toHexString(iHex1 ^ iHex2);
            }
        }

        for (int idxRoundKeys = 0; idxRoundKeys < 10; ++idxRoundKeys) {
            sCriptografado = RealizaSubBytes(sCriptografado);
            sCriptografado = RealizaShiftRows(sCriptografado);
            if (idxRoundKeys < 9)
                sCriptografado = RealizaMixColumns(sCriptografado);
            
            sCriptografado = MixColumnRoundKey(sCriptografado, roundKeys.get(idxRoundKeys + 1).getRoundKey());
        }
    }

    private String[][] RealizaSubBytes(String[][] sCriptografado) {
        KeyUtils keyUtils = new KeyUtils();
        for (int idx = 0; idx < 4; idx++) {
            sCriptografado[idx] = keyUtils.subsWord(sCriptografado[idx]);
        }

        return sCriptografado;
    }

    private String[][] RealizaShiftRows(String[][] sCriptografado) {
        String[] sTextoNovo = new String[16];
        String[] sTextoBuscaInfo = new String[16];
        int idxNovo = 0;
        for (int idxLinha = 0; idxLinha < 4; ++idxLinha) {
            for (int idxColuna = 0; idxColuna < 4; ++idxColuna, ++idxNovo) {
                sTextoBuscaInfo[idxNovo] = sCriptografado[idxColuna][idxLinha];
            }
        }

        int[] iMovimentacao = {12, 8, 4};
        for (int idx = 0; idx < 16; idx++) {
            String sValor = sTextoBuscaInfo[idx];
            if (idx == 0 || idx % 4 == 0) {
                sTextoNovo[idx] = sValor;
                continue;
            }

            int iMover = iMovimentacao[(idx % 4) - 1];
            if (iMover + idx > 15) {
                iMover -= 16;
            }

            sTextoNovo[iMover + idx] = sValor;
        }

        idxNovo = 0;
        for (int idxLinha = 0; idxLinha < 4; ++idxLinha) {
            for (int idxColuna = 0; idxColuna < 4; ++idxColuna, ++idxNovo) {
                sCriptografado[idxLinha][idxColuna] = sTextoNovo[idxNovo];
            }
        }

        return sCriptografado;
    }

    private String[][] RealizaMixColumns(String[][] sCriptografado) {
        String[][] sMatrizMultiplicacao = {{"02", "03", "01", "01"}, {"01", "02", "03", "01"}, {"01", "01", "02", "03"}, {"03", "01", "01", "02"}};
        String[][] sMatrizResultante = new String[4][4];
        String[][] sMatrizShiftRows = sCriptografado;

        for (int idxColuna = 0; idxColuna < 4; ++idxColuna) {
            for (int idxLinha = 0; idxLinha < 4; idxLinha++) {
                String[] sMultiplicacoes = new String[4];
                for (int idxGalois = 0; idxGalois < 4; idxGalois++) {
                    sMultiplicacoes[idxGalois] = GeraGalois(idxLinha, idxColuna, idxGalois, sMatrizMultiplicacao, sMatrizShiftRows);
                }

                sMatrizResultante[idxLinha][idxColuna] = GeraWordResultante(sMultiplicacoes);
            }
        }

        return sMatrizResultante;
    }

    private String GeraGalois(int idxLinha, int idxColuna, int idxGalois, String[][] sMatrizMultiplicacao, String[][] sMatrizShiftRows) {
        int iValor1 = Integer.parseInt(String.valueOf(sMatrizShiftRows[idxColuna][idxGalois].charAt(0)), 16);
        int iValor2 = 0;

        if (sMatrizShiftRows[idxColuna][idxGalois].length() == 1) {
            iValor2 = iValor1;
            iValor1 = 0;
        } else {
            iValor2 = Integer.parseInt(String.valueOf(sMatrizShiftRows[idxColuna][idxGalois].charAt(1)), 16);
        }

        int iMult1 = Integer.parseInt(String.valueOf(sMatrizMultiplicacao[idxLinha][idxGalois].charAt(0)), 16);;
        int iMult2 = Integer.parseInt(String.valueOf(sMatrizMultiplicacao[idxLinha][idxGalois].charAt(1)), 16);

        if ((iValor1 == 0 && iValor2 == 0) || (iMult1 == 0 && iMult2 == 0)) {
            return "0";
        } else {
            if (iValor1 == 0 && iValor2 == 1) {
                return sMatrizMultiplicacao[idxLinha][idxGalois];
            } else if (iMult1 == 0 && iMult2 == 1) {
                return sMatrizShiftRows[idxColuna][idxGalois];
            }
        }

        int iResultado = Constants.getValorTabelaL(iValor1, iValor2) + Constants.getValorTabelaL(iMult1, iMult2);

        if (iResultado > 0xff) {
            iResultado -= 0xff;
        }

        String sResultadoHex = Integer.toHexString(iResultado);
        int iResultado1 = Integer.parseInt(String.valueOf(sResultadoHex.charAt(0)), 16);
        int iResultado2 = 0;

        if (sResultadoHex.length() == 1) {
            iResultado2 = iResultado1;
            iResultado1 = 0;
        } else {
            iResultado2 = Integer.parseInt(String.valueOf(sResultadoHex.charAt(1)), 16);
        }

        return Integer.toHexString(Constants.getValorTabelaE(iResultado1, iResultado2));
    }

    private String GeraWordResultante(String[] sMultiplicacoes) {
        int iValor1 = Integer.parseInt(sMultiplicacoes[0], 16);
        for (int idx = 1; idx < 4; ++idx) {
            iValor1 ^= Integer.parseInt(sMultiplicacoes[idx], 16);
        }

        return Integer.toHexString(iValor1);
    }

    private String[][] MixColumnRoundKey(String[][] sMatrizResultante, String[][] sMatrizShiftRows) {
        KeyUtils keyUtils = new KeyUtils();

        String[][] sResultadoFinal = new String[4][4];
        for (int idx = 0; idx < 4; ++idx) {
            sResultadoFinal[idx] = keyUtils.fazOperacaoXOR(sMatrizResultante[idx], sMatrizShiftRows[idx]);
        }

        return sResultadoFinal;
    }
}
