package juego;

import java.awt.Color; // retocar colision rectangulo verde
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Velociraptor {
	private int ancho;
	private int alto;

	private int x;
	private int y;
	private double velocidad;
	private double angulo;

	private Image img;

	public Velociraptor(int y, int x, double velocidad) {

		this.ancho = 130;
		this.alto = 80;
		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
		this.angulo = Math.PI;
		this.img = Herramientas.cargarImagen("raptorIzq.gif");
	}

	public void dibujar(Entorno e) {
		// e.dibujarRectangulo(x, y, ancho, alto, angulo, Color.green);
		e.dibujarImagen(img, x, y, 0, 0.6);
	}

	public void mover() {
		x += velocidad * Math.cos(angulo);

	}

	public boolean banderaDeCaida(Piso[] pisos) {
		if (pisos[0].tocaTecho(y + alto / 2, x) || pisos[1].tocaTecho(y + alto / 2, x)
				|| pisos[2].tocaTecho(y + alto / 2, x) || pisos[3].tocaTecho(y + alto / 2, x)
				|| pisos[4].tocaTecho(y + alto / 2, x) || pisos[5].tocaTecho(y + alto / 2, x)) {
			return false;
		}
		return true;
	}

	public void caer(Entorno e) {
		y = y + 5;
	}

	public void cambiarDeDireccion() { // modificar ya que es lo que nos da vuelta al dino
		angulo += -Math.PI;
	}

	public void cambiarDeDireccionImg(boolean a) {
		if (a) {
			// this.img = Herramientas.cargarImagen("raptor.png");
			this.img = Herramientas.cargarImagen("raptor.gif");

		} else {
			// this.img = Herramientas.cargarImagen("raptorizq.png");
			this.img = Herramientas.cargarImagen("raptorIzq.gif");

		}
	}

	public boolean choqueRayo(Rayo rayo) {
		return x < rayo.getX() + ancho / 2 && x > rayo.getX() - ancho / 2 && y > rayo.getY() - rayo.getAlto()
				&& y < rayo.getY() + rayo.getAlto();
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

	public int getAlto() {
		return alto;
	}

}
