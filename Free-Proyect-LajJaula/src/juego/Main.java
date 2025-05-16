package juego;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        String nombreJugador = JOptionPane.showInputDialog(null,
                "Introdueix el teu nom:", "Benvingut a La Jaula!",
                JOptionPane.PLAIN_MESSAGE);

        if (nombreJugador == null || nombreJugador.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Has de posar un nom per jugar.");
            System.exit(0);
        }

        JFrame finestra = new JFrame("La Jaula - Joc");


        JuegoPanel panel = new JuegoPanel(nombreJugador);

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