package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Menu {
    private double x, y, ancho, alto;
    private Hechizo.Apocalipsis apocalipsis;
    private Hechizo.MisilMagico misilMagico;
    private boolean lanzarSeleccionado;
    private Image fondo;
    
    // Posiciones y dimensiones para facilitar la alineación
    private double rectLanzarAncho;
    private double rectLanzarAlto;

    public Menu(double x, double y, double ancho, double alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.fondo = Herramientas.cargarImagen("juego/img/madera.jpg");
        
        // Crear los hechizos específicos, posiciones verticales incrementales centrados en x
        this.apocalipsis = new Hechizo.Apocalipsis(x, y - alto/2 + 250);
        this.misilMagico = new Hechizo.MisilMagico(x, y - alto/2 + 320);
    }

    private void dibujarBarrasVidaMana(Entorno entorno, Mago mago) {
        double barraX = x; // Centro horizontal
        double barraY = y - alto / 2 + 60; // Posición vertical para barras
        double anchoTotal = ancho - 40;
        double altoTotal = 70;
        double anchoBarra = anchoTotal - 20;
        double altoBarra = 22;
        
        // Fondo general de las barras con sombra sutil
        entorno.dibujarRectangulo(barraX, barraY, anchoTotal, altoTotal, 0, new Color(30, 30, 40, 220));
        entorno.dibujarImagen(fondo, 1328, 400, 0, 2.3);
        // Posiciones de las barras dentro del recuadro
        double yVida = barraY - 15;
        double yMana = barraY + 20;

        // === BARRA DE VIDA ===
        // Fondo barra vida
        entorno.dibujarRectangulo(barraX, yVida, anchoBarra, altoBarra, 0, new Color(30, 10, 10));
        // Vida actual
        double porcentajeVida = mago.getVidaActual() / mago.getVidaMaxima();
        double anchoVidaActual = anchoBarra * porcentajeVida;

        Color colorVida;
        if (porcentajeVida > 0.6) {
            colorVida = new Color(0, 200, 0);
        } else if (porcentajeVida > 0.3) {
            colorVida = new Color(230, 140, 0);
        } else {
            colorVida = new Color(180, 30, 30);
        }

        if (anchoVidaActual > 0) {
            entorno.dibujarRectangulo(barraX - (anchoBarra - anchoVidaActual) / 2, yVida, 
                                    anchoVidaActual, altoBarra, 0, colorVida);
        }
        // Borde barra vida
        entorno.dibujarRectangulo(barraX, yVida, anchoBarra, altoBarra, 0, new Color(220, 220, 220, 120));

        // === BARRA DE MANA ===
        // Fondo barra mana
        entorno.dibujarRectangulo(barraX, yMana, anchoBarra, altoBarra, 0, new Color(10, 10, 40));
        // Mana actual
        double porcentajeMana = mago.getManaActual() / mago.getManaMaximo();
        double anchoManaActual = anchoBarra * porcentajeMana;

        Color colorMana = new Color(50, 120, 255);
        if (anchoManaActual > 0) {
            entorno.dibujarRectangulo(barraX - (anchoBarra - anchoManaActual) / 2, yMana, 
                                    anchoManaActual, altoBarra, 0, colorMana);
        }
        // Borde barra mana
        entorno.dibujarRectangulo(barraX, yMana, anchoBarra, altoBarra, 0, new Color(170, 200, 250, 120));

        // === TEXTO BARRAS ===
        entorno.cambiarFont("Gabriola", 18, Color.WHITE);
        entorno.escribirTexto("HP: " + (int)mago.getVidaActual() + " / " + (int)mago.getVidaMaxima(), 
                                barraX - anchoBarra / 2 + 10, yVida - 5);
        entorno.escribirTexto("MP: " + (int)mago.getManaActual() + " / " + (int)mago.getManaMaximo(), 
                                barraX - anchoBarra / 2 + 10, yMana - 5);
        
        // === KILLS ===
        entorno.cambiarFont("Gabriola", 40, Color.RED);
        entorno.escribirTexto("Kills: " + Juego.killMur, 1000, 700);
    }

    public void dibujar(Entorno entorno, Mago mago) {
        // Dibujar el fondo del menú
        entorno.dibujarRectangulo(x, y, ancho, alto, 0, new Color(45, 45, 55, 230));

        // Barras de vida y mana arriba del menú
        dibujarBarrasVidaMana(entorno, mago);

        // Separador visual debajo de barras usando rectángulo delgado semitransparente
        double separadorY = y - alto/2 + 140;
        entorno.dibujarRectangulo(x, separadorY, ancho - 40, 4, 0, new Color(100, 100, 120, 150));

        // Título centrado horizontalmente
        entorno.cambiarFont("Bahnschrift", 44, Color.BLACK);
        entorno.escribirTexto("HECHIZOS", 960, y - alto/2 + 190);

        // Posiciones verticales para hechizos
        double hechizoBaseY = y - alto/2 + 250;
        double espacioEntreHechizos = 70;

        // Dibujar hechizo Apocalipsis centrado en x
        if (apocalipsis.estaSeleccionado()) {
            entorno.cambiarFont("Gabriola", 22, new Color(50, 255, 50));
        } else {
            entorno.cambiarFont("Gabriola", 22, new Color(220, 220, 220));
        }
        entorno.escribirTexto("Apocalipsis", x-50, hechizoBaseY);

        // Dibujar hechizo Misil Magico centrado en x
        if (misilMagico.estaSeleccionado()) {
            entorno.cambiarFont("Gabriola", 22, new Color(50, 255, 50));
        } else {
            entorno.cambiarFont("Gabriola", 22, new Color(220, 220, 220));
        }
        entorno.escribirTexto("Misil Magico", x-50, hechizoBaseY + espacioEntreHechizos);

    }

    public void verificarClick(int mouseX, int mouseY) {
        // Ajustar área clickeable de hechizos centrada en x y con dimensiones heurísticas
        double hechizoAncho = 160;
        double hechizoAlto = 40;

        if (mouseX >= apocalipsis.getX() - hechizoAncho/2 && mouseX <= apocalipsis.getX() + hechizoAncho/2 &&
            mouseY >= apocalipsis.getY() - hechizoAlto/2 && mouseY <= apocalipsis.getY() + hechizoAlto/2) {
            misilMagico.setSeleccionado(false);
            apocalipsis.setSeleccionado(true);
            return;
        }

        if (mouseX >= misilMagico.getX() - hechizoAncho/2 && mouseX <= misilMagico.getX() + hechizoAncho/2 &&
            mouseY >= misilMagico.getY() - hechizoAlto/2 && mouseY <= misilMagico.getY() + hechizoAlto/2) {
            apocalipsis.setSeleccionado(false);
            misilMagico.setSeleccionado(true);
            return;
        }
    }

    public String getHechizoSeleccionado() {
        if (apocalipsis.estaSeleccionado()) {
            return "Apocalipsis";
        } 
        if (misilMagico.estaSeleccionado()) {
            return "Misil Magico";
        }
        return null;
    }

}

