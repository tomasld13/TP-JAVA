package juego;

import java.awt.Color;
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

	public Velociraptor(int ancho, int alto, int x, int y, double velocidad, double angulo) {
		
		this.alto = 80;
		this.ancho = 130;
		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
		this.angulo = Math.PI;
		this.img = Herramientas.cargarImagen("raptorizq.gif");
	}

	public void dibujar(Entorno e) {
		// e.dibujarRectangulo(x, y, ancho, alto, angulo, Color.green);
		e.dibujarImagen(img, x, y, angulo, 0.6);
	}

	public void mover() {
		x += velocidad * Math.cos(angulo);

	}

	public boolean finDeEscalera(Piso pisos) {
		return x < pisos.getX() - pisos.getAncho() / 2 + ancho / 2
				|| x > pisos.getX() + pisos.getAncho() / 2 - ancho / 2;
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
				&& y < rayo.getY();
	}

}
