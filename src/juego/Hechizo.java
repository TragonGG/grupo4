package juego;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Hechizo {
    public String nombre;
    public double x, y, ancho, alto;
    public boolean seleccionado;
    public double costoMana;
    public TipoHechizo tipo;
    
    // Sistema de efectos visuales
    public Image efectoGif;
    public boolean mostrandoEfecto;
    public int duracionEfecto;
    public double efectoX, efectoY;
    
    public enum TipoHechizo {
        APOCALIPSIS, MISIL_MAGICO, CURACION
    }
    
    public Hechizo(String nombre, double x, double y, double ancho, double alto, double costoMana, TipoHechizo tipo) {
        this.nombre = nombre;
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.seleccionado = false;
        this.costoMana = costoMana;
        this.tipo = tipo;
        this.mostrandoEfecto = false;
        this.duracionEfecto = 0;
        
        // Cargar imagen del efecto según el tipo
        cargarEfectoVisual();
    }
    
    private void cargarEfectoVisual() {
        switch (tipo) {
            case APOCALIPSIS:
                this.efectoGif = Herramientas.cargarImagen("juego/img/apocalipsis.gif");
                break;
            case MISIL_MAGICO:
                this.efectoGif = Herramientas.cargarImagen("juego/img/MisilMagico.gif");
                break;
            case CURACION:
                this.efectoGif = Herramientas.cargarImagen("juego/img/curacion.gif");
                break;
        }
    }
    
    public void ejecutarEn(Mago mago, double targetX, double targetY) {
        if (!mago.usarMana(costoMana)) {
            System.out.println("No hay suficiente mana para " + nombre);
            return;
        }
        
        // Lógica específica según el tipo de hechizo
        switch (tipo) {
            case APOCALIPSIS:
                System.out.println("¡APOCALIPSIS DESATADO! Daño masivo en área");
                iniciarEfecto(targetX, targetY, 60);
                break;
                
            case MISIL_MAGICO:
                System.out.println("¡Misil Mágico lanzado! Proyectil dirigido");
                iniciarEfecto(targetX, targetY, 30);
                break;
                
            case CURACION:
                System.out.println("¡Curación activada! Restaurando vida");
                mago.curar(30);
                iniciarEfecto(mago.getX(), mago.getY(), 40);
                break;
               
        }
    }
    
    private void iniciarEfecto(double x, double y, int duracion) {
        this.mostrandoEfecto = true;
        this.duracionEfecto = duracion;
        this.efectoX = x;
        this.efectoY = y;
    }
    
    public void actualizar() {
        if (mostrandoEfecto) {
            duracionEfecto--;
            if (duracionEfecto <= 0) {
                mostrandoEfecto = false;
            }
        }
    }
    
    public void dibujarEfecto(Entorno entorno) {
        if (mostrandoEfecto && efectoGif != null) {
            entorno.dibujarImagen(efectoGif, efectoX, efectoY, 0, 1);
        }
    }
    
    // Getters y setters
    public boolean estaSeleccionado() { return seleccionado; }
    public void setSeleccionado(boolean seleccionado) { this.seleccionado = seleccionado; }
    public String getNombre() { return nombre; }
    public double getCostoMana() { return costoMana; }
    public double getX() { return x; }
    public double getY() { return y; }
    public double getAncho() { return ancho; }
    public double getAlto() { return alto; }
    public TipoHechizo getTipo() { return tipo; }
    public boolean estaEjecutandose() { return mostrandoEfecto; }
    
    public boolean contienePunto(double mouseX, double mouseY) {
        return mouseX >= (x - ancho/2) && mouseX <= (x + ancho/2) &&
               mouseY >= (y - alto/2) && mouseY <= (y + alto/2);
    }
}