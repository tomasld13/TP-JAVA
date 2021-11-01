package juego;

import java.awt.Color; // retocar colision rectangulo verde
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

	private Image img;

	public Velociraptor(int y, int x, double velocidad) {
		this.ancho = 130;
		this.alto = 80;
		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
		this.angulo = Math.PI;
		this.img = Herramientas.cargarImagen("raptorIzq.gif");
	}

	public void dibujar(Entorno e) {
		if (angulo >= Math.PI) {
			// this.img = Herramientas.cargarImagen("raptor.png");
			this.img = Herramientas.cargarImagen("raptor.gif");

		} else {
			// this.img = Herramientas.cargarImagen("raptorizq.png");
			this.img = Herramientas.cargarImagen("raptorIzq.gif");

		}
		// e.dibujarRectangulo(x, y, ancho, alto, angulo, Color.green);
		e.dibujarImagen(img, x, y, 0, 0.6);

	}

	public boolean chocasteConEntorno(Entorno e) {
		return x < 0 + ancho / 2 || x > 800 - ancho / 2;
	}

	public void mover() {
		x += velocidad * Math.cos(angulo);

	}

	// CHECK ME
	public boolean estasParadoEnUnPiso(Piso[] pisos) {
		if (pisos[0].chocasteParteSuperiorCon(x, y + alto / 2) || pisos[1].chocasteParteSuperiorCon(y + alto / 2, x)
				|| pisos[2].chocasteParteSuperiorCon(x, y + alto / 2)
				|| pisos[3].chocasteParteSuperiorCon(y + alto / 2, x)
				|| pisos[4].chocasteParteSuperiorCon(x, y + alto / 2)
				|| pisos[5].chocasteParteSuperiorCon(y + alto / 2, x)) {
			return true;
		}
		return false;
	}

	public void caer(Entorno e) {
		y = y + 5;
	}

	public void cambiarDeDireccion() {
		angulo += -Math.PI;
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
