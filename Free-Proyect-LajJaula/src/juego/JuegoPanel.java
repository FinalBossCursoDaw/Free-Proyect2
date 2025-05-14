package juego;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

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
    private Image cinta, pelota, zapatillas;

    private int JugadorFila=1;
    private int JugadorColumna=1;

    private List<Objeto> objetos = new ArrayList<>();
    private Random random = new Random();

    private  int cintasRecogidas=0;
    private int pilotesRecogidas = 0;
    private int zapatillasRecogidas = 0;

    private int MAX_RECOLLIDES = 2;

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

        cinta = new ImageIcon("imagenes/objetos/Objeto-Cinta.png").getImage();
        pelota = new ImageIcon("imagenes/objetos/Objeto-Pelota.png").getImage();
        zapatillas = new ImageIcon("imagenes/objetos/Objeto-Zapatillas.png").getImage();

        int[] pos1 = getPosicioAleatoria();
        objetos.add(new Objeto(pos1[0], pos1[1], Objeto.TIPO_CINTA, cinta));

        int[] pos2 = getPosicioAleatoria();
        objetos.add(new Objeto(pos2[0], pos2[1], Objeto.TIPO_PELOTA, pelota));

        int[] pos3 = getPosicioAleatoria();
        objetos.add(new Objeto(pos3[0], pos3[1], Objeto.TIPO_ZAPATILLAS, zapatillas));

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


        for (Objeto obj : objetos) {
            int objX = obj.getColumna() * 32;
            int objY = obj.getFila() * 32;
            g.drawImage(obj.getImagen(), objX, objY, 32, 32, null);
        }
        int contadorX = 10;
        int contadorY = 10;
        int separacioVertical = 40;

        g.drawImage(cinta, contadorX, contadorY, 32, 32, null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString(cintasRecogidas + " / " + MAX_RECOLLIDES, contadorX + 40, contadorY + 22);

        g.drawImage(pelota, contadorX, contadorY + separacioVertical, 32, 32, null);
        g.drawString(pilotesRecogidas + " / " + MAX_RECOLLIDES, contadorX + 40, contadorY + separacioVertical + 22);

        g.drawImage(zapatillas, contadorX, contadorY + separacioVertical * 2, 32, 32, null);
        g.drawString(zapatillasRecogidas + " / " + MAX_RECOLLIDES, contadorX + 40, contadorY + separacioVertical * 2 + 22);
    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            if (mapa[JugadorFila - 1][JugadorColumna] == 0) {
                JugadorFila--;
            }
            personajeActual = personajeArriba;
        }
        if (key == KeyEvent.VK_DOWN) {
            if (mapa[JugadorFila + 1][JugadorColumna] == 0) {
                JugadorFila++;
            }
            personajeActual = personajeAbajo;
        }
        if (key == KeyEvent.VK_LEFT) {
            if (mapa[JugadorFila][JugadorColumna - 1] == 0) {
                JugadorColumna--;
            }
            personajeActual = personajeIzquierda;
        }
        if (key == KeyEvent.VK_RIGHT) {
            if (mapa[JugadorFila][JugadorColumna + 1] == 0) {
                JugadorColumna++;
            }
            personajeActual = personajeDerecha;
        }
        Objeto aEliminar = null;

        for (int i = 0; i < objetos.size(); i++) {
            Objeto obj = objetos.get(i);

            if (obj.getFila() == JugadorFila && obj.getColumna() == JugadorColumna) {
                aEliminar = obj;
            }
        }

        if (aEliminar != null) {
            objetos.remove(aEliminar);

            if (aEliminar.getTipo() == Objeto.TIPO_CINTA) {
                cintasRecogidas++;
                if (cintasRecogidas < MAX_RECOLLIDES) {
                    int[] pos = getPosicioAleatoria();
                    objetos.add(new Objeto(pos[0], pos[1], Objeto.TIPO_CINTA, cinta));
                }
            }
            if (aEliminar.getTipo() == Objeto.TIPO_PELOTA) {
                pilotesRecogidas++;
                if (pilotesRecogidas < MAX_RECOLLIDES) {
                    int[] pos = getPosicioAleatoria();
                    objetos.add(new Objeto(pos[0], pos[1], Objeto.TIPO_PELOTA, pelota));
                }
            }
            if (aEliminar.getTipo() == Objeto.TIPO_ZAPATILLAS) {
                zapatillasRecogidas++;
                if (zapatillasRecogidas < MAX_RECOLLIDES) {
                    int[] pos = getPosicioAleatoria();
                    objetos.add(new Objeto(pos[0], pos[1], Objeto.TIPO_ZAPATILLAS, zapatillas));
                }
            }
        }
        if (cintasRecogidas == MAX_RECOLLIDES &&
                pilotesRecogidas == MAX_RECOLLIDES &&
                zapatillasRecogidas == MAX_RECOLLIDES) {

            JOptionPane.showMessageDialog(this, "🏆 Has recollit 2 de cada objecte!\nHas guanyat!",
                    "Fi del joc", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }

        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    private int[] getPosicioAleatoria() {
        int fila, columna;
        do {
            fila = random.nextInt(mapa.length);
            columna = random.nextInt(mapa[0].length);
        } while (mapa[fila][columna] != 0);
        return new int[]{fila, columna};
    }
}

