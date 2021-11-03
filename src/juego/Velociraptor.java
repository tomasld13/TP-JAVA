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

	public boolean estasParadoEnUnPiso(Piso[] pisos) {
		for (Piso p : pisos) {
			if (p.chocasteParteSuperiorCon(x, y + alto / 2)) {
				return true;
			}
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
		return new Laser(x,y, direccion);
	}

	public boolean estasChocandoUnRayo(Rayo rayo) {
		return x < rayo.getX() + ancho / 2 && x > rayo.getX() - ancho / 2 && y > rayo.getY() - rayo.getAlto()
				&& y < rayo.getY() + rayo.getAlto();
	}

	public boolean saleDelPrimerPiso(Entorno e) {
		return x < 0 + ancho / 2 && y > e.alto() - alto;
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
