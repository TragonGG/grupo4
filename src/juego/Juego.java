package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;
public class Juego extends InterfaceJuego {
	
    private Entorno entorno;
    private Mago mago; // Instancia de Mago
    private Menu menu;
    private Image fondo; // Instancia de Fondo
    private Murcielago[] murcielagos;
    private Obstaculos[] rocas;
    private boolean arr, aba, izq, der; // Condiciones de verificacion
    private MenuInicial menuInicial;
    private MenuMuerte menuMuerte;
    private MenuVictoria menuVictoria;
    private MenuRecompensa menuRecompensa;
    private boolean juegoIniciado = false;
    private boolean juegoTerminado = false;
    private boolean juegoEnPausa = false;
    private boolean recompensa = false; // Condicion si se obtuvo recompensa
    private int cooldown; //Periodo de gracia para no recibir daño
    private int contMur; //contador de murcielagos
    public static int killMur; // Contador de muertes 
    private boolean win = false;
    private int ronda; // Contador de ronda en la que vas
    private int killsPorRonda; //Kills necesarias para avanzar de ronda
    private int killsEnEstaRonda; //Kills que llevas en la ronda
    private int ticksRonda; // Contador de ticks


    Juego() {
        this.entorno = new Entorno(this, "Proyecto para TP", 1200, 800);
        this.mago = new Mago(400, 300); // 
        this.fondo = Herramientas.cargarImagen("juego/img/fondojuego.png"); //Carga el fondo

        
        this.murcielagos = new Murcielago[10];
        	if(contMur<=10)
        		{for (int i = 0; i < murcielagos.length;i++) {
        			this.murcielagos[i] = new Murcielago(100,50);
        			murcielagos[i].generarPosicionSinSuperposicion(murcielagos, i, 75);
        			contMur ++;
        			}
        		}
       
        this.cooldown = 0;
        this.ticksRonda = 100;
        
        this.ronda = 1;
        this.killsPorRonda = 10;
        this.killsEnEstaRonda = 0;
      
        this.menuInicial = new MenuInicial(600, 400, 1200, 800);
        this.menuMuerte = new MenuMuerte(600, 400, 1200, 800);
        this.menuVictoria = new MenuVictoria(600, 400, 1200, 800);
        this.menuRecompensa = new MenuRecompensa(600, 400, 1200, 800);

        
        rocas = new Obstaculos[5];
        int[] posX = {200, 200, 450, 700, 700};
        int[] posY = {600, 200, 400, 200, 600};
        for (int i = 0; i < rocas.length; i++) {
            rocas[i] = new Obstaculos(posX[i], posY[i]);
        }

        // menu
        this.menu = new Menu(1060, 400, 299, 800);

        //colisiones
        this.arr = false;
        this.izq = false;
        this.der = false;
        this.aba = false;
   
        this.entorno.iniciar();
    }
    
