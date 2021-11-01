package juego;

import java.awt.Color;
import entorno.Entorno;
import entorno.Herramientas;

public class Laser {
	
	private int tamaño;
	private double x;
	private double y;
	//private Image img;
	
	public Laser(int tamaño, double x, double y) {
		this.tamaño = tamaño;
		this.x = x;
		this.y = y;
	}
	
	public void dibujarLaser(Entorno e) {
		e.dibujarTriangulo(x, y, tamaño, tamaño, Math.PI / 2, Color.yellow);
	}
}
