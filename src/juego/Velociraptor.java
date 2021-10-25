package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Velociraptor {
	private int ancho;
	private int alto;
	private int piso;
	private int x;
	private int y;
	private double velocidad;
	private double angulo;



	private Image img;
	private boolean banderaDeCaida;

	public Velociraptor(int ancho, int piso, int x, int y, double velocidad, double angulo) {

		this.piso = piso;
		this.ancho = 130;
		this.alto = 80;
		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
		this.angulo = Math.PI;
		this.img = Herramientas.cargarImagen("raptorizq.gif");
	}

	public void dibujar(Entorno e) {
		e.dibujarRectangulo(x, y, ancho, alto, angulo, Color.green);
		e.dibujarImagen(img, x, y, angulo, 0.6);
	}

	public void mover() {
		x += velocidad * Math.cos(angulo);

	}

	public void quePiso() {
		if (y > 547) {
			piso = 1;
		} else if (y > 457 && y < 460) {
			piso = 2;
		} else if (y > 355 && y < 360) {
			piso = 3;
		} else if (y > 257 && y < 262) {
			piso = 4;
		} else if (y > 157 && y < 162) {
			piso = 5;
		} else if (y > 56 && y < 60) {
			piso = 6;
		} else
			piso = 0;
	}

	public boolean banderaDeCaida() {

		if (piso == 1) {
			banderaDeCaida = false;
		} else if (x < 700 && (piso == 2 || piso == 4 || piso == 6)) {
			banderaDeCaida = false;
		} else if (x > 100 && (piso == 3 || piso == 5)) {
			banderaDeCaida = false;

		} else
			banderaDeCaida = true;
		return banderaDeCaida;
	}

	public void caer(Entorno e) {
		y = y + 5;
	}

	public void cambiarDeDireccion() {
		angulo += -Math.PI;
	}

	public void cambiarDeDireccionImg(boolean a) {
		if (a) {
			// this.img = Herramientas.cargarImagen("raptor.png");
			this.img = Herramientas.cargarImagen("raptor.gif");
		} else {
			// this.img = Herramientas.cargarImagen("raptorizq.png");
			this.img = Herramientas.cargarImagen("raptorizq.gif");
		}
	}

	public boolean choqueRayo(Rayo rayo) {
		return x < rayo.getX() + ancho / 2 && x > rayo.getX() - ancho / 2 && y > rayo.getY() - rayo.getAlto()
				&& y < rayo.getY() + rayo.getAlto();
	}

	public boolean finDePiso(Piso pisos) {
		return x > pisos.getX() + pisos.getAncho() / 2 + ancho / 2
				|| x < pisos.getX() - pisos.getAncho() / 2 - ancho / 2;
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
