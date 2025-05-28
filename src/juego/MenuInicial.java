
package juego;

import java.awt.Color;
import entorno.Entorno;

public class MenuInicial {
    private double x, y, ancho, alto;
    private boolean iniciarSeleccionado;

    // Posición exacta del botón "Iniciar partida"
    private double iniciarX;
    private double iniciarY;

    public MenuInicial(double x, double y, double ancho, double alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;

        this.iniciarSeleccionado = false;

        // Definir posición del botón "Iniciar partida"
        this.iniciarX = x;
        this.iniciarY = y + 50;
    }

    public void dibujar(Entorno entorno) {
        // Dibujar el fondo del menú inicial (pantalla completa)
        entorno.dibujarRectangulo(x, y, ancho, alto, 0, new Color(60, 60, 60));

        // Título "JavaAO"
        entorno.cambiarFont("Gabriola", 80, Color.WHITE);
        entorno.escribirTexto("JavaAO", x - 80, y - 100);

        // Dibujar rectángulo detrás del botón "Iniciar partida"
        double rectAncho = 200; // Ancho del rectángulo
        double rectAlto = 50;   // Alto del rectángulo
        Color rectColor = new Color(80, 80, 80); // Color del rectángulo
        entorno.dibujarRectangulo(iniciarX, iniciarY, rectAncho, rectAlto, 0, rectColor);

        // Dibujar texto "Iniciar partida"
        if (iniciarSeleccionado) {
            entorno.cambiarFont("Gabriola", 30, Color.GREEN);
        } else {
            entorno.cambiarFont("Gabriola", 30, Color.WHITE);
        }
        entorno.escribirTexto("Iniciar partida", iniciarX - 60, iniciarY);
    }

    public void verificarClick(int mouseX, int mouseY) {
        // Verificar si se hizo clic en "Iniciar partida"
        // Definimos un margen de área clickeable alrededor del botón
        double margenX = 100;
        double margenY = 25;

        if (mouseX >= iniciarX - margenX && mouseX <= iniciarX + margenX &&
            mouseY >= iniciarY - margenY && mouseY <= iniciarY + margenY) {
            iniciarSeleccionado = !iniciarSeleccionado;
        }
    }

    public boolean isIniciarSeleccionado() {
        return iniciarSeleccionado;
    }
}