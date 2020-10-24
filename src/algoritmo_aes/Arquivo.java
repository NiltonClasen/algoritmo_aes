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

    public String[] requisitarChave() {
        //realizar tratamento p digitar mais que 16
        //return JOptionPane.showInputDialog("Digite a chave em bytes, separados por vírgula").trim();
        String sTexto = "65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80";
        String[] sTextoSeparado = sTexto.split(",");
        for (int idx = 0; idx < 16; ++idx) {
            sTextoSeparado[idx] = Integer.toHexString(Integer.parseInt(sTextoSeparado[idx]));
        }
        
        return sTextoSeparado;
    }

    public void criptografarArquivo() {
        String nome = JOptionPane.showInputDialog("Digite o nome do arquivo que será gerado").trim();
    }
}
