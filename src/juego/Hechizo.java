package juego;

public class Hechizo {
    private String nombre;
    private double x, y, ancho, alto;
    private boolean seleccionado;
    private double costoMana; 
    
    public Hechizo(String nombre, double x, double y, double ancho, double alto, double costoMana) {
        this.nombre = nombre;
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.seleccionado = false;
        this.costoMana = costoMana;
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
    
    public double getCostoMana() {
        return costoMana;
    }
    
    public boolean contienePunto(double mouseX, double mouseY) {
        return mouseX >= (x - ancho/2) && mouseX <= (x + ancho/2) &&
               mouseY >= (y - alto/2) && mouseY <= (y + alto/2);
    }
    
    public double getX() { return x; }
    public double getY() { return y; }
    public double getAncho() { return ancho; }
    public double getAlto() { return alto; }
    
    // Método para ejecutar el hechizo (a implementar según la lógica específica)
    public void ejecutar(Mago mago) {
        if (mago.usarMana(costoMana)) {
            
            System.out.println("Ejecutando " + nombre + " - Mana usado: " + costoMana);
        } else {
            System.out.println("No hay suficiente mana para " + nombre);
        }
    }
    
    // Hechizo específico: Apocalipsis
    public static class Apocalipsis extends Hechizo {
        public Apocalipsis(double x, double y) {
            super("Apocalipsis", x, y, 120, 25, 50); // Cuesta 50 de mana
        }
        
        @Override
        public void ejecutar(Mago mago) {
            if (mago.usarMana(getCostoMana())) {
                // Lógica específica del Apocalipsis
                System.out.println("¡APOCALIPSIS DESATADO! Daño masivo en área");
                // Aquí podrías agregar efectos visuales, daño a enemigos, etc.
            } else {
                System.out.println("No hay suficiente mana para Apocalipsis");
            }
        }
    }
    
    // Hechizo específico: Misil Mágico
    public static class MisilMagico extends Hechizo {
        public MisilMagico(double x, double y) {
            super("Misil Magico", x, y, 120, 25, 20); // Cuesta 20 de mana
        }
        
        @Override
        public void ejecutar(Mago mago) {
            if (mago.usarMana(getCostoMana())) {
                // Lógica específica del Misil Mágico
                System.out.println("¡Misil Mágico lanzado! Proyectil dirigido");
              
            } else {
                System.out.println("No hay suficiente mana para Misil Mágico");
            }
        }
    }
}