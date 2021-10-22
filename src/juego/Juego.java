package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {

	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;

	private Piso[] pisos = new Piso[5];

	private Vikinga vikinga;

	private Velociraptor raptor;
	private Velociraptor raptor2;
	private Velociraptor raptor3;
	private Velociraptor raptor4;

	// private Velociraptor[] raptors;

	private Image fondo;
	private Rayo rayo;
	private Objetivo objetivo;

	private boolean vuelta;

	private int puntaje; // y también vidas? vidas--

	public Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Prueba del Peronismo", 800, 600);
		vikinga = new Vikinga(65, 50, 5, 30, 555, 5, 9, 0, false);

		raptor = new Velociraptor(100, entorno.alto() - 150, 2);
		raptor2 = new Velociraptor(200, entorno.alto() - 250, 2);
		raptor3 = new Velociraptor(100, entorno.alto() - 350, 2);
		raptor4 = new Velociraptor(200, entorno.alto() - 450, 2);

		objetivo = new Objetivo(50, 27, 50);
		
		pisos[0] = new Piso(entorno.ancho() / 2 - 60, entorno.alto() - 100);
		pisos[1] = new Piso(entorno.ancho() / 2 + 60, entorno.alto() - 200);
		pisos[2] = new Piso(entorno.ancho() / 2 - 60, entorno.alto() - 300);
		pisos[3] = new Piso(entorno.ancho() / 2 + 60, entorno.alto() - 400);
		pisos[4] = new Piso(entorno.ancho() / 2 - 60, entorno.alto() - 500);
		
		fondo = Herramientas.cargarImagen("fondo.png");

		vuelta = true;

		int puntaje = 10;

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

		for (int i = 0; i < pisos.length; i++) {
			pisos[i].dibujar(entorno);
		}

		entorno.cambiarFont("sans", 20, Color.white);
		entorno.escribirTexto("Vidas: " + vikinga.getVidas() + " Puntos: " + puntaje, entorno.ancho() - 200, 22);

		objetivo.dibujarObjetivo(entorno);
		vikinga.dibujarVikinga(entorno);
		// Velociraptor.dibujarVelociraptor(entorno);

		if (rayo != null) {
			rayo.dibujar(entorno);
			rayo.ida();
			if (rayo.getX() > entorno.ancho() || rayo.getX() < 0) {
				rayo = null;
			}
		}
		vikinga.quePiso();

		// vikinga.establecerElPiso(pisos);

		vikinga.caer(entorno);

		if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA) || entorno.estaPresionada('h')) {
			vikinga.moverHaciaIzquierda(entorno);
		}
		if (entorno.estaPresionada(entorno.TECLA_DERECHA) || entorno.estaPresionada('l')) {
			vikinga.moverHaciaDerecha(entorno);
		}
		if (entorno.estaPresionada('u')) {
			vikinga.saltar(entorno); // salto
		}
		if (entorno.estaPresionada('e')) {
			vikinga.escudo(entorno);
		}

		if (entorno.estaPresionada(entorno.TECLA_ESPACIO) && rayo == null) {
			rayo = new Rayo(20, 20, vikinga.getX(), vikinga.getY(), 5, vikinga.getPiso());
		}

		// Raptor

		// raptor.dibujarRaptor(entorno);
		raptor2.dibujarRaptor(entorno);
		raptor3.dibujarRaptor(entorno);
		raptor4.dibujarRaptor(entorno);

		// raptor.mover();
		raptor2.mover();
		raptor3.mover();
		raptor4.mover();

		if (raptor.finDeEscalera(pisos[0]) || raptor2.finDeEscalera(pisos[1]) || raptor3.finDeEscalera(pisos[2])
				|| raptor4.finDeEscalera(pisos[3])) {
			raptor.cambiarDeDireccion();
			raptor2.cambiarDeDireccion();
			raptor3.cambiarDeDireccion();
			raptor4.cambiarDeDireccion();
			if (vuelta) {
				raptor.cambiarDeDireccionImg(vuelta);
				raptor2.cambiarDeDireccionImg(vuelta);
				raptor3.cambiarDeDireccionImg(vuelta);
				raptor4.cambiarDeDireccionImg(vuelta);
				vuelta = false;
			} else {
				raptor.cambiarDeDireccionImg(vuelta);
				raptor2.cambiarDeDireccionImg(vuelta);
				raptor3.cambiarDeDireccionImg(vuelta);
				raptor4.cambiarDeDireccionImg(vuelta);
				vuelta = true;
			}
		}

		if (rayo != null && raptor.choqueRayo(rayo)) {
			rayo = null;
			raptor = null;
			puntaje += 80;
		} else {
			raptor.dibujarRaptor(entorno);
			raptor.mover();
		}

		// Procesamiento de un instante de tiempo
		// ...
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}
