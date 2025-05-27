package juego;

public class Hechizo {
    private String nombre;
    private double x, y, ancho, alto;
    private boolean seleccionado;
    
    public Hechizo(String nombre, double x, double y, double ancho, double alto) {
        this.nombre = nombre;
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.seleccionado = false;
    }
    
    public boolean estaSeleccionado() {
        return seleccionado;
    }
    
    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public boolean contienePunto(double mouseX, double mouseY) {
        return mouseX >= (x - ancho/2) && mouseX <= (x + ancho/2) &&
               mouseY >= (y - alto/2) && mouseY <= (y + alto/2);
    }
    
    public double getX() { return x; }
    public double getY() { return y; }
    public double getAncho() { return ancho; }
    public double getAlto() { return alto; }
    
    // Hechizo específico: Apocalipsis
    public static class Apocalipsis extends Hechizo {
        public Apocalipsis(double x, double y) {
            super("Apocalipsis", x, y, 120, 25);
        }
        
        // Aquí puedes agregar parámetros específicos del Apocalipsis
        // Por ejemplo: daño, área de efecto, etc.
    }
    
    // Hechizo específico: Misil Mágico
    public static class MisilMagico extends Hechizo {
        public MisilMagico(double x, double y) {
            super("Misil Magico", x, y, 120, 25);
        }
        
        // Aquí puedes agregar parámetros específicos del Misil Mágico
        
    }
}