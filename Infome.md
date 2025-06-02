//COMENTARIO: Este es el mismo informe que en https://docs.google.com/document/d/18Y7E_6HEi1srjt_WVKj8Ow6lGlJU_O5aZ6e2rlFdEGw/edit?usp=sharing

En este informe mostraremos el desarrollo de nuestro proyecto llamado “El camino de Gondolf”, un videojuego que trata de sobrevivir, a base de rondas, oleadas de murciélagos, donde si tocan a tu personaje estos mismos te quitarán vida e intentan matarte con el 
fin de terminar el juego. El objetivo del juego es poder derrotar a todos los enemigos que te encuentres en tu camino y poder sobrevivir. Para ello, insertamos distintas mecánicas como el sistema de rondas, vida y mana, y un menú para que el jugador pueda ver sus 
estadísticas utilizando la biblioteca Entorno en Java. Este informe describe el proceso de desarrollo, las decisiones tomadas, los desafíos encontrados y las funcionalidades implementadas.

Con respecto al desarrollo de nuestro videojuego, implementamos las siguientes clases, donde dentro de cada una de ellas tienen métodos con sus respectivas tareas.

La clase Juego es la encargada de que el personaje principal sea controlado por el usuario. Dentro de esta clase tiene los siguientes métodos:

colisionPersonaje(): Crea colisión con los bordes del mapa para que el mago no se vaya del area de juego.

colisionPersonajeRoca(): Crea colisión entre el mago y las rocas que hay en el juego para así crear obstáculos dentro del mapa.

colisionMagoMurcielago(): Crea colisión entre el mago y los murcielagos que aparecen en el juego. 

colisionMurcielagos(): Crea colisiones entre los mismos murciélagos para que no se superpongan uno con el otro.

pasarARondaSiguiente(): Detecta cuando el jugador llega a las 10 kills en la primera ronda y luego de esta el objetivo se aumenta en 5 murciélagos más hasta llegar a las ronda 10.

reiniciarJuego(): Método que se ejecuta cuando se detecta que el jugador muere haciendo que vuelva a empezar desde la ronda 1 y reiniciando sus estadísticas. 

eliminarMurcielago(): Esta función convierte null al murciélago del array y genera otro para que haya 10 murciélagos por pantalla.

Además, la clase Juego contiene varias variables como  entorno que representa el entorno gráfico. El objeto mago es la instancia del personaje principal controlado por el usuario, mientras que menu representa el panel lateral donde se muestra información importante 
como la vida, el maná y la ronda actual. La variable fondo es una imagen de fondo que ambienta visualmente el escenario del juego.
Los enemigos estan un array de Murcielago donde son llamados como murcielagos. Por otro lado, los obstáculos del entorno están almacenados en el arreglo Obstaculos, compuesto por rocas . Para manejar las colisiones del mago con estos elementos, se utilizan las 
condiciones booleanas arr, aba, izq y der, que indican si hay colisión en las direcciones arriba, abajo, izquierda o derecha, respectivamente.
En cuanto a las interfaces gráficas del juego, se definen diferentes menús: menuInicial para la pantalla de inicio, menuMuerte para la pantalla de derrota, menuVictoria para cuando el jugador gana, y menuRecompensa, que aparece en la rondas 5 y permite al jugador 
seleccionar una mejora.
Para controlar juego, se usan varias condiciones: juegoIniciado indica si la partida ha comenzado, juegoTerminado si ha finalizado (ya sea por victoria o derrota), juegoEnPausa si se encuentra pausada, y recompensa determina si el jugador ya ha recibido la mejora 
correspondiente en la ronda especial. También se utiliza la variable cooldown como un temporizador de gracia que impide recibir daño continuo en un corto período de tiempo.
Se incluye un contador de murciélagos (contMur) que lleva la cuenta de cuántos enemigos hay en pantalla, y un contador global de muertes llamado killMur, que es estático y permite llevar un registro total de enemigos eliminados durante toda la partida. La condición 
win indica si el jugador ha alcanzado la victoria.
Además, se manejan variables relacionadas con el sistema de rondas: ronda representa el número actual, killsPorRonda define cuántos enemigos deben eliminarse para avanzar, killsEnEstaRonda cuenta cuántas muertes lleva el jugador en la ronda actual, y ticksRonda 
registra el tiempo transcurrido en cada ronda mediante ciclos o "ticks".

	

