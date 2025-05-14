package juego;

import java.awt.*;
import javax.swing.*;

public class Enemigo {

    private int fila;
    private int columna;
    private Image imagenActual;
    private int direccion = 1;
    private boolean vertical;


    private Image enemigoabajo,enemigoDerecha,enemigoArriba,enemigoIzquierda;

    public Enemigo(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;


        enemigoabajo = new ImageIcon("imagenes/enemigos/enemigo-abajo.png").getImage();
        enemigoArriba= new ImageIcon("imagenes/enemigos/enemigo-arriba.png").getImage();
        enemigoDerecha=new ImageIcon("imagenes/enemigos/enemigo-derecha.png").getImage();
        enemigoIzquierda=new ImageIcon("imagenes/enemigos/enemigo-izquierda.png").getImage();
        imagenActual = enemigoabajo;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public Image getImagen() {
        return imagenActual;
    }
    public void mover(int[][] mapa) {
        if (vertical) {
            int nuevaFila = fila + direccion;


            if (mapa[nuevaFila][columna] == 0) {
                fila = nuevaFila;

                if (direccion == 1) {
                    imagenActual = enemigoabajo;
                } else {
                    imagenActual = enemigoArriba;
                }
            } else {
                // Si choca con muro, cambia de dirección
                direccion = direccion * -1;
            }
        } else {
            int nuevaColumna = columna + direccion;

            if (mapa[fila][nuevaColumna] == 0) {
                columna = nuevaColumna;

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

}