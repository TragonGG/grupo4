package juego;

import java.awt.Color;
import entorno.Entorno;

public class Menu {
    private double x, y, ancho, alto;
    
    public Menu(double x, double y, double ancho, double alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
    }
    
    public void dibujar(Entorno entorno) {
        // Dibujar el fondo del menú en color rojo para ver el tamaño
        entorno.dibujarRectangulo(x, y, ancho, alto, 0, Color.RED);
        
        // Cambiar la fuente y color del texto para el título
        entorno.cambiarFont("Arial", 20, Color.WHITE);
        
        // Escribir "JavaAO" en el medio arriba del menú
        entorno.escribirTexto("JavaAO", x - 30, y - alto/2 + 30);
        
        // Cambiar la fuente para los hechizos
        entorno.cambiarFont("Arial", 16, Color.WHITE);
        
        // Escribir los hechizos uno abajo del otro
        entorno.escribirTexto("Apocalipsis", x - 50, y - alto/2 + 80);
        entorno.escribirTexto("Misil Magico", x - 55, y - alto/2 + 110);
    }
}