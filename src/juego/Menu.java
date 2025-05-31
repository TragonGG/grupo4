package juego;

import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Menu {
    private double x, y, ancho, alto;
    private Hechizo[] hechizos;
    private int hechizoSeleccionado = -1; // -1 = ninguno seleccionado
    private Image fondo;

    public Menu(double x, double y, double ancho, double alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.fondo = Herramientas.cargarImagen("juego/img/madera.jpg");
        
        inicializarHechizos();
    }
    
    private void inicializarHechizos() {
        double hechizoBaseY = y - alto/2 + 250;
        double espacioEntreHechizos = 70;
        
        hechizos = new Hechizo[] {
            new Hechizo("Apocalipsis", x, hechizoBaseY, 120, 25, 35, Hechizo.TipoHechizo.APOCALIPSIS),
            new Hechizo("Misil Magico", x, hechizoBaseY + espacioEntreHechizos, 120, 25, 20, Hechizo.TipoHechizo.MISIL_MAGICO),
            new Hechizo("Curacion", x, hechizoBaseY + espacioEntreHechizos * 2, 120, 25, 15, Hechizo.TipoHechizo.CURACION)
        };
    }

    private void dibujarBarrasVidaMana(Entorno entorno, Mago mago) {
        double barraX = x;
        double barraY = y - alto / 2 + 60;
        double anchoTotal = ancho - 40;
        double altoTotal = 70;
        double anchoBarra = anchoTotal - 20;
        double altoBarra = 22;
        
        entorno.dibujarRectangulo(barraX, barraY, anchoTotal, altoTotal, 0, new Color(30, 30, 40, 220));
        entorno.dibujarImagen(fondo, 1328, 400, 0, 2.3);
        
        double yVida = barraY - 15;
        double yMana = barraY + 20;

        // Barra de vida
        entorno.dibujarRectangulo(barraX, yVida, anchoBarra, altoBarra, 0, new Color(30, 10, 10));
        double porcentajeVida = mago.getVidaActual() / mago.getVidaMaxima();
        double anchoVidaActual = anchoBarra * porcentajeVida;

        Color colorVida = porcentajeVida > 0.6 ? new Color(0, 200, 0) :
                         porcentajeVida > 0.3 ? new Color(230, 140, 0) :
                         new Color(180, 30, 30);

        if (anchoVidaActual > 0) {
            entorno.dibujarRectangulo(barraX - (anchoBarra - anchoVidaActual) / 2, yVida, 
                                    anchoVidaActual, altoBarra, 0, colorVida);
        }

        // Barra de mana
        entorno.dibujarRectangulo(barraX, yMana, anchoBarra, altoBarra, 0, new Color(10, 10, 40));
        double porcentajeMana = mago.getManaActual() / mago.getManaMaximo();
        double anchoManaActual = anchoBarra * porcentajeMana;

        if (anchoManaActual > 0) {
            entorno.dibujarRectangulo(barraX - (anchoBarra - anchoManaActual) / 2, yMana, 
                                    anchoManaActual, altoBarra, 0, new Color(50, 120, 255));
        }

        // Texto
        entorno.cambiarFont("Gabriola", 18, Color.WHITE);
        entorno.escribirTexto("HP: " + (int)mago.getVidaActual() + " / " + (int)mago.getVidaMaxima(), 
                            barraX - anchoBarra / 2 + 10, yVida - 5);
        entorno.escribirTexto("MP: " + (int)mago.getManaActual() + " / " + (int)mago.getManaMaximo(), 
                            barraX - anchoBarra / 2 + 10, yMana - 5);
        
        entorno.cambiarFont("Gabriola", 40, Color.RED);
        entorno.escribirTexto("Kills: " + Juego.killMur, 1000, 700);
    }

    public void dibujar(Entorno entorno, Mago mago) {
        entorno.dibujarRectangulo(x, y, ancho, alto, 0, new Color(45, 45, 55, 230));
        dibujarBarrasVidaMana(entorno, mago);
        
        double separadorY = y - alto/2 + 140;
        entorno.dibujarRectangulo(x, separadorY, ancho - 40, 4, 0, new Color(100, 100, 120, 150));
        
        entorno.cambiarFont("Gabriola", 50, Color.WHITE);
        entorno.escribirTexto("HECHIZOS", 960, y - alto/2 + 190);

        // Dibujar todos los hechizos
        for (int i = 0; i < hechizos.length; i++) {
            Color colorTexto = (i == hechizoSeleccionado) ? 
                new Color(50, 255, 50) : new Color(220, 220, 220);
            
            entorno.cambiarFont("Gabriola", 22, colorTexto);
            entorno.escribirTexto(hechizos[i].getNombre() + " (" + (int)hechizos[i].getCostoMana() + " MP)", 
                                x-50, hechizos[i].getY());
        }
    }

    public void verificarClick(int mouseX, int mouseY) {
        double hechizoAncho = 160;
        double hechizoAlto = 40;

        for (int i = 0; i < hechizos.length; i++) {
            if (mouseX >= hechizos[i].getX() - hechizoAncho/2 && 
                mouseX <= hechizos[i].getX() + hechizoAncho/2 &&
                mouseY >= hechizos[i].getY() - hechizoAlto/2 && 
                mouseY <= hechizos[i].getY() + hechizoAlto/2) {
                
                // Deseleccionar todos los hechizos
                for (int j = 0; j < hechizos.length; j++) {
                    hechizos[j].setSeleccionado(false);
                }
                
                // Seleccionar el hechizo clickeado
                hechizos[i].setSeleccionado(true);
                hechizoSeleccionado = i;
                return;
            }
        }
    }

    public Hechizo getHechizoSeleccionado() {
        return (hechizoSeleccionado >= 0) ? hechizos[hechizoSeleccionado] : null;
    }
    
    public void actualizarHechizos() {
        for (Hechizo hechizo : hechizos) {
            hechizo.actualizar();
        }
    }
    
    public void dibujarEfectosHechizos(Entorno entorno) {
        for (Hechizo hechizo : hechizos) {
            hechizo.dibujarEfecto(entorno);
        }
    }
}