La clase Mago es la encargada de mostrar la imagen del personaje y darle movimiento con las teclas W, A, S y D. También le otorga vida y mana que podrá utilizar para sus hechizos. Los metodos utilizados fueron:


actualizarBordes(): Es la hitbox del mago.

dibujar(): Dibuja al mago en diferentes direcciones dependiendo de donde esté mirando.

recibirDanio(): Método para cuando reciba daño se le reste vida.

Curar(): Setter que aumenta la vida

estaVivo(): Verifica si el mago tiene la vida mayor a 0, si no es así devuelve falso.

Estos metodos dependen de estas instancias, imagen es un arreglo de imágenes que contiene las distintas apariencias del mago, utilizadas para representar las animaciones según la dirección en la que se mueve.
La posición del mago en pantalla está determinada por las coordenadas x e y, mientras que la variable velocidad define qué tan rápido se desplaza. Las variables escala, ancho y alto se utilizan para ajustar el tamaño visual del mago en pantalla, permitiendo adaptar 
su sprite según las necesidades gráficas del juego.
Para manejar las colisiones con obstáculos u otros elementos del entorno, se definen los bordes bordIz (izquierdo), bordSup (superior), bordDer (derecho) y bordInf (inferior), que permiten detectar si el mago está tocando o está próximo a colisionar con otro objeto.
direccionActual guarda una cadena de texto que indica hacia dónde se está moviendo el mago en ese momento, lo que también sirve para determinar qué imagen mostrar.
Además, sistema de  vida yl maná del personaje. vidaMaxima y vidaActual controlan la salud total y actual del mago, respectivamente, mientras que manaMaximo y manaActual hacen lo mismo pero con el recurso de maná, que generalmente se utiliza para realizar ataques
especiales o habilidades mágicas.


La clase Murciélago se encarga de dibujar y generar los murciélagos, también les da movimiento hacia la dirección del mago y cuando colisionan con él hacen daño.

actualizarBordes(): Es la hitbox de los murcielagos.

moverHacia(): Movimiento de los murciélagos hacia la dirección del mago.

direccionHacia(): Método que direcciona al murciélago hacia la posición del mago.

colisionConOtrosMurcielagos(): Método que detecta la hitbox de un murcielago chocando con otro murciélago lo que hace que no se superpongan uno con el otro.

generarPosicionAleatoria(): Hace que los murciélagos se generen en lugares aleatorios de la pantalla siempre cerca de las paredes.

estaCercaDeOtrosMurcielagos(): Detecta si un murciélago está cerca de otro, si esto es True entonces ese murciélago va aparecer en otro lugar de la pantalla con tal de que no aparezcan tantos murciélagos en un solo lugar.

Las instancias que utiliza la clase Murciélago son x e y que indican la ubicación en pantalla donde aparece cada murciélago. Las variables ancho y alto se utilizan para definir las dimensiones de la imagen.
La variable imagen almacena la imagen que representa gráficamente al murciélago. Su tamaño puede ajustarse mediante la variable escala. La velocidad de movimiento del murciélago está definida por la variable velocidad.
Al igual que con el mago, se definen los bordes bordIz, bordSup, bordDer y bordInf, que permiten calcular y detectar colisiones con otros elementos del juego, como obstáculos o el personaje principal.
La clase Obstáculos tiene la función de generar los obstáculos para el mago, en este caso unas rocas que están en el mapa que obstaculizan el camino del mago impidiendo su paso pero el de los murciélagos no. Esta instancia tiene casi la misma instancia que murcielago, 
siendo bordIz, bordSup, bordDer, bordInf, escala, ancho , alto, x, y e Imagen, todas cumpliendo las mismas funciones.

