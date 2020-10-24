/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmo_aes;

import java.util.ArrayList;

/**
 *
 * @author Nilton, Gustavo e Kienen
 */
public class Algoritmo_aes {

    private static int QTD_ROUND_KEY = 10;
    private static Arquivo arquivo = new Arquivo();
    private static int DEBUG = 1;
    private static ArrayList<RoundKey> roundKeys = new ArrayList<>();
    private static RoundKey chaveGerador;
    private static KeyUtils keyUtils = new KeyUtils();

    public static void main(String[] args) {
        chaveGerador = new RoundKey(arquivo.requisitarChave());
        roundKeys.add(chaveGerador);
        logger(chaveGerador, null, "Chave de entrada");
        for (int idxRoundKey = 0; idxRoundKey < QTD_ROUND_KEY; ++idxRoundKey) {
            String[] a = keyUtils.copiarChaveAnterior(roundKeys.get(idxRoundKey));
            logger(null, a, "Copiar Chave Anterior");
            String[] a2 = keyUtils.rotWord(a);
            logger(null, a2, "rotword");
            String[] a3 = keyUtils.subsWord(a2);
            logger(null, a3, "subsWord");
            String[] a4 = keyUtils.roundConstant(a3, idxRoundKey);
            logger(null, a4, "roundConstant");
            String[] a5 = keyUtils.fazOperacaoXOR(a3, a4);
            logger(null, a5, "fazOperacaoXOR");
            String[] a6 = keyUtils.primeiraPalavra(roundKeys.get(idxRoundKey).getWord(0), a5);
            logger(null, a6, "primeiraPalavra");
            RoundKey RoundKeyInicial = new RoundKey();
            RoundKeyInicial.setWord(a6, 0);
            RoundKeyInicial.setNovaRoundKey(roundKeys.get(idxRoundKey));
            roundKeys.add(RoundKeyInicial);
            logger(RoundKeyInicial, null, "RoundKey adicionada");
        }
        //Le informações do arquivo e deixa em HexaDecimal
        String[] textoSimples = {"44", "45", "53", "45", "4e", "56", "4f", "4c", "56", "49", "4d", "45", "4e", "54", "4f", "21"};
        CriptografiaArquivo criptArquivo = new CriptografiaArquivo(textoSimples);
        criptArquivo.IniciaCriptogrfia(roundKeys.get(0));
    }

    public static void logger(RoundKey roundkey, String[] word, String etapa) {
        if (DEBUG == 1) {
            System.out.println("\\\\Etapa " + etapa + "////");
            if (roundkey != null) {
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        System.out.print("[" + roundkey.getRoundKey()[i][j] + "]");
                    }
                    System.out.print("\n");
                }
            } else {
                for (int i = 0; i < word.length; i++) {
                    System.out.println("[" + word[i] + "]");
                }
            }
        }
    }
}
