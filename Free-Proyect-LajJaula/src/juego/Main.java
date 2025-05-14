package juego;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame finestra = new JFrame("La Jaula - Joc");


        JuegoPanel panel = new JuegoPanel();

        ImageIcon icon = new ImageIcon("imagenes/logo.png");
        finestra.setIconImage(icon.getImage());

        finestra.add(panel);


        finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        finestra.setResizable(false);
        finestra.pack();
        finestra.setLocationRelativeTo(null);
        finestra.setVisible(true);
    }
}