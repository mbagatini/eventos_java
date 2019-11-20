/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apoio;

import javax.swing.JOptionPane;

/**
 *
 * @author Morgana
 */
public class Mensagem {
    
    public static void mostraInformacao(String titulo, String mensagem){
        JOptionPane.showMessageDialog(null, mensagem, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void mostraAletra(String titulo, String mensagem){
        JOptionPane.showMessageDialog(null, mensagem, titulo, JOptionPane.WARNING_MESSAGE);
    }
    
    public static void mostraErro(String titulo, String mensagem){
        JOptionPane.showMessageDialog(null, mensagem, titulo, JOptionPane.ERROR_MESSAGE);
    }
    
    public static boolean confirmaMensagem(String titulo, String mensagem){
        int conf = JOptionPane.showConfirmDialog(null, mensagem, titulo, JOptionPane.YES_NO_OPTION);
        return conf == 0;
   }
}
