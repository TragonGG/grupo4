package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Murcielago {
	double x;
	double y;
	private Image imagen;
	private double velocidad;
	private double escala;
	private boolean vida;
	
	public Murcielago(double x, double y) {
		   int lado =(int) (Math.random() * 4); // 0 = arriba, 1 = abajo, 2 = izquierda, 3 = derecha

		    switch (lado) // SPAWN DE LOS MURCIELAGOS
		    			{
		        case 0: // Arriba
		            this.x = Math.random() * 900;
		            this.y = 0;
		            break;
		        case 1: // Abajo
		            this.x = Math.random() * 900;
		            this.y = 780;
		            break;
		        case 2: // Izquierda
		            this.x = 0;
		            this.y = Math.random() * 780;
		            break;
		        case 3: // Derecha
		            this.x = 900;
		            this.y = Math.random() * 800;
  		            break;
		    }

		    this.escala = 0.3;
		    this.velocidad = 1.5;
		    this.imagen = Herramientas.cargarImagen("juego/img/bat.gif");
		    
	}
		 
	
	public void dibujar(Entorno entorno) {
		entorno.dibujarImagen(imagen, x, y, 0,escala);
	}
	
	public void mover(Mago m) {
	    double dx = m.getX() - this.x;
	    double dy = m.getY() - this.y;

	    // Normalizar vector (dx, dy) para moverse en dirección al mago
	    double distancia = Math.sqrt(dx * dx + dy * dy);

	    if (distancia > 0) {
	        this.x += (dx / distancia) * this.velocidad;
	        this.y += (dy / distancia) * this.velocidad;
	    }
	}
}