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

    private static Arquivo arquivo = new Arquivo();
    private static int debug = 1;
    private ArrayList<RoundKey> roundKeys = new ArrayList<>();
    private static RoundKey chaveGerador;

    public static void main(String[] args) {
        chaveGerador = new RoundKey(arquivo.requisitarChave());

        if (debug == 1) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    System.out.print("[" + chaveGerador.getRoundKey()[i][j] + "]");
                }
                System.out.print("\n");
            }
        }
        
        
        int[] i = chaveGerador.getWord(1);
    }

}
