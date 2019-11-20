/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apoio;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.UIManager;

/**
 *
 * @author morgana
 */
public class Progress extends JWindow {

    private JPanel jContentPane;
    private JLabel jLabel;
    private JProgressBar jProgressBar;

    /**
     * This is the default constructor
     */
    public Progress() {
        super();
        initialize();
        this.setVisible(true);
    }

    /**
     * Método Construtor
     */
    private void initialize() {
        this.setSize(250, 100);

        UIManager.put("ProgressBar.selectionBackground", Color.black);
        UIManager.put("ProgressBar.selectionForeground", Color.white);
        UIManager.put("ProgressBar.foreground", new Color(8, 32, 128));
        UIManager.put("ProgressBar.cellLength", new Integer(5));
        UIManager.put("ProgressBar.cellSpacing", new Integer(1));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension size = getSize();

        if (size.width > screenSize.width) {
            size.width = screenSize.width;
        }

        if (size.height > screenSize.height) {
            size.height = screenSize.height;
        }

        this.setLocation((screenSize.width - size.width) / 2, (screenSize.height - size.height) / 2);

        this.setContentPane(getJContentPane());
    }
    
    /**
     * Inicializa a Label
     */
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jLabel = new JLabel();
            jLabel.setSize(new java.awt.Dimension(200, 100));
            jLabel.setLocation(new java.awt.Point(0, 0));
            jLabel.setText("Processando dados. Aguarde...");
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(jLabel, null);
        }
        return jContentPane;
    }

    /**
     * Inicializa a ProgressBar
     */
    private JProgressBar getJProgressBar() {
        if (jProgressBar == null) {
            jProgressBar = new JProgressBar();
            jProgressBar.setBounds(new java.awt.Rectangle(0, 150, 250, 20));
            jProgressBar.setStringPainted(true);
            jProgressBar.setIndeterminate(true);
        }
        return jProgressBar;
    }
}
