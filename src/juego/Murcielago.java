package juego;

import java.awt.Image;

public class Murcielago {
	double x;
	double y;
	private Image imagen;
	private double velocidad;
	private double escala;
	
	public Murcielago(double x, double y) {
		int spawnX = (int) (Math.random() * 600);
		this.x= spawnX;
		this.y=y;
	}
}
