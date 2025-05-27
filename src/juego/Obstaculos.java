package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Obstaculos {
	double x,y;
	private Image imagen;
	private double escala, alto, ancho;
	public double bordIz, bordDer, bordSup, bordInf;


	public Obstaculos(double x, double y) {
		this.x = x;
		this.y = y;
		this.escala = 0.5;
		this.imagen = Herramientas.cargarImagen("juego/img/roca.png");
		
		this.alto = this.imagen.getHeight(null) * this.escala;
        this.ancho = this.imagen.getWidth(null) * this.escala;
		
		
		
		
		this.bordIz = this.x - this.ancho / 8;
    	this.bordDer = this.x + this.ancho / 8;
    	this.bordSup = this.y - this.alto / 5;
    	this.bordInf = this.y + this.alto / 10;
		}
	
	
	public void dibujar(Entorno entorno) {
		entorno.dibujarImagen(imagen, x, y, 0, escala);
	}
	
	
}
	
