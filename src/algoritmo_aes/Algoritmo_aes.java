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
    private static int QTD_ROUND_KEY = 11;
    private static Arquivo arquivo = new Arquivo();
    private static int DEBUG = 0;
    private static ArrayList<RoundKey> roundKeys = new ArrayList<>();
    private static RoundKey chaveGerador;

    public static void main(String[] args) {
        chaveGerador = new RoundKey(arquivo.requisitarChave());
        roundKeys.add(chaveGerador);

        if (DEBUG == 1) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    System.out.print("[" + chaveGerador.getRoundKey()[i][j] + "]");
                }
                System.out.print("\n");
            }
        }

        //Gera chave complicada
        String[] wordComplicada = {"6f", "c6", "10", "a7"};
        RoundKey RoundKeyInicial = new RoundKey();
        RoundKeyInicial.setWord(wordComplicada, 0);

        for (int idxRoundKey = 1; idxRoundKey < QTD_ROUND_KEY; ++idxRoundKey) {
            RoundKeyInicial.setNovaRoundKey(roundKeys.get(roundKeys.size() - 1));
            roundKeys.add(RoundKeyInicial);
        }
    }

}
