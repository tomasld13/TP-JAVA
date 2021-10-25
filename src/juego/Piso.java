package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Piso { // respeten los nombres del enunciado

	private int x;
	private int y;

	private int alto;
	private int ancho;
	private Image img;

	public Piso(int x, int y) {

		this.x = x;
		this.y = y;
		this.alto = 20;
		this.ancho = 680;
		this.img = Herramientas.cargarImagen("escalera.png");
	}

	public void dibujar(Entorno e) {
		// e.dibujarRectangulo(x, y, ancho, alto, 0, Color.WHITE);
		e.dibujarImagen(img, x, y, 0);
	}

	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getAncho() {
		return ancho;
	}

}
