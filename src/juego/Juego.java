package juego;

import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {

	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Velociraptor[] raptors;
	private Piso[] pisos; // fixme
	private Vikinga vikinga;

	private Image gameOver;
	private Image fondo;
	private Rayo rayo;
	private Commodore commodore;
	private int contadorDeTicks; // contadorDeTiempo, tiempo, cantidadDeFrames, cantidadDeTics

	private int vidas; // fixme
	private int puntaje;

	public Juego() {
		contadorDeTicks = 500;
		vidas = 3;
		this.entorno = new Entorno(this, "Blanco_CarroAvila_Ledesma_Equipo3", 800, 600);

		vikinga = new Vikinga(30, 550);

		commodore = new Commodore(50, 55, 50);

		raptors = new Velociraptor[4];
		pisos = new Piso[6];
		pisos[0] = new Piso(entorno.ancho() / 2, entorno.alto() - 10, 800);
		pisos[1] = new Piso(entorno.ancho() / 2 - 60, entorno.alto() - 110, 680);
		pisos[2] = new Piso(entorno.ancho() / 2 + 60, entorno.alto() - 210, 680);
		pisos[3] = new Piso(entorno.ancho() / 2 - 60, entorno.alto() - 310, 680);
		pisos[4] = new Piso(entorno.ancho() / 2 + 60, entorno.alto() - 410, 680);
		pisos[5] = new Piso(entorno.ancho() / 2 - 60, entorno.alto() - 510, 680);

		fondo = Herramientas.cargarImagen("fondo.png");
		gameOver = Herramientas.cargarImagen("gameoverphrase.jpg");

//      inicia el juego
		this.entorno.iniciar();

	}

	public void tick() {

		if (vidas < 1) {
			entorno.dibujarImagen(gameOver, entorno.ancho() / 2, entorno.alto() / 2, 0);
			entorno.escribirTexto("Perdiste tu puntuacion fue" + puntaje, entorno.ancho() / 2, 400);
			return;
		}

		if (vikinga.recuperasteCommodore(commodore)) {
			entorno.dibujarImagen(gameOver, entorno.ancho() / 2, entorno.alto() / 2, 0);
			entorno.escribirTexto("!GANASTE¡tu puntuacion fue" + puntaje, entorno.ancho() / 2, 400);
			return;
		}

		entorno.dibujarImagen(fondo, entorno.ancho() / 2, entorno.alto() / 2, 0);

		for (Piso p : pisos) {
			p.dibujar(entorno);
		}

		entorno.cambiarFont("sans", 20, Color.white);
		entorno.escribirTexto("Vidas: " + vidas + " Puntos: " + puntaje, entorno.ancho() - 200, 22);

		commodore.dibujar(entorno);
// vikinga

		if (entorno.estaPresionada('w') && vikinga.banderaDeSalto(pisos)) {
			vikinga.saltar(entorno);
		}

		if (vikinga.banderaDeCaida(pisos)) {
			vikinga.caer(entorno);
		}
		vikinga.dibujar(entorno);

		if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA) || entorno.estaPresionada('a')) {
			vikinga.moverHaciaIzquierda(entorno);
			vikinga.quieta(false);
		} else if (entorno.estaPresionada(entorno.TECLA_DERECHA) || entorno.estaPresionada('d')) {
			vikinga.moverHaciaDerecha(entorno);
			vikinga.quieta(false);
		} else {
			vikinga.quieta(true);
		}
		if (entorno.estaPresionada('e')) {
			vikinga.escudo(entorno);
		}

//Rayo        
		if (rayo != null) {
			rayo.dibujar(entorno);
			rayo.mover(entorno);
			if (rayo.teExcedisteDelEntorno(entorno)) {
				rayo = null;
			}
		}

		if (entorno.estaPresionada(entorno.TECLA_ESPACIO) && rayo == null) {
			rayo = vikinga.disparar();
		}

// Raptors        

		for (int e = 0; e < raptors.length; e++) {
			if (raptors[e] != null) {
				raptors[e].dibujar(entorno);
				raptors[e].mover();
				if (!raptors[e].estasParadoEnUnPiso(pisos)) {
					raptors[e].caer(entorno);
				}
				if (raptors[e].chocasteConEntorno(entorno)) {
					raptors[e].cambiarDeDireccion();
				}
				if (vikinga.ChoqueRaptor(raptors[e])) {
					vikinga.respawn();
					vidas -= 1;
				}
				if (rayo != null && raptors[e].chocasteUnRayo(rayo)) {
					rayo = null;
					raptors[e] = null;
					puntaje += 80;
					contadorDeTicks = 350;
				}
			}
		}
		if (contadorDeTicks >= 500) {
			int nulo=0;
			for (int i = 0; i < raptors.length; i++)
				if (raptors[i] == null && nulo == 0) {
					raptors[i] = new Velociraptor(40, 200, 4);
					nulo+=1;
				}
			contadorDeTicks = 0;
		}
		System.out.println(contadorDeTicks);
		contadorDeTicks += 1;
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}
