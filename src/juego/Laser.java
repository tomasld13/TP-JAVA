package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Laser {

	private double x;
	private double y;
	private Image img;
	private boolean direccion;
	private int alto;

	public Laser(double x, double y, boolean direccion) {
		this.x = x;
		this.y = y;
		this.img = Herramientas.cargarImagen("laser.png");
		this.direccion = direccion;
		this.alto = 30;
	}

	public void dibujar(Entorno e) {
		e.dibujarImagen(img, x, y, 0, 0.25);
	}

	public void mover() {
		if (direccion == true) {
			x += 6;
		}
		if (direccion == false) {
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
