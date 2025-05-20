package juego;

import javax.swing.*;
import java.awt.*;

public class Enemigo {
    private int fila, columna;
    private int direccion = 1; // 1 o -1
    private boolean esVertical;

    private Image enemigoAbajo, enemigoArriba, enemigoDerecha, enemigoIzquierda;
    private Image imagenActual;

    public Enemigo(int fila, int columna, boolean esVertical) {
        this.fila = fila;
        this.columna = columna;
        this.esVertical = esVertical;

        enemigoAbajo = new ImageIcon("imagenes/enemigo/Enemigo-Abajo.png").getImage();
        enemigoArriba = new ImageIcon("imagenes/enemigo/Enemigo-Arriba.png").getImage();
        enemigoDerecha = new ImageIcon("imagenes/enemigo/Enemigo-Derecha.png").getImage();
        enemigoIzquierda = new ImageIcon("imagenes/enemigo/Enemigo-Izquierda.png").getImage();

        if (esVertical) {
            imagenActual = enemigoAbajo;
        } else {
            imagenActual = enemigoDerecha;
        }
    }

    public void mover(int[][] mapa) {
        if (esVertical) {
            int novaFila = fila + direccion;

            if (mapa[novaFila][columna] == 0) {
                fila = novaFila;

                if (direccion == 1) {
                    imagenActual = enemigoAbajo;
                } else {
                    imagenActual = enemigoArriba;
                }

            } else {
                direccion = direccion * -1; // Canvia de direcció
            }

        } else {
            int novaCol = columna + direccion;

            if (mapa[fila][novaCol] == 0) {
                columna = novaCol;

                if (direccion == 1) {
                    imagenActual = enemigoDerecha;
                } else {
                    imagenActual = enemigoIzquierda;
                }

            } else {
                direccion = direccion * -1;
            }
        }
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public Image getImagen() {
        return imagenActual;
    }
}
