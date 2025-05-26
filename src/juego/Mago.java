package juego;

import java.awt.Image;
import entorno.Herramientas;
import entorno.Entorno;

public class Mago {
    private Image[] imagen;
    public double x, y, velocidad, escala, alto, ancho;
    public double bordIz, bordSup, bordDer, bordInf;
    private String direccionActual; // Estado que representa la dirección actual del mago


    public Mago(double x, double y) {
        this.x = x;
        this.y = y;
        this.velocidad = 3;
        this.escala = 0.5;
        this.direccionActual = "derecha"; // Dirección inicial por defecto
        
        this.imagen = new Image[5];
        this.imagen[0] = Herramientas.cargarImagen("juego/img/magoA-V1.gif");
        this.imagen[1] = Herramientas.cargarImagen("juego/img/magoD-V1.gif");
        this.imagen[2] = Herramientas.cargarImagen("juego/img/magoW-V1.gif");
        this.imagen[3] = Herramientas.cargarImagen("juego/img/magoS-V1.gif");
        this.imagen[4] = Herramientas.cargarImagen("juego/img/magoBomba-V1.gif");
        
        this.alto = this.imagen[1].getHeight(null) * this.escala;
        this.ancho = this.imagen[1].getWidth(null) * this.escala;
        
        this.bordIz = this.x - this.ancho / 2;
    	this.bordDer = this.x + this.ancho / 2;
    	this.bordSup = this.y - this.alto / 2;
    	this.bordInf = this.y + this.alto / 2;
        
        
    } 
    
  //metodo para actualizar los bordes al mover el mago
    public void actualizarBordes() {
    	this.bordIz = this.x - this.ancho / 10;
    	this.bordDer = this.x + this.ancho / 10;
    	this.bordSup = this.y - this.alto / 10;
    	this.bordInf = this.y + this.alto / 10;
    }
    
    public void moverIzquierda() {
        this.x -= velocidad;
        this.direccionActual = "izquierda";
        actualizarBordes();
    }
    
    public void moverDerecha() {
        this.x += velocidad;
        this.direccionActual = "derecha";
        actualizarBordes();
    }

    public void moverArriba() {
        this.y -= velocidad;
        this.direccionActual = "arriba";
        actualizarBordes();
    }

    public void moverAbajo() {
        this.y += velocidad;
        this.direccionActual = "abajo";
        actualizarBordes();
    }

    // Getter para obtener la dirección actual
    public String getDireccionActual() {
        return direccionActual;
    }

    // Setter para establecer la dirección explícitamente si es necesario
    public void setDireccionActual(String direccion) {
        this.direccionActual = direccion;
    }
    

    public void dibujar(Entorno entorno) {
        // Ahora solo usamos la dirección actual almacenada en el objeto
        if (direccionActual.equals("abajo")) {
            entorno.dibujarImagen(imagen[3], x, y, 0, escala);
        }
        else if (direccionActual.equals("arriba")) {
            entorno.dibujarImagen(imagen[2], x, y, 0, escala);
        }
        else if (direccionActual.equals("derecha")) {
            entorno.dibujarImagen(imagen[1], x, y, 0, escala);
        }
        else if (direccionActual.equals("izquierda")) {
            entorno.dibujarImagen(imagen[0], x, y, 0, escala);
        }
    }  
    
   //getter para el X del mago
    public double getX() {
        return x;
    }

  //getter para el Y del mago
    public double getY() {
        return y;
    }
}