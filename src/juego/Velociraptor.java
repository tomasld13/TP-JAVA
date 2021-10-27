package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Velociraptor {
	private int ancho;
	private int alto;
	private int piso;
	private int x;
	private int y;
	private double velocidad;
	private double angulo;



	private Image img;
	private boolean banderaDeCaida;

	public Velociraptor(int ancho, int piso, int x, int y, double velocidad, double angulo) {

		this.piso = piso;
		this.ancho = 130;
		this.alto = 80;
		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
		this.angulo = Math.PI;
		this.img = Herramientas.cargarImagen("raptorizq.gif");
	}

	public void dibujar(Entorno e) {
		//e.dibujarRectangulo(x, y, ancho, alto, angulo, Color.green);
		e.dibujarImagen(img, x, y, 0, 0.6);
	}

	public void mover() {
		x += velocidad * Math.cos(angulo);
		

	}

//	public void quePiso(Piso[] pisos) {
//		for (int i = 0; i < pisos.length - 1; i++) {
//			if (y + alto /2  < pisos[i].getY()-pisos[i].getAlto()/2 && y  - alto / 2 > pisos[i + 1].getY() + pisos[i + 1].getAlto()/2) {
//				piso = i;
//			}
//		}
//	}
//		if (y > 547) {
//			piso = 1;
//		} else if (y > 457 && y < 460) {
//			piso = 2;
//		} else if (y > 355 && y < 360) {
//			piso = 3;
//		} else if (y > 257 && y < 262) {
//			piso = 4;
//		} else if (y > 157 && y < 162) {
//			piso = 5;
//		} else if (y > 56 && y < 60) {
//			piso = 6;
//		} else
//			piso = 0;
	

	public boolean banderaDeCaida(Piso[]pisos) {
		if (pisos[0].tocaTecho(y+alto/2, x) || pisos[1].tocaTecho(y+alto/2, x) || pisos[2].tocaTecho(y+alto/2, x) || pisos[3].tocaTecho(y+alto/2, x) || pisos[4].tocaTecho(y+alto/2, x) || pisos[5].tocaTecho(y+alto/2, x)){
			return false;
		}
		return true;
	}


	public void caer(Entorno e) {
		y = y + 5;
	}

	public void cambiarDeDireccion() { //modificar ya que es lo que nos da vuelta al dino
		angulo += -Math.PI;
	}

	public void cambiarDeDireccionImg(boolean a) {
		if (a) {
			// this.img = Herramientas.cargarImagen("raptor.png");
			this.img = Herramientas.cargarImagen("raptor.gif");

		} else {
			// this.img = Herramientas.cargarImagen("raptorizq.png");
			this.img = Herramientas.cargarImagen("raptorizq.gif");

		}
	}

	public boolean choqueRayo(Rayo rayo) {
		return x < rayo.getX() + ancho / 2 && x > rayo.getX() - ancho / 2 && y > rayo.getY() - rayo.getAlto()
				&& y < rayo.getY() + rayo.getAlto();
	}

	public boolean finDePiso(Piso pisos) {
		return x > pisos.getX() + pisos.getAncho() / 2 + ancho / 2
				|| x < pisos.getX() - pisos.getAncho() / 2 - ancho / 2;
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
	
	public int getPiso() {
		return piso;
	}

}