La clase Hechizo es el encargado de crear los poderes que dispone el jugador y usarlos para derrotar a los murciélagos. Para ello se importó unos GIFs y también le pusimos una hitbox con un radio donde si se detecta que un murciélago está en dentro de ella, el hechizo 
los mata volviendo a los murciélagos null. También se creó un poder para curar al mago.

cargarEfectoVisual(): Carga los Gifs para poder usarlos como hechizos.

ejecutarEn(): Ejecuta el hechizo en la posición X e Y del mouse elegido por el jugador.

eliminarMurcielagosEnArea(): Elimina todos los murciélagos que estén dentro del área de efecto del hechizo. Solo se usa para hechizos ofensivos (APOCALIPSIS y MISIL_MAGICO).

estaEnRango(): Verifica si un punto está dentro del área rectangular de efecto del hechizo.

La clase Hechizo cuenta con las instancias x, y, ancho, alto, como todas las demas pero tambien tiene La variable seleccionado es una condición booleana que indica si el hechizo ha sido elegido por el jugador, costoMana especifica cuántos puntos de maná consume 
el uso del hechizo. El tipo de hechizo está representado por la variable tipo, que es un valor del enumerador TipoHechizo, el cual agrupa las distintas categorías disponibles: APOCALIPSIS, MISIL_MAGICO y CURACION. La variable efectoGif representa la animación o imagen
que se muestra al activarse el hechizo. La condición mostrandoEfecto indica si dicha animación se encuentra actualmente en pantalla, mientras que duracionEfecto determina "ticks" dura ese efecto visual. Las coordenadas efectoX y efectoY definen la posición donde debe 
aparecer el efecto, Por último, radioEfecto indica el área de alcance del hechizo
   

La clase MenuInicial muestra una pantalla de inicio cuando el jugador ejecuta el programa mostrando una imagen con el título del juego y un botón de “Inicio partida”, cuando se detecta que el jugador clickeo en ese botón lo manda al juego.
MenuInicial cuenta con  x, y, ancho, alto igual que las demás pero también tiene fondo representa la imagen de fondo del menú, y boton corresponde al botón principal que permite iniciar la partida. La variable booleana   iniciarSeleccionado funciona como una 
condición para saber si el botón se tocó o no y iniciarX e iniciarY que indican dónde se debe posicionar el botón.

La clase Menu muestra un panel a la derecha de la ventana con las estadísticas del jugador como la vida, maná y kills. También muestra los hechizos disponibles para el jugador como “Apocalipsis” , “Misil Mágico” y “Curación” que servirán para matar a los murciélagos.

verificarClick(): Método que verifica si el jugador clickea en determinas X e Y. Además, se creó un For para poder deseleccionar un hechizo.
Menú cuenta con las instancias,  x, y, ancho, alto, el arreglo hechizos donde se almacenan los hechizos creados, hechizoSeleccionado guarda el índice del hechizo actualmente seleccionado, por defecto esta en -1 siendo este que ninguno está seleccionado, la imagen de 
fondo del menú está representada por la variable fondo, mientras que hechizo es un arreglo de imágenes asociadas a cada hechizo

La clase MenuMuerte muestra una pantalla de “game over” cuando se detecta que el jugador tiene 0 puntos de vida. También tiene la opción de volver a jugar presionando el botón que aparece en pantalla. Contiene las instancias  x, y, ancho, alto, iniciarSeleccionado y 
iniciarX e iniciarY para el botón de reiniciar.

