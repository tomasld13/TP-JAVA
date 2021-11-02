package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Velociraptor {

	private int x;
	private int y;

	private int ancho;
	private int alto;

	private double velocidad;
	private double angulo;
	private boolean distanciaDeDisparo;
	private boolean direccion;
	private Laser laser;

	private Image img;

	public Velociraptor(int y, int x, double velocidad) {
		this.ancho = 130;
		this.alto = 80;
		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
		this.angulo = Math.PI;
		this.img = Herramientas.cargarImagen("raptorizq.gif");
		this.direccion=true;
		this.distanciaDeDisparo=true;
	}

	public void dibujar(Entorno e) {
		if (direccion) {
			// this.img = Herramientas.cargarImagen("raptor.png");
			this.img = Herramientas.cargarImagen("raptor.gif");

		} else {
			// this.img = Herramientas.cargarImagen("raptorizq.png");
			this.img = Herramientas.cargarImagen("raptorizq.gif");

		}
		// e.dibujarRectangulo(x, y, ancho, alto, angulo, Color.green);
		e.dibujarImagen(img, x, y, 0, 0.6);

	}
	
	public boolean distanciaPermitida(double xDeVikinga, double yDeVikinga) {
		if(y + alto > yDeVikinga && y-alto < yDeVikinga && (x > xDeVikinga || x < xDeVikinga)) {
			return false;
		}
		return true;
	}
	
	public boolean chocasteConEntorno(Entorno e) {
		return x < 0 + ancho / 2 || x > e.ancho() - ancho / 2;
	}

	public void mover() {
		if(direccion) {
			x += velocidad; // * Math.cos(angulo)
		}
		else {
			x-=velocidad;
		}
		

	}

	// CHECK ME
	public boolean estasParadoEnUnPiso(Piso[] pisos) {
		if (pisos[0].chocasteParteSuperiorCon(x, y + alto / 2) || pisos[1].chocasteParteSuperiorCon(x, y + alto / 2)
				|| pisos[2].chocasteParteSuperiorCon(x, y + alto / 2)
				|| pisos[3].chocasteParteSuperiorCon(x, y + alto / 2)
				|| pisos[4].chocasteParteSuperiorCon(x, y + alto / 2)
				|| pisos[5].chocasteParteSuperiorCon(x, y + alto / 2)) {
			return true;
		}
		return false;
	}

	public void caer(Entorno e) {
		y = y + 5;
	}

	public void cambiarDeDireccion() {
		//angulo += -Math.PI;
		if(direccion) {
			direccion=false;
		}else {
			direccion=true;
		}
	}
	
	public Laser disparar() {
		laser = new Laser(x, y, direccion);
		return laser;
	}
//	public void cambiarDeDireccionImg(boolean a) {
//		if (a) {
//			// this.img = Herramientas.cargarImagen("raptor.png");
//			this.img = Herramientas.cargarImagen("raptor.gif");
//
//		} else {
//			// this.img = Herramientas.cargarImagen("raptorizq.png");
//			this.img = Herramientas.cargarImagen("raptorIzq.gif");
//
//		}
//	}

	// isEmpty()
	// hasColour()
	// isChocandoUnRayo()
	// estásChocandoUnRayo()
	public boolean chocasteUnRayo(Rayo rayo) {
		return x < rayo.getX() + ancho / 2 && x > rayo.getX() - ancho / 2 && y > rayo.getY() - rayo.getAlto()
				&& y < rayo.getY() + rayo.getAlto();
	}
	
	public void respawn(Entorno e){
		if (x < 10 + ancho / 2 && y > e.alto()-alto) {
			x = 200;
			y = 40;
		}
	}
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}

}
