package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Vikinga {
	private int alto;
	private int ancho;
	private int vidas;
	private double x;
	private double y;
	private int velocidad;
	private int piso;
	private Image img;
	
	
	public Vikinga(int alto, int ancho, int vidas, double x, double y, int velocidad, int piso) {
		this.alto = alto;
		this.ancho = ancho;
		this.vidas = vidas;
		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
		this.piso = piso;
		this.img = Herramientas.cargarImagen("per.png");
	}
	
	
	
	public int getAlto() {
		return alto;
	}
	public int getAncho() {
		return ancho;
	}
	public int getVidas() {
		return vidas;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public int getVelocidad() {
		return velocidad;
	}
	public int getPiso() {
		return piso;
	}

	public void quePiso() {
		if(y > 500  && y < 600 ) {
			piso=1;
		}
		if(y > 400  && y < 500 ) {
			piso=2;
		}
		if(y > 300  && y < 400 ) {
			piso=3;
		}
		if(y > 200  && y < 300 ) {
			piso=4;
		}
		if(y > 100  && y < 200 ) {
			piso=5;
		}
		if(y > 0  && y < 100 ) {
			piso=6;
		}
	}

	public void dibujarVikinga(Entorno e) {
		//e.dibujarTriangulo(x, y, alto, ancho, Math.PI/2, Color.CYAN);
		e.dibujarImagen(img, x, y, 0, 0.20);
	}
	
	public void moverHaciaIzquierda(Entorno e) {
		if(x > ancho/2) {
			x-=velocidad;
			img = Herramientas.cargarImagen("peri.png");
		}
	}
	
	public void moverHaciaDerecha(Entorno e) {
		if(x < e.ancho()-ancho/2) {
			x+=velocidad;
			img = Herramientas.cargarImagen("per.png");
		}
	}
	
	public void subir(Entorno e) {
		if (x > e.ancho()-85 && piso == 1) {
			y = 500-alto/2-10;
			x = e.ancho()-120;
		}
		if (x < 85 && piso == 2) {
			y = 400-alto/2-10;
			x = 120;
		}
		if (x > e.ancho()-85 && piso == 3) {
			y = 300-alto/2-10;
			x = e.ancho()-120;
		}
		if (x < 85 && piso == 4) {
			y = 200-alto/2-10;
			x = 120;
		}
		if (x > e.ancho()-85 && piso == 5) {
			y = 100-alto/2-10;
			x = e.ancho()-120;
		}
	}
	
	public void caer(Entorno e) {
		if (x > e.ancho()-85 && piso == 2) {
			y = 490+alto;
		}
		if (x < 85 && piso == 3) {
			y = 395+alto;
		}
		if (x > e.ancho()-85 && piso == 4) {
			y = 295+alto;

		}
		if (x < 85 && piso == 5) {
			y = 195+alto;
		}
		if (x > e.ancho()-85 && piso == 6) {
			y = 95+alto;
		}
	}


	public void moverHaciaArriba(Entorno entorno) {
		y-=1;
	}

}
