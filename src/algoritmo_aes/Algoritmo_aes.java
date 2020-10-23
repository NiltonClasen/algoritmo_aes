/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmo_aes;

/**
 *
 * @author Nilton, Gustavo e Kienen
 */
public class Algoritmo_aes {

    private static Arquivo arquivo = new Arquivo();
    private static GeraChave geraChave = new GeraChave();
    
    public static void main(String[] args) {
        int[] iChave = arquivo.requisitarChave();
        geraChave.geraChaveMatriz(iChave);
    }
    
}
