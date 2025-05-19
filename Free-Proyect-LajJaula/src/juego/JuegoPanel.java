package juego;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
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
//mapa//
    private Image mur, terra;
//personaje//
    private Personaje personaje;
//objeto//
    private Image cinta, pelota, zapatillas,corazon;
    private List<Objeto> objetos = new ArrayList<>();
private List<Objeto> vidas = new ArrayList<>();
    private Random random = new Random();
    private int cintasRecogidas = 0;
    private int pilotesRecogidas = 0;
    private int zapatillasRecogidas = 0;
    private final int MAX_RECOLLIDES = 2;
//enemigo//
private List<Enemigo> enemigos = new ArrayList<>();
//nombre//
    private String nombreJugador;


    public JuegoPanel(String nombreJugador) {
        setPreferredSize(new Dimension(640, 480));
        setFocusable(true);
        addKeyListener(this);
        //nombre//
        this.nombreJugador = nombreJugador;


//mapa//
        mur = new ImageIcon("imagenes/mazmorra/muro.png").getImage();
        terra = new ImageIcon("imagenes/mazmorra/suelo.png").getImage();
//personaje//
        Image pjAbajo = new ImageIcon("imagenes/Personaje/personaje-Abajo.png").getImage();
        Image pjArriba = new ImageIcon("imagenes/Personaje/personaje-arriba.png").getImage();
        Image pjIzquierda = new ImageIcon("imagenes/Personaje/personaje-Izquierda.png").getImage();
        Image pjDerecha = new ImageIcon("imagenes/Personaje/personaje-Derecha.png").getImage();
        personaje = new Personaje(1, 1, pjAbajo, pjArriba, pjIzquierda, pjDerecha);
//objeto//
        cinta = new ImageIcon("imagenes/objetos/Objeto-Cinta.png").getImage();
        pelota = new ImageIcon("imagenes/objetos/Objeto-Pelota.png").getImage();
        zapatillas = new ImageIcon("imagenes/objetos/Objeto-Zapatillas.png").getImage();
        corazon = new ImageIcon("imagenes/objetos/Objeto-Corazon.png").getImage();

        objetos.add(new Objeto(getPosicioAleatoria()[0], getPosicioAleatoria()[1], Objeto.TIPO_CINTA, cinta));
        objetos.add(new Objeto(getPosicioAleatoria()[0], getPosicioAleatoria()[1], Objeto.TIPO_PELOTA, pelota));
        objetos.add(new Objeto(getPosicioAleatoria()[0], getPosicioAleatoria()[1], Objeto.TIPO_ZAPATILLAS, zapatillas));
        for (int i = 0; i < 3; i++) {
            vidas.add(new Objeto(0, mapa[0].length - 1 - i, Objeto.TIPO_VIDA, corazon));
        }


        int[] posEnemigo = getPosicioAleatoria();
        enemigos.add(new Enemigo(5, 5, true));
        enemigos.add(new Enemigo(8, 3, false));
        enemigos.add(new Enemigo(2, 10, false));
        enemigos.add(new Enemigo(10, 15, true));

        Timer timer = new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Enemigo enemigo : enemigos) {
                    enemigo.mover(mapa);
                }
                repaint();
                comprobarColisionConEnemigo();
            }
        });
        timer.start();
    }
    private void comprobarColisionConEnemigo() {
for (Enemigo enemigo : enemigos) {
    if (personaje.getColumna()==enemigo.getColumna() && personaje.getFila()==enemigo.getFila()) {
        if (!vidas.isEmpty()) {
            vidas.remove(vidas.get(vidas.size()-1));
            personaje.reiniciarPosicion();
        }
        if (vidas.isEmpty()) {
            BD.conectar(nombreJugador, cintasRecogidas, pilotesRecogidas, zapatillasRecogidas, 0);
            JOptionPane.showMessageDialog(this,
                    " T'has quedat sense vides! Game Over!",
                    "Fi del joc",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }

    }
}

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

        g.drawImage(personaje.getImagenActual(), personaje.getColumna() * 32, personaje.getFila() * 32, 32, 32, null);
        for (Objeto obj : objetos) {
            g.drawImage(obj.getImagen(), obj.getColumna() * 32, obj.getFila() * 32, 32, 32, null);
        }

        int x = 10, y = 10, dy = 40;
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawImage(cinta, x, y, 32, 32, null);
        g.drawString(cintasRecogidas + " / " + MAX_RECOLLIDES, x + 40, y + 22);
        g.drawImage(pelota, x, y + dy, 32, 32, null);
        g.drawString(pilotesRecogidas + " / " + MAX_RECOLLIDES, x + 40, y + dy + 22);
        g.drawImage(zapatillas, x, y + dy * 2, 32, 32, null);
        g.drawString(zapatillasRecogidas + " / " + MAX_RECOLLIDES, x + 40, y + dy * 2 + 22);


        for (Enemigo e : enemigos) {
            g.drawImage(e.getImagen(), e.getColumna() * 32, e.getFila() * 32, 32, 32, null);
        }
        for (Objeto vida : vidas) {
            g.drawImage(vida.getImagen(), vida.getColumna() * 32, vida.getFila() * 32, 32, 32, null);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) personaje.moverArriba(mapa);
        if (key == KeyEvent.VK_DOWN) personaje.moverAbajo(mapa);
        if (key == KeyEvent.VK_LEFT) personaje.moverIzquierda(mapa);
        if (key == KeyEvent.VK_RIGHT) personaje.moverDerecha(mapa);

        Objeto aEliminar = null;
        for (Objeto obj : objetos) {
            if (obj.getFila() == personaje.getFila() && obj.getColumna() == personaje.getColumna()) {
                aEliminar = obj;
                break;
            }
        }

        if (aEliminar != null) {
            objetos.remove(aEliminar);

            if (aEliminar.getTipo() == Objeto.TIPO_CINTA) {
                cintasRecogidas++;
                if (cintasRecogidas < MAX_RECOLLIDES) añadirObjeto(Objeto.TIPO_CINTA, cinta);
            }
            if (aEliminar.getTipo() == Objeto.TIPO_PELOTA) {
                pilotesRecogidas++;
                if (pilotesRecogidas < MAX_RECOLLIDES) añadirObjeto(Objeto.TIPO_PELOTA, pelota);
            }
            if (aEliminar.getTipo() == Objeto.TIPO_ZAPATILLAS) {
                zapatillasRecogidas++;
                if (zapatillasRecogidas < MAX_RECOLLIDES) añadirObjeto(Objeto.TIPO_ZAPATILLAS, zapatillas);
            }
        }

        if (cintasRecogidas == MAX_RECOLLIDES && pilotesRecogidas == MAX_RECOLLIDES && zapatillasRecogidas == MAX_RECOLLIDES) {
            BD.conectar(nombreJugador, cintasRecogidas, pilotesRecogidas, zapatillasRecogidas, vidas.size());
            JOptionPane.showMessageDialog(this, "🏆 Has recollit 2 de cada objecte!\nHas guanyat!", "Fi del joc", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
        comprobarColisionConEnemigo();
        repaint();
    }

    private void añadirObjeto(int tipo, Image imagen) {
        int[] pos = getPosicioAleatoria();
        objetos.add(new Objeto(pos[0], pos[1], tipo, imagen));
    }

    private int[] getPosicioAleatoria() {
        int fila, columna;
        do {
            fila = random.nextInt(mapa.length);
            columna = random.nextInt(mapa[0].length);
        } while (mapa[fila][columna] != 0);
        return new int[]{fila, columna};
    }

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}
}
