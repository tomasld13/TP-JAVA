package juego; // en este caso la commodor

import entorno.Entorno;
import entorno.Herramientas;
import java.awt.Image;

public class Commodore {
	private double x;
	private double y;

	private int tamaño;
	private Image img;

	public Commodore(int tamaño, double x, double y) {
		this.tamaño = tamaño;
		this.x = x;
		this.y = y;
		this.img = Herramientas.cargarImagen("commodore.png");
	}

	public void dibujar(Entorno e) {
		e.dibujarImagen(img, x, y, 0, 0.15);
	}

	public int getTamaño() {
		return tamaño;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

}
