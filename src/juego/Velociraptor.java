package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Velociraptor {

	private double x;
	private double y;

	private int ancho;
	private int alto;

	private double velocidad;
	private boolean direccion;
	private Laser laser;

	private Image img;

	public Velociraptor(int y, int x, double velocidad) {
		this.ancho = 130;
		this.alto = 80;
		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
		this.img = Herramientas.cargarImagen("raptorizq.gif");
		this.direccion = true;
	}

	public void dibujar(Entorno e) {
		if (direccion) {
			this.img = Herramientas.cargarImagen("raptor.gif");
		} else {
			this.img = Herramientas.cargarImagen("raptorizq.gif");
		}
		e.dibujarImagen(img, x, y, 0, 0.6);
	}

	public boolean distanciaPermitida(double xDeVikinga, double yDeVikinga) {
		if (y + alto > yDeVikinga && y - alto < yDeVikinga && (x > xDeVikinga || x < xDeVikinga)) {
			return false;
		}
		return true;
	}

	public boolean chocasteConEntorno(Entorno e) {
		return x < 0 + ancho / 2 || x > e.ancho() - ancho / 2;
	}

	public void mover() {
		if (direccion) {
			x += velocidad;
		} else {
			x -= velocidad;
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
		if (direccion) {
			direccion = false;
		} else {
			direccion = true;
		}
	}

	public Laser disparar() {
		laser = new Laser(x, y, direccion);
		Herramientas.cargarSonido("sounds/laser.wav").start();
		return laser;
	}

	// isEmpty()
	// hasColour()
	// isChocandoUnRayo()
	// estásChocandoUnRayo()
	public boolean chocasteUnRayo(Rayo rayo) {
		return x < rayo.getX() + ancho / 2 && x > rayo.getX() - ancho / 2 && y > rayo.getY() - rayo.getAlto()
				&& y < rayo.getY() + rayo.getAlto();
	}

	public void respawn(Entorno e) {
		if (x < 10 + ancho / 2 && y > e.alto() - alto) {
			x = 200;
			y = 40;
		}
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}

}
