package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Escalera {
	private int x;
	private int y;
	
	private int alto;
	private int ancho;
	private Image img;
	
	public Escalera(int x, int y) {
		
		this.x = x;
		this.y = y;
		this.alto = 10;
		this.ancho = 700;
		this.img = Herramientas.cargarImagen("escalera.png");
	} 
	
	public void dibujarEscalera(Entorno e) {
		e.dibujarRectangulo(x, y, ancho, alto, 0, Color.WHITE);
		e.dibujarImagen(img, x, y, 0);
	}

}
