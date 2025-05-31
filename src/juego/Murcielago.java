package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Murcielago {
	double x;
	double y;
	double ancho, alto;
	private Image imagen;
	private double velocidad;
	private double escala;
	private boolean vida;
	private boolean primerMovimiento;
	public double bordIz, bordSup, bordDer, bordInf;

	
	
	public Murcielago(double x, double y) {
		   	this.generarPosicionAleatoria();
		   	
		   	this.escala = 0.3;
		    this.velocidad = (Math.random() * 1) + 1;
		    this.imagen = Herramientas.cargarImagen("juego/img/bat.gif");
		    
		    
		    this.primerMovimiento = true;
		    
		    this.vida = true;
		    
		    
	        this.alto = this.imagen.getHeight(null) * this.escala;
	        this.ancho = this.imagen.getWidth(null) * this.escala;
		    
	        this.bordIz = this.x - this.ancho / 10;
	    	this.bordDer = this.x + this.ancho / 10;
	    	this.bordSup = this.y - this.alto / 10;
	    	this.bordInf = this.y + this.alto / 10;

		    
	}
	
	
	public void actualizarBordes() {
    	this.bordIz = this.x - this.ancho / 10;
    	this.bordDer = this.x + this.ancho / 10;
    	this.bordSup = this.y - this.alto / 10;
    	this.bordInf = this.y + this.alto / 10;
    }
    
		 
	
	
	//Dibuja a el Murcielago
	public void dibujar(Entorno entorno) {
		entorno.dibujarImagen(imagen, x, y, 0,escala);
	}
	
	
	
	//Metodo para direccionar al murcielago
	public double[] dirreccionHacia(Mago m) {
	    double dx = m.getX() - this.x;
	    double dy = m.getY() - this.y;

	    // Normalizar vector (dx, dy) para moverse en dirección al mago
	    double distancia = Math.sqrt(dx * dx + dy * dy);

	    if (distancia > 0) {
	        return new double[] {
	            (dx / distancia) * this.velocidad,
	            (dy / distancia) * this.velocidad
	        };
	    }
	    return new double[] {0, 0};
	}
	
		
	//Metodo para mover el murcielago
	public boolean moverHacia(Mago mago, Murcielago[] murcielagos, int murcielagoIndice) {
	    double[] direccion = this.dirreccionHacia(mago);
	    double pasoX = direccion[0];
	    double pasoY = direccion[1];

	    double xOriginal = this.x;
	    double yOriginal = this.y;

	    // Simula el movimiento
	    this.x += pasoX;
	    this.y += pasoY;
	    this.actualizarBordes();

	    if (!colisionConOtrosMurcielagos(murcielagos, murcielagoIndice) || this.isPrimerMovimiento()) {
	        this.marcarMovido();
	        return true;
	    } else {
	        // Revertimos el movimiento
	        this.x = xOriginal;
	        this.y = yOriginal;

	        // Intentamos moverse en una dirección levemente aleatoria
	        double angulo = Math.random() * 2 * Math.PI;
	        double pasoDesvioX = Math.cos(angulo) * this.velocidad * 0.5;
	        double pasoDesvioY = Math.sin(angulo) * this.velocidad * 0.5;

	        this.x += pasoDesvioX;
	        this.y += pasoDesvioY;
	        this.actualizarBordes();

	        // No marcamos como movido aún, pero al menos sale del bloqueo
	        return false;
	    }
	}
	
	//colision entre murcielagos
	private boolean colisionConOtrosMurcielagos(Murcielago[] murcielagos, int miIndice) {
	    for (int i = 0; i < murcielagos.length; i++) {
	        if (i != miIndice && murcielagos[i] != null) {
	            Murcielago otro = murcielagos[i];
	            if (this.seSuperponeCon(otro)) {
	                return true;
	            }
	        }
	    }
	    return false;
	}
	
	
	//Verificacion de colisioens
	private boolean seSuperponeCon(Murcielago otro) {
	    return this.bordDer > otro.bordIz &&
	           this.bordIz < otro.bordDer &&
	           this.bordInf > otro.bordSup &&
	           this.bordSup < otro.bordInf;
	}
	
	
	
	//primer movimiento para que la colision no funciones en el spawn
	public boolean isPrimerMovimiento() {
	    return primerMovimiento;
	}

	public void marcarMovido() {
	    this.primerMovimiento = false;
	}
	
	
	public void reponerMurcielagos(Murcielago[] m) {
		for (int i = 0; i < m.length; i++) {
			if(m[i] == null) {
				m[i] = new Murcielago(100,50);
			}
				
		}
	}
	
	
	private void generarPosicionAleatoria() {
	    int lado = (int) (Math.random() * 4); // 0 = arriba, 1 = abajo, 2 = izquierda, 3 = derecha

	    switch (lado) {
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
	}
	
	public boolean estaCercaDeOtrosMurcielagos(Murcielago[] murcielagos, int miIndice, double distanciaMinima) {
	    for (int i = 0; i < murcielagos.length; i++) {
	        if (i != miIndice && murcielagos[i] != null) {
	            Murcielago otro = murcielagos[i];
	            
	            double dx = this.x - otro.x;
	            double dy = this.y - otro.y;
	            double distancia = Math.sqrt(dx * dx + dy * dy);
	            
	            if (distancia < distanciaMinima) {
	                return true; // Está muy cerca de otro murciélago
	            }
	        }
	    }
	    return false; // No está cerca de ninguno
	}
	
	public void generarPosicionSinSuperposicion(Murcielago[] murcielagos, int miIndice, double distanciaMinima) {
	    boolean posicionValida = false;

	    while (!posicionValida) {
	        generarPosicionAleatoria();
	        this.actualizarBordes();

	        if (!estaCercaDeOtrosMurcielagos(murcielagos, miIndice, distanciaMinima)) {
	            posicionValida = true;
	        }
	    }
	}
	

	
}