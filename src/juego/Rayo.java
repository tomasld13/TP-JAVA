package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Rayo {
	private int ancho;
	private int alto;
	private double x;
	private double y;
	private int daño;
	private int direccion;
	private Image img;
	
	public Rayo(int ancho, int alto, double x, double y, int daño, int direccion) {
		super();
		this.ancho = ancho;
		this.alto = alto;
		this.x = x;
		this.y = y;
		this.daño = daño;
		this.direccion = direccion;
		this.img = Herramientas.cargarImagen("rayo.png");
	}

	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getDaño() {
		return daño;
	}

	public void dibujarRayo(Entorno e) {
		//e.dibujarTriangulo(x, y, alto, ancho, Math.PI / 2, Color.yellow);
		e.dibujarImagen(img, x, y, 1.05, 0.05);
	}

	public void ida() {
		if (direccion == 1 || direccion == 3 || direccion == 5) {
			x += 5;
		} else {
			x -= 5;
		}
	}

}
