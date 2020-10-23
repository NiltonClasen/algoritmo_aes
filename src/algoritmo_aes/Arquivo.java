/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmo_aes;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Nilton
 */
public class Arquivo {

    public File file;

    public File selecionarArquivo() {
        JFileChooser jfc = new JFileChooser();
        int resposta = jfc.showOpenDialog(null);
        if (resposta == JFileChooser.APPROVE_OPTION) {
            return file = jfc.getSelectedFile();
        } else {
            return null;
        }
    }

    public int[] requisitarChave() {
        //realizar tratamento p digitar mais que 16
        //return JOptionPane.showInputDialog("Digite a chave em bytes, separados por vírgula").trim();
        String sTexto = "40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56";
        String[] sTextoSeparado = sTexto.split(",");
        int[] iValores = new int[16];
        for (int idx = 0; idx < 16; ++idx) {
            iValores[idx] = Integer.parseInt(sTextoSeparado[idx]);
        }
        return iValores;
    }

    public void criptografarArquivo() {
        String nome = JOptionPane.showInputDialog("Digite o nome do arquivo que será gerado").trim();
    }
}
