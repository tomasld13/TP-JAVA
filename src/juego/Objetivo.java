package juego;

import java.awt.Color;
import entorno.Entorno;

public class Objetivo {
	private int tamaño;
	private double x;
	private double y;
	
	public Objetivo(int tamaño, double x, double y) {
		super();
		this.tamaño = tamaño;
		this.x = x;
		this.y = y;
	}
	
	public void dibujarObjetivo(Entorno e) {
		e.dibujarTriangulo(x, y, tamaño, tamaño, Math.PI/2, Color.red);
	}
		
}
