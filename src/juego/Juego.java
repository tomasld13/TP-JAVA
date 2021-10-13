package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;


public class Juego extends InterfaceJuego {

	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Vikinga vikinga;
	private Image fondo;
	private Rayo rayo;
	private Objetivo objetivo;
	
	public Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Prueba del Entorno", 800, 600);
		vikinga = new Vikinga(65, 50, 5, 30,555,3,0);
		objetivo = new Objetivo(50, 27, 50);
		fondo = Herramientas.cargarImagen("castlebien.png");
		// Inicializar lo que haga falta para el juego
		// ...
		
		// Inicia el juego!
		this.entorno.iniciar();
		
		
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick() {
		
		entorno.dibujarImagen(fondo, entorno.ancho()/2, entorno.alto()/2, 0);
		
		entorno.dibujarRectangulo(entorno.ancho()/2-60, entorno.alto()-100, 700, 10, 0, Color.GRAY);
		entorno.dibujarRectangulo(entorno.ancho()/2+60, entorno.alto()-200, 700, 10, 0, Color.blue);
		entorno.dibujarRectangulo(entorno.ancho()/2-60, entorno.alto()-300, 700, 10, 0, Color.GRAY);
		entorno.dibujarRectangulo(entorno.ancho()/2+60, entorno.alto()-400, 700, 10, 0, Color.blue);
		entorno.dibujarRectangulo(entorno.ancho()/2-60, entorno.alto()-500, 700, 10, 0, Color.GRAY);
		
		entorno.cambiarFont("sans",20,Color.white);
		entorno.escribirTexto("Vidas: " + vikinga.getVidas() + " Puntos: 8", entorno.ancho()-200, 22);
		
		objetivo.dibujarObjetivo(entorno);
		vikinga.dibujarVikinga(entorno);
		
		if(rayo!=null) {
			rayo.dibujarRayo(entorno);
			rayo.ida();
		if(rayo.getX() > entorno.ancho() || rayo.getX() < 0) {
			rayo=null;
		}
		}
		vikinga.quePiso();
		vikinga.caer(entorno);
		
		if(entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
			vikinga.moverHaciaIzquierda(entorno);
		}
		if(entorno.estaPresionada(entorno.TECLA_DERECHA)) {
			vikinga.moverHaciaDerecha(entorno);
		}
		if(entorno.estaPresionada('u')) {
			vikinga.subir(entorno);
		}
		
		if(entorno.estaPresionada(entorno.TECLA_ESPACIO) && rayo == null) {
			rayo = new Rayo(20, 20, vikinga.getX(), vikinga.getY(), 5,vikinga.getPiso());
		}

		// Procesamiento de un instante de tiempo
		// ...
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}
