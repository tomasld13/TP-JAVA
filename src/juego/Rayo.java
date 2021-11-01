// utilizar booleano de direccion de la vikinga, para dirigir el rayo

package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Rayo {
	
	private double x;
	private double y;

	private int alto;
	private boolean direccion;

	private Image img;

	public Rayo(double x, double y, boolean direccion) {
		this.x = x;
		this.y = y;
		this.direccion = direccion;
		this.img = Herramientas.cargarImagen("rayo.png");
		this.alto = 30;
	}

	public void dibujar(Entorno e) {
		e.dibujarImagen(img, x, y, 1.05, 0.05);
	}

	public void mover(Entorno e) {
		if (direccion == true && x <= 800) {
			x += 6;
		}
		if (direccion == false && x >= 0) {
			x -= 6;
		}
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
