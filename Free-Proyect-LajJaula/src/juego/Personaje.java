package juego;

import java.awt.Image;

public class Personaje {

    private int fila;
    private int columna;

    private Image imagenActual;
    private final Image imgAbajo;
    private final Image imgArriba;
    private final Image imgIzquierda;
    private final Image imgDerecha;

    public Personaje(int filaInicial, int columnaInicial,
                     Image imgAbajo, Image imgArriba, Image imgIzquierda, Image imgDerecha) {
        this.fila = filaInicial;
        this.columna = columnaInicial;
        this.imgAbajo = imgAbajo;
        this.imgArriba = imgArriba;
        this.imgIzquierda = imgIzquierda;
        this.imgDerecha = imgDerecha;
        this.imagenActual = imgAbajo;
    }

    // Métodos para mover
    public void moverArriba(int[][] mapa) {
        if (mapa[fila - 1][columna] == 0) {
            fila--;
        }
        imagenActual = imgArriba;
    }

    public void moverAbajo(int[][] mapa) {
        if (mapa[fila + 1][columna] == 0) {
            fila++;
        }
        imagenActual = imgAbajo;
    }

    public void moverIzquierda(int[][] mapa) {
        if (mapa[fila][columna - 1] == 0) {
            columna--;
        }
        imagenActual = imgIzquierda;
    }

    public void moverDerecha(int[][] mapa) {
        if (mapa[fila][columna + 1] == 0) {
            columna++;
        }
        imagenActual = imgDerecha;
    }

    // Getters
    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public Image getImagenActual() {
        return imagenActual;
    }
    public void reiniciarPosicion() {
        this.fila = 1;
        this.columna = 1;
    }
}