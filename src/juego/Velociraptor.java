package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Velociraptor {
	private int alto;
	private int ancho;
	
	private int x;
	private int y;

	
	private double velocidad;
	private double angulo;
	
	private Image img;

	public Velociraptor(int x, int y, double velocidad) {
		
		this.alto = 80;
		this.ancho = 120;
		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
		this.angulo = Math.PI ;
		this.img = Herramientas.cargarImagen("raptor.png");
	}
	
	public void dibujarRaptor(Entorno e) {
		e.dibujarRectangulo(x, y, ancho, alto, angulo, Color.green);
		e.dibujarImagen(img, x, y, angulo, 0.7);
	}
	
	public void mover() {
		x += velocidad * Math.cos(angulo);
		
	}
	
	public boolean finDeEscalera(Escalera esca) {
		return x < esca.getX() - esca.getAncho()/2 + ancho/2 || x > esca.getX() +esca.getAncho() /2 - ancho / 2; 
	}
	
	public void cambiarDeDireccion() {
		angulo += -Math.PI ;
	}

	
	
	
}
