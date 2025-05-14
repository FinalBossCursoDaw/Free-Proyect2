package juego;

import java.awt.*;


    public class Objeto {
        public static final int TIPO_CINTA = 1;
        public static final int TIPO_PELOTA = 2;
        public static final int TIPO_ZAPATILLAS = 3;

        private int fila, columna;
        private int tipo;
        private Image imagen;

        public Objeto(int fila, int columna, int tipo, Image imagen) {
            this.fila = fila;
            this.columna = columna;
            this.tipo = tipo;
            this.imagen = imagen;
        }

        public int getFila() {
            return fila;
        }

        public int getColumna() {
            return columna;
        }

        public int getTipo() {
            return tipo;
        }

        public Image getImagen() {
            return imagen;
        }
}