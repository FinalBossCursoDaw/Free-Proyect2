package juego;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class JuegoPanel extends JPanel implements KeyListener {

    private final int[][] mapa = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };

    private Image mur;
    private Image terra;
    private Image personajeActual,personajeAbajo,personajeDerecha,personajeIzquierda,personajeArriba;
    private int JugadorFila=1;
    private int JugadorColumna=1;
    public JuegoPanel() {
        setPreferredSize(new Dimension(640, 480));
        setFocusable(true);
        addKeyListener(this);

        mur = new ImageIcon("imagenes/mazmorra/muro.png").getImage();
        terra = new ImageIcon("imagenes/mazmorra/suelo.png").getImage();
        personajeAbajo = new ImageIcon("imagenes/Personaje/personaje-Abajo.png").getImage();
        personajeDerecha = new ImageIcon("imagenes/Personaje/personaje-Derecha.png").getImage();
        personajeIzquierda = new ImageIcon("imagenes/Personaje/personaje-Izquierda.png").getImage();
        personajeArriba= new ImageIcon("imagenes/Personaje/personaje-arriba.png").getImage();


        personajeActual = personajeAbajo;

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        for (int fila = 0; fila < mapa.length; fila++) {
            for (int col = 0; col < mapa[0].length; col++) {
                int x = col * 32;
                int y = fila * 32;

                if (mapa[fila][col] == 1) {
                    g.drawImage(mur, x, y, 32, 32, null);
                } else {
                    g.drawImage(terra, x, y, 32, 32, null);
                }
            }

        }

        int x = JugadorColumna * 32;
        int y = JugadorFila * 32;
        g.drawImage(personajeActual, x, y, 32, 32, null);


    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            JugadorFila--;
            personajeActual = personajeArriba ;

        }
        if (key == KeyEvent.VK_DOWN) {
            JugadorFila++;
            personajeActual = personajeAbajo;
        }
        if (key == KeyEvent.VK_LEFT) {
            JugadorColumna--;
            personajeActual = personajeIzquierda;
        }
        if (key == KeyEvent.VK_RIGHT) {
            JugadorColumna++;
            personajeActual = personajeDerecha;
        }
repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

