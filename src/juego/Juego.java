package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {

	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;

	private Piso[] pisos = new Piso[6];

	private Vikinga vikinga;

//	private Velociraptor raptor;
//	private Velociraptor raptor2;
//	private Velociraptor raptor3;
//	private Velociraptor raptor4;

	private Velociraptor raptor1 = new Velociraptor(100, 5, 350, 55, 2, 0);
	private Velociraptor raptor2 = new Velociraptor(100, 5, 100, 55, 2, 0);
	private Velociraptor raptor3 = new Velociraptor(100, 5, 600, 55, 2, 0);

	private Image gameOver;
	private Image fondo;
	private Rayo rayo;
	private Objetivo objetivo;

	private boolean vuelta;

	private int vidas = 3;
	private int puntaje; // y también vidas? vidas--

	public Juego() {

		this.entorno = new Entorno(this, "Blanco_CarroAvila_Ledesma_Equipo3", 800, 600);

		vikinga = new Vikinga(65, 50, 5, 30, 400, 5, 9, 0, false, false, false);

		objetivo = new Objetivo(50, 55, 50);

		pisos[0] = new Piso(entorno.ancho() / 2 - 60, entorno.alto());
		pisos[1] = new Piso(entorno.ancho() / 2 - 60, entorno.alto() - 100);
		pisos[2] = new Piso(entorno.ancho() / 2 + 60, entorno.alto() - 200);
		pisos[3] = new Piso(entorno.ancho() / 2 - 60, entorno.alto() - 300);
		pisos[4] = new Piso(entorno.ancho() / 2 + 60, entorno.alto() - 400);
		pisos[5] = new Piso(entorno.ancho() / 2 - 60, entorno.alto() - 500);

		fondo = Herramientas.cargarImagen("fondo.png");
		gameOver = Herramientas.cargarImagen("gameoverphrase.jpg");

		vuelta = true;

//		int puntaje = 10;

//      inicia el juego
		this.entorno.iniciar();

	}

	public void tick() {

		entorno.dibujarImagen(fondo, entorno.ancho() / 2, entorno.alto() / 2, 0);

		for (int i = 0; i < pisos.length; i++) {
			pisos[i].dibujar(entorno);
		}

		entorno.cambiarFont("sans", 20, Color.white);
		entorno.escribirTexto("Vidas: " + vidas + " Puntos: " + puntaje, entorno.ancho() - 200, 22);

		objetivo.dibujarObjetivo(entorno);
// vikinga

		vikinga.dibujarVikinga(entorno);

		vikinga.quePiso();

		if (vikinga.banderaDeCaida()) {
			vikinga.caer(entorno); // Gravedad
		}
		if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA) || entorno.estaPresionada('a')) {
			vikinga.moverHaciaIzquierda(entorno);
		}
		if (entorno.estaPresionada(entorno.TECLA_DERECHA) || entorno.estaPresionada('d')) {
			vikinga.moverHaciaDerecha(entorno);
		}
		if (entorno.estaPresionada('w')) {
			if (vikinga.banderaDeSaltoDePiso()) {
				vikinga.saltar(entorno);
			}
		}
		if (entorno.estaPresionada('e')) {
			vikinga.escudo(entorno);
		}

		if (vikinga.ChoqueRaptor(raptor1)) {
			vikinga.muerte();
			vidas -= 1;
		}
//Rayo		
		if (rayo != null) {
			rayo.dibujar(entorno);
			rayo.ida();
			if (rayo.getX() > entorno.ancho() || rayo.getX() < 0) {
				rayo = null;
			}
		}
		if (entorno.estaPresionada(entorno.TECLA_ESPACIO) && rayo == null) {
			rayo = new Rayo(vikinga.getX(), vikinga.getY(), vikinga.getPiso());
		}
// Raptors		

		if (raptor1 != null) {
			raptor1.dibujar(entorno);
			raptor1.mover();

			for (int i = 0; i < pisos.length; i++) {
				if (raptor1.finDeEscalera(pisos[i]) || raptor1.getX() < 20 || raptor1.getX() > 780) {
					raptor1.cambiarDeDireccion();
					if(vuelta) {
						raptor1.cambiarDeDireccionImg(vuelta);
						vuelta = false;
					} else {
						raptor1.cambiarDeDireccionImg(vuelta);
						vuelta = true;
					}
					
					System.out.println("me caí");
				} else {
					System.out.println("NADAAA");
				}

			}
			raptor1.quePiso();
			if (raptor1.banderaDeCaida()) {
				raptor1.caer(entorno);
			}

			if (raptor1.getX() < 20 || raptor1.getX() > 780) {
				raptor1.cambiarDeDireccion();
				raptor1.cambiarDeDireccionImg(vuelta);
			}
		}
//				|| raptor4.finDeEscalera(escaCuatro)) {
//			raptor.cambiarDeDireccion();
//			raptor2.cambiarDeDireccion();
//			raptor3.cambiarDeDireccion();
//			raptor4.cambiarDeDireccion();
//		}			if (vuelta) {
//				raptor.cambiarDeDireccionImg(vuelta);
//				raptor2.cambiarDeDireccionImg(vuelta);
//				raptor3.cambiarDeDireccionImg(vuelta);
//				raptor4.cambiarDeDireccionImg(vuelta);
//				vuelta = false;
//			} else {
//				raptor.cambiarDeDireccionImg(vuelta);
//				raptor2.cambiarDeDireccionImg(vuelta);
//				raptor3.cambiarDeDireccionImg(vuelta);
//				raptor4.cambiarDeDireccionImg(vuelta);
//				vuelta = true;
//			}

		if (rayo != null && raptor1.choqueRayo(rayo)) {
			rayo = null;
			raptor1 = null;
			puntaje += 80;
		}

		if (vidas == 0) {
			entorno.dibujarImagen(gameOver, entorno.ancho() / 2, entorno.alto() / 2, 0);
		}
		// else {
		// raptor.dibujarRaptor(entorno);
		// raptor.mover();
		// }

//Array de Raptors =======													
//		for (int i = 0; i < raptors.length; i++) {
//			raptors[i].dibujar(entorno);
//			raptors[i].mover();
//			if (raptors[i].finDeEscalera(pisos[i])) {
//				raptors[i].cambiarDeDireccion();
//			}
//				if (vuelta) {
//					raptors[i].cambiarDeDireccionImg(vuelta);
//					vuelta = false;
//				} else {
//					raptors[i].cambiarDeDireccionImg(vuelta);
//					vuelta = true;
//	=====			}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}