La clase MenuRecompensa muestra en pantalla un menú, al llegar a la ronda 5, donde ofrecerá 3 botones con recompensa, aumento de vida,se aumenta a 200 HP, aumentó durabilidad, es decir la resistencia del personaje haciendo que los murcielagos en vez de quitar 20 
puntos de vida ahora quitaron 10 puntos de vida, o de mana, aumentando a 200 MP. Al momento de entrar a este menú el juego se pausara hasta elegir una recompensa.

clickEnBoton(): Método que verifica si el usuario clickea dentro del botón seleccionado.

verificarClickRecompensa(): Método el cual utiliza	las variables de los distintos botones para verificar que sean pulsados correctamente, si son pulsados correctamente setea lo elegido.
	
reiniciarEstado(): Setter para reiniciar los estados de las recompensas y botones.
Las instancias que cuenta el MenuRecompensa son   escala, ancho , alto, x, y, fondo, iniciarSeleccionado, la condición recompensaElegida indica si el jugador ya eligió una mejora, la condición rDurabilidad señala si la mejora  durabilidad fue obtenida. El menú cuenta 
con tres botones que representan distintas mejoras, están dadas por las variables vidaX y vidaY para la mejora de vida, manaX y manaY para la mejora de maná, y durabilidadX y durabilidadY para la mejora de la durabilidad. botonAncho y botonAlto definen el tamaño 
estándar de cada uno de los botones del menú.

Problemas encontrados: Uno de los problemas al intentar poner un sistema de pociones para que el jugador pueda recuperar vida fue que necesitabas reestructurar el código para obtener los X e Y de los murciélagos muertos.

Otro problemas que tuvimos fue intentar la implementación de más murciélagos en pantalla intentando llegar a que la máxima capacidad de murciélagos sea 50. Intentando implementar esto nos dimos cuenta que necesitábamos reestructurar el sistema de reaparición de los
murciélagos desde 0 debido a que tantos murciélagos causaban conflictos con los métodos que tenemos.

Un inconveniente que tuvimos al principio del proyecto fue intentar implementar animaciones al movimiento del mago mediante la utilización de Gifs. Cuando utilizamos estos Gifs y el jugador tocaba dos teclas al mismo tiempo, las imágenes se superponen entre sí al mismo 
tiempo haciendo que la imagen del mago no se pueda ver con claridad. Para solucionar esto tuvimos que solamente utilizar imágenes PNG para así poder ver las imágenes del mago con claridad.

También tuvimos un inconveniente con poner sonidos en el juego, quisimos poner un sonido de victoria para cuando el jugador supere el juego pero cuando se ejecutaba el sonido este se reproducía varias veces por segundo, lo que lo hacía inaudible.

A la hora de implementar el sistema de mejoras una mejora planeada fue la aumentar la velocidad del personaje, pero esta, al intentar ser puesta en práctica, el setter no aumentaba ni disminuía su velocidad teniendo conflictos con las funciones ya hechas. 
Intentamos buscar una solución analizando el código pero decidimos cambiarla por una mejora que aumenta la durabilidad.

En conclusión:
  Este juego, como mencionamos, se trata de un juego en 2D acerca de un mago, con oleadas de murciélagos, siendo el objetivo sobrevivir a 10 oleadas sin morir. Este proyecto se constituyó de 10 clases, siendo 5 sobre el menú, y las otras 5 sobre los objetos juego. El 
 desarrollo de este videojuego fue un desafío para nosotros, tanto técnicos como de diseño, que nos hicieron pensar en varias soluciones, muchas de ellas funcionaron pero otras, por desgracia, tuvimos que abandonar porque no hallábamos una solución eficiente. 
 A lo largo de este proyecto aprendimos varias lecciones como el de saber estructurar bien las clases para un código más óptimo. El trabajo nos brindó una valiosa experiencia práctica, reforzó nuestra lógica de programación y nos mostró lo desafiante, pero también 
 gratificante, que puede ser crear un juego completo desde cero.
