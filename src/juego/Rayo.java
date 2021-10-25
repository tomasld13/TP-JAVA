// utilizar booleano de direccion de la vikinga, para dirigir el rayo

package juego;

import java.awt.Image;


import entorno.Entorno;
import entorno.Herramientas;

public class Rayo {
	private double x;
	private double y;
	
	private int direccion;
	
	private Image img;
	private int alto;

	public Rayo(double x, double y, int direccion) {
		this.x = x;
		this.y = y;
		this.direccion = direccion;
		this.img = Herramientas.cargarImagen("rayo.png");

		this.alto = 30;
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
	
	public void dibujar(Entorno e) {
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