    public void tick() {    	
    	   if (!juegoIniciado) {   		   
    	       // MENU INICIAL 
    	       menuInicial.dibujar(entorno);
    	       
    	       if (entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {
    	           menuInicial.verificarClick(entorno.mouseX(), entorno.mouseY());
    	           if (menuInicial.isIniciarSeleccionado()) {
    	               juegoIniciado = true;   	               
    	           }   	          
    	       }
    	       return;
    	   }     	   
    	   
    	// MENU DE RECOMPENSA - se activa solo en ronda 5
    	   if (ronda == 5 && !recompensa && mago.estaVivo() && !juegoTerminado) {
    	       juegoEnPausa = true;
    	       menuRecompensa.dibujar(entorno);

    	       if (entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {
    	           menuRecompensa.verificarClickRecompensa(entorno.mouseX(), entorno.mouseY(), mago); 	          
    	           recompensa = true; // Se marca como usada la recompensa
	               juegoEnPausa = false; 
	               ticksRonda = 0; // Da tiempo antes de que aparezcan enemigos
	               ronda = 4; 
	               pasarARondaSiguiente(); //Reinicio de ronda N5
    	       }
    	       return; // Importante: evita que se dibuje el juego mientras está este menú
    	   }
    	     	   
    	   //JUEGO
    	   if (mago.estaVivo() && !juegoTerminado && win == false) {
    	       // Procesamiento de un instante de tiempo
    	       entorno.dibujarImagen(fondo, 311, 400, 0, 1); // Dibuja el fondo 1
    	       menu.dibujar(entorno, mago);
    	       mago.regenerarMana();    	       
    	       boolean clickEnMenu = false;
    	       
    	       //MENU DE HECHIZOS
    	       if (entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {
    	    	    // Detectar si se hizo clic dentro del menú
    	    	    if (entorno.mouseX() >= 1060) {
    	    	        clickEnMenu = true;
    	    	        menu.verificarClick(entorno.mouseX(), entorno.mouseY());
    	    	    } else {
    	    	        // Si hay hechizo seleccionado y se clickea fuera del menú, se lanza
    	    	        Hechizo hechizoSeleccionado = menu.getHechizoSeleccionado();
    	    	        if (hechizoSeleccionado != null) {
    	    	            boolean hechizoEjecutado = hechizoSeleccionado.ejecutarEn(mago, entorno.mouseX(), entorno.mouseY(), murcielagos, this);
    	    	            if (hechizoEjecutado) {
    	    	                menu.deseleccionarHechizo();
    	    	            }
    	    	        }
    	    	    }
    	    	}
    	    	 
    	       menu.actualizarHechizos();
    	       
    	       menu.dibujarEfectosHechizos(entorno);
    	       
    	       //Metodo de colisiones
    	       this.colisionPersonaje(mago);
    	       
    	       //Dibuja las rocas
    	       for (int i = 0; i < rocas.length; i++) {
    	           rocas[i].dibujar(entorno);	
    	           this.colisionPersonajeRoca(mago, rocas[i]);
    	       }
    	       
    	       //RONDAS
    		   entorno.cambiarFont(null, 20, Color.WHITE);
    		   entorno.escribirTexto("Ronda: " + ronda, 10, 30);
    	      
    		   
    		   // Lógica de movimiento con prioridad de teclas
    	       // Si se presionan múltiples teclas, la última comprobada tendrá prioridad
    	       boolean seMueve = false;
    	    	       
    	       if(juegoEnPausa == false){
   
    	    	   if (entorno.estaPresionada('W') && !this.arr) { // Tecla W para mover arriba
    	    		   mago.moverArriba();
    	    		   seMueve = true;
    	    	   }
    	    	   if (entorno.estaPresionada('S') && !this.aba) { // Tecla S para mover abajo
    	    		   mago.moverAbajo();
    	    		   seMueve = true;
    	    	   }
    	    	   if (entorno.estaPresionada('A') && !this.izq) { // Tecla A para mover izquierda
    	    		   mago.moverIzquierda();
    	    		   seMueve = true;
    	    	   }
    	    	   if (entorno.estaPresionada('D') && !this.der) { // Tecla D para mover derecha
    	    		   mago.moverDerecha();
    	    		   seMueve = true;
    	    	   }
    	    	   
    	    	   // Dibuja el mago una sola vez con su dirección actual
    	    	   mago.dibujar(entorno);

    	    	   //TIMERS
    	    	   if (cooldown > 0) 
    	    		   cooldown--;
    	       
    	    	   if (ticksRonda >0 && ronda <10){
    	    		   ticksRonda--;
    	    		   entorno.cambiarFont("Gabriola", 40, Color.RED);
    	    		   entorno.escribirTexto("Preparate para los enemigos", 250,150);
    	    	   	} else if(ticksRonda>0 && ronda == 10) {
    	    	   		ticksRonda--;
    	    	   		entorno.cambiarFont("Gabriola", 40, Color.RED);
    	    	   		entorno.escribirTexto("Preparate para la ultima ronda", 250,150);
    	    	   	}
    	           	       
    	    	   // MURCIELAGO - MOVIMIENTO, ELIMINACION, CREACION DE NUEVOS MURC, COLISIONES.
    	    	   if(ronda <= 11 && ticksRonda == 0 && juegoEnPausa == false)  // condicion para el respawn de los murcielagos
    	       			{for (int i = 0; i < murcielagos.length; i++) { 
    	       				Murcielago m = murcielagos[i];
    	            
    	       				if (m == null) {
	    	            	   	  	            		
	    	            		murcielagos[i] = new Murcielago(100, 50);
	    	            		murcielagos[i].generarPosicionSinSuperposicion(murcielagos, i, 50);
	    	            		contMur++;
	    	            		continue; // Si el murciélago es null, salta a la siguiente iteración
    	       				}
    	            
    	       				m.moverHacia(mago, murcielagos, i);
    	            
    	       				// Verificar colisión con el mago
    	       				if (colisionMagoMurcielago(mago, murcielagos[i]) && cooldown == 0) {
    	       					
    	       					if(menuRecompensa.rDurabilidad == false) 
    	       						{mago.recibirDanio(20);
    	       					}else {mago.recibirDanio(10);}
    	       					
    	       					cooldown = 60; // tick para recibir daño devuelta
    	       					eliminarMurcielago(i);  	       				
    	       					continue; // Salir del bucle para evitar dibujar el murciélago eliminado
    	       				}
    	            
    	       				murcielagos[i].dibujar(entorno);
    	       			}
    	       		} 
    	       }   	       
    	       
    	       //Asigna el booleano para las colisiones
    	       this.arr = false;
    	       this.izq = false;
    	       this.der = false;
    	       this.aba = false; 	       
               }
    	   
    	   //DETECTA SI ES UNA VICTORIA
    	   if (ronda > 10 && mago.estaVivo() && !juegoTerminado ) {
    		   			win =true;		
    		   			menuVictoria.dibujar(entorno);    
    		   			if (entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {
    		    	           menuVictoria.verificarClick(entorno.mouseX(), entorno.mouseY());
    		    	           if (menuVictoria.isIniciarSeleccionado()) {
    		    	               juegoIniciado = true;
    		    	               reiniciarJuego();
    		    	               }
    		    	           menuVictoria.verificarClickSalir(entorno.mouseX(), entorno.mouseY());
    		   			}
                               
           //DETECTA SI EL MAGO MUERE
           } else if(!mago.estaVivo()) {
               juegoTerminado = true;
               menuMuerte.dibujar(entorno);
               if (entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {
    	           menuMuerte.verificarClick(entorno.mouseX(), entorno.mouseY());
    	           if (menuMuerte.isIniciarSeleccionado()) {
    	               juegoIniciado = true;
    	               reiniciarJuego();
               
    	           }	
               }
           }
    }       
    //COLISIONES
 
    public void colisionPersonaje(Mago m) {
        // Borde izquierdo
        if (Math.abs(m.bordIz - 0) < 2) {
            this.izq = true;
        }

        // Borde superior
        if (Math.abs(m.bordSup - 0) < 2) {
            this.arr = true;
        }

        // Borde derecho - dejando espacio de 200px para hechizos
        if (Math.abs(m.bordDer - (entorno.ancho() - 280)) < 2) {
            this.der = true;
        }

        // Borde inferior
        if (Math.abs(m.bordInf - entorno.alto()) < 15) {
            this.aba = true;
        }
    }
    
    public void colisionPersonajeRoca(Mago m, Obstaculos r) {
        boolean colision = 
            m.bordDer > r.bordIz &&
            m.bordIz < r.bordDer &&
            m.bordInf > r.bordSup &&
            m.bordSup < r.bordInf;

        if (colision) {
            if (Math.abs(m.bordDer - r.bordIz) < 5) this.der = true;
            if (Math.abs(m.bordIz - r.bordDer) < 5) this.izq = true;
            if (Math.abs(m.bordInf - r.bordSup) < 5) this.aba = true;
            if (Math.abs(m.bordSup - r.bordInf) < 5) this.arr = true;
        }
    }
    
    public boolean colisionMagoMurcielago(Mago mago, Murcielago m) {
        return mago.bordDer > m.bordIz &&
               mago.bordIz < m.bordDer &&
               mago.bordInf > m.bordSup &&
               mago.bordSup < m.bordInf;
    }
    
 
    public boolean colisionMurcielagos(Murcielago[] murcielagos, int indice) {
  		//Primer murcielago a comparar
    	Murcielago n1 = murcielagos[indice];
  		
  		for(int i = 0; i<murcielagos.length; i++) {
  			if (i != indice) {
  				//Segundo murcielago a comparar
  				Murcielago n2 = murcielagos[i];
  				
  				//verifica los bordes de los murcielagos
  				boolean colisiona = 
  		                n1.bordDer > n2.bordIz && //
  		                n1.bordIz < n2.bordDer &&
  		                n1.bordInf > n2.bordSup &&
  		                n1.bordSup < n2.bordInf;

  		            if (colisiona) {
  		                return true;
  		            }
  			}
  		}
  		return false;
  	}

    //Metodos Menu
    
    private void pasarARondaSiguiente() {
        ronda++;
        killsEnEstaRonda = 0;
        contMur = murcielagos.length;

        for (int i = 0; i < murcielagos.length; i++) {
            murcielagos[i] = new Murcielago(100, 50);
            murcielagos[i].generarPosicionSinSuperposicion(murcielagos, i, 175);
        }
        killsPorRonda += 5;    
}
    
    private void reiniciarJuego() {
        this.mago = new Mago(400, 300); //Reinicio de las instancias influyentes en la logica y jugabilidad
        this.cooldown = 0;
        this.juegoTerminado = false;
        this.win = false;
        killMur = 0;
        killsEnEstaRonda = 0;
        killsPorRonda = 10;
        ronda = 1;
        recompensa = false; 
        menuRecompensa.reiniciarEstado(); //setter para los estado del menuRecompensa
        
        // Reset murciélagos
        for (int i = 0; i < murcielagos.length; i++) {
            murcielagos[i] = new Murcielago(100, 50);
            murcielagos[i].generarPosicionSinSuperposicion(murcielagos, i, 75);
        }
        menuVictoria.deseleccionar();
        contMur = murcielagos.length;
    }
    	
    //Metodos eliminaciones
    public void eliminarMurcielago(int indice) {
    	if (indice >= 0 && indice < murcielagos.length && murcielagos[indice] != null) {
        murcielagos[indice] = null; // Elimina el murciélago
        contMur--; // Baja el contador de Murcielagos vivos
        killMur++; // Sube el contador de kills   	
        killsEnEstaRonda++;
        
        if (killsEnEstaRonda >= killsPorRonda && ticksRonda == 0) {
            pasarARondaSiguiente();
            ticksRonda = 100;
        }
      }
    }    
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        Juego juego = new Juego();
    }
}