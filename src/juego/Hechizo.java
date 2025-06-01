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
    
    // Sistema de área de efecto
    public double radioEfecto;
    
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
        
        // Definir radio de efecto según el tipo
        switch (tipo) {
            case APOCALIPSIS:
                this.radioEfecto = 150; // Área grande
                break;
            case MISIL_MAGICO:
                this.radioEfecto = 80; // Área mediana
                break;
            case CURACION:
                this.radioEfecto = 0; // Sin área de efecto ofensiva
                break;
        }
        
        // Cargar imagen del efecto según el tipo
        cargarEfectoVisual();
    }
    
    private void cargarEfectoVisual() {
        switch (tipo) {
            case APOCALIPSIS:
                this.efectoGif = Herramientas.cargarImagen("juego/img/apocalipsis-v1.gif");
                break;
            case MISIL_MAGICO:
                this.efectoGif = Herramientas.cargarImagen("juego/img/MisilMagico-v1.gif");
                break;
            case CURACION:
                this.efectoGif = Herramientas.cargarImagen("juego/img/curacion.gif");
                break;
        }
    }
    
    public boolean ejecutarEn(Mago mago, double targetX, double targetY, Murcielago[] murcielagos, Juego juego) {
        if (!mago.usarMana(costoMana)) {
            System.out.println("No hay suficiente mana para " + nombre);
            return false;
        }
        
        // Lógica específica según el tipo de hechizo
        switch (tipo) {
            case APOCALIPSIS:
                System.out.println("¡APOCALIPSIS DESATADO! Daño masivo en área");
                iniciarEfecto(targetX, targetY, 60);
                eliminarMurcielagosEnArea(targetX, targetY, murcielagos, juego);
                break;
                
            case MISIL_MAGICO:
                System.out.println("¡Misil Mágico lanzado! Proyectil dirigido");
                iniciarEfecto(targetX, targetY, 30);
                eliminarMurcielagosEnArea(targetX, targetY, murcielagos, juego);
                break;
                
            case CURACION:
                System.out.println("¡Curación activada! Restaurando vida");
                mago.curar(30);
                iniciarEfecto(mago.getX(), mago.getY(), 40);
                break;
        }
        
        return true; // Hechizo ejecutado exitosamente
    }
    
    private void eliminarMurcielagosEnArea(double centroX, double centroY, Murcielago[] murcielagos, Juego juego) {
        for (int i = 0; i < murcielagos.length; i++) {
            if (murcielagos[i] != null) {
                double distancia = calcularDistancia(centroX, centroY, murcielagos[i].x, murcielagos[i].y);
                
                if (distancia <= radioEfecto) {
                    System.out.println("¡Murciélago eliminado por " + nombre + "!");
                    juego.eliminarMurcielagoPublico(i);
                }
            }
        }
    }
    
    private double calcularDistancia(double x1, double y1, double x2, double y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        return Math.sqrt(dx * dx + dy * dy);
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
            entorno.dibujarImagen(efectoGif, efectoX, efectoY, 0, 0.4);
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
    public double getRadioEfecto() { return radioEfecto; }
    
    public boolean contienePunto(double mouseX, double mouseY) {
        return mouseX >= (x - ancho/2) && mouseX <= (x + ancho/2) &&
               mouseY >= (y - alto/2) && mouseY <= (y + alto/2);
    }
}