package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {

	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Escalera escaUno;
	private Escalera escaDos;
	private Escalera escaTres;
	private Escalera escaCuatro;
	private Escalera escaCinco;
	private Vikinga vikinga;
	private Velociraptor velociraptor;
	private Image fondo;
	private Rayo rayo;
	private Objetivo objetivo;
	

	public Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Prueba del Entorno", 800, 600);
		vikinga = new Vikinga(65, 50, 5, 30, 555, 5, 9, 0, false);
		velociraptor = new Velociraptor(100,entorno.alto() - 150,3);
		objetivo = new Objetivo(50, 27, 50);
		escaUno = new Escalera(entorno.ancho() / 2 - 60, entorno.alto() - 100);
		escaDos = new Escalera (entorno.ancho() / 2 + 60, entorno.alto() - 200);
		escaTres = new Escalera (entorno.ancho() / 2 - 60, entorno.alto() - 300);
		escaCuatro = new Escalera (entorno.ancho() / 2 + 60, entorno.alto() - 400);
		escaCinco = new Escalera (entorno.ancho() / 2 - 60, entorno.alto() - 500);
		fondo = Herramientas.cargarImagen("castlebien.png");
		// Inicializar lo que haga falta para el juego
		// ...

		// Inicia el juego!
		this.entorno.iniciar();

	}

	/**
	 * Durante el juego, el mÃ©todo tick() serÃ¡ ejecutado en cada instante y por lo
	 * tanto es el mÃ©todo mÃ¡s importante de esta clase. AquÃ­ se debe actualizar
	 * el estado interno del juego para simular el paso del tiempo (ver el enunciado
	 * del TP para mayor detalle).
	 */
	public void tick() {

		entorno.dibujarImagen(fondo, entorno.ancho() / 2, entorno.alto() / 2, 0);
		
		escaUno.dibujarEscalera(entorno);
		escaDos.dibujarEscalera(entorno);
		escaTres.dibujarEscalera(entorno);
		escaCuatro.dibujarEscalera(entorno);
		escaCinco.dibujarEscalera(entorno);
		
		
		entorno.cambiarFont("sans", 20, Color.white);
		entorno.escribirTexto("Vidas: " + vikinga.getVidas() + " Puntos: 8", entorno.ancho() - 200, 22);

		objetivo.dibujarObjetivo(entorno);
		vikinga.dibujarVikinga(entorno);
		// Velociraptor.dibujarVelociraptor(entorno);

		if (rayo != null) {
			rayo.dibujarRayo(entorno);
			rayo.ida();
			if (rayo.getX() > entorno.ancho() || rayo.getX() < 0) {
				rayo = null;
			}
		}
		vikinga.quePiso();
		vikinga.caer(entorno);

		if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
			vikinga.moverHaciaIzquierda(entorno);
		}
		if (entorno.estaPresionada(entorno.TECLA_DERECHA)) {
			vikinga.moverHaciaDerecha(entorno);
		}
		if (entorno.estaPresionada('u') ) {
			vikinga.saltar(entorno);   							 // salto
			
		}

		if (entorno.estaPresionada(entorno.TECLA_ESPACIO) && rayo == null) {
			rayo = new Rayo(20, 20, vikinga.getX(), vikinga.getY(), 5, vikinga.getPiso());
		}
		
		//Raptor
		
		velociraptor.dibujarRaptor(entorno);
		
		velociraptor.mover();
		
		if (velociraptor.chocasteEntorno(entorno)) {
			velociraptor.cambiarDeDireccion();
		}

		// Procesamiento de un instante de tiempo
		// ...
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}
