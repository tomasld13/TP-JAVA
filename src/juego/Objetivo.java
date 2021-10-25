package juego; // en este caso la commodor

import java.awt.Color;
import entorno.Entorno;
import entorno.Herramientas;
import java.awt.Image;

public class Objetivo {
	private double x;
	private double y;
	private Image img;

	public Objetivo(int tamaño, double x, double y) {
		this.x = x;
		this.y = y;
		this.img = Herramientas.cargarImagen("commodore.png");
	}

	public void dibujarObjetivo(Entorno e) {
		e.dibujarImagen(img, x, y, 0,0.15);
	}

}
