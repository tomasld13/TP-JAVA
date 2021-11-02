package juego;

import java.awt.Image;
import java.awt.Color;
import entorno.Entorno;
import entorno.Herramientas;

public class Laser {
	
	private int tamaño;
	private double x;
	private double y;
	private Image img;
	private boolean direccion;
	private int alto;
	
	
	public Laser(double x, double y, boolean direccion) {
		this.tamaño = 30;
		this.x = x;
		this.y = y;
		this.img = Herramientas.cargarImagen("rayo.png");
		this.direccion = direccion;
		this.alto=30;
	}

	public void dibujar(Entorno e) {
		e.dibujarImagen(img, x, y, 1.05, 0.05);
	}

	public void mover(Entorno e) {
		if (direccion == true) {
			x += 6;
		}
		if (direccion == false ) {
			x -= 6;
		}
	}
	
	public boolean teExcedisteDelEntorno(Entorno e) {
		return x < 0 || x > e.ancho();
	}

	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	public int getAlto() {
		return alto;
	}
}
