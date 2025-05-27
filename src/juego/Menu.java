package juego;

import java.awt.Color;
import entorno.Entorno;

public class Menu {
    private double x, y, ancho, alto;
    private Hechizo.Apocalipsis apocalipsis;
    private Hechizo.MisilMagico misilMagico;
    private boolean lanzarSeleccionado;

    // Posición exacta de texto "Lanzar"
    private double lanzarX;
    private double lanzarY;

    public Menu(double x, double y, double ancho, double alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;

        // Crear los hechizos específicos
        this.apocalipsis = new Hechizo.Apocalipsis(x - 25, y - alto/2 + 80);
        this.misilMagico = new Hechizo.MisilMagico(x - 25, y - alto/2 + 110);
        this.lanzarSeleccionado = false;

        // Definir posición del texto Lanzar una sola vez
        this.lanzarX = x - 30;
        this.lanzarY = y - alto/2 + 200;
    }

    public void dibujar(Entorno entorno) {
        // Dibujar el fondo del menú
        entorno.dibujarRectangulo(x, y, ancho, alto, 0, new Color(60, 60, 60));

        // Título
        entorno.cambiarFont("Gabriola", 40, Color.WHITE);
        entorno.escribirTexto("JavaAO", x - 30, y - alto/2 + 30);

        // Dibujar hechizo Apocalipsis
        if (apocalipsis.estaSeleccionado()) {
            entorno.cambiarFont("Gabriola", 20, Color.GREEN);
        } else {
            entorno.cambiarFont("Gabriola", 20, Color.WHITE);
        }
        entorno.escribirTexto("Apocalipsis", x - 50, y - alto/2 + 80);

        // Dibujar hechizo Misil Magico
        if (misilMagico.estaSeleccionado()) {
            entorno.cambiarFont("Gabriola", 20, Color.GREEN);
        } else {
            entorno.cambiarFont("Gabriola", 20, Color.WHITE);
        }
        entorno.escribirTexto("Misil Magico", x - 55, y - alto/2 + 110);

        // Dibujar rectángulo detrás del texto "Lanzar"
        double rectAncho = 100; // Ancho del rectángulo
        double rectAlto = 30;   // Alto del rectángulo
        Color rectColor = new Color(80, 80, 80); // Color del rectángulo
        entorno.dibujarRectangulo(lanzarX, lanzarY, rectAncho, rectAlto, 0, rectColor);

        // Dibujar texto "Lanzar"
        if (lanzarSeleccionado) {
            entorno.cambiarFont("Gabriola", 20, Color.GREEN);
        } else {
            entorno.cambiarFont("Gabriola", 20, Color.WHITE);
        }
        entorno.escribirTexto("Lanzar", lanzarX, lanzarY);
    }


    public void verificarClick(int mouseX, int mouseY) {
        // Verificar si se hizo clic en algún hechizo
        if (apocalipsis.contienePunto(mouseX, mouseY)) {
            misilMagico.setSeleccionado(false);
            apocalipsis.setSeleccionado(true);
        } else if (misilMagico.contienePunto(mouseX, mouseY)) {
            apocalipsis.setSeleccionado(false);
            misilMagico.setSeleccionado(true);
        } else {
            // Verificar si se hizo clic en "Lanzar"
            // Definimos un margen de área clickeable alrededor del texto
            double margenX = 50;
            double margenY = 10;

            if (mouseX >= lanzarX - margenX && mouseX <= lanzarX + margenX &&
                mouseY >= lanzarY - margenY && mouseY <= lanzarY + margenY) {
                lanzarSeleccionado = !lanzarSeleccionado;
            }
        }
    }

    public String getHechizoSeleccionado() {
        if (apocalipsis.estaSeleccionado()) {
            return "Apocalipsis";
        } else if (misilMagico.estaSeleccionado()) {
            return "Misil Magico";
        }
        return null;
    }

    public boolean isLanzarSeleccionado() {
        return lanzarSeleccionado;
    }
}

