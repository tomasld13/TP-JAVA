package juego;

import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Vikinga {

	private double x;
	private double y;

	private int alto; // tamaño, size
	private int ancho; // 0.5 * size

	private int vidas;

	private int velocidad;
	private int velocidadDeCaida;

	private boolean banderaDeSaltoDePiso;
	private boolean banderaDeCaida;

	private int piso;
	private boolean direccion; // true=derecha false=izquierda
	private Image img; // img
//	private Image imgescudo; // imagenDelEscudo

	public Vikinga(int alto, int ancho, int vidas, double x, double y, int velocidad, int velocidadDeCaida, int piso,
			boolean banderaDeSaltoDePiso, boolean banderaDeCaida, boolean direccion) {
		this.alto = alto;
		this.ancho = ancho;
		this.vidas = vidas;
		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
		this.velocidadDeCaida = velocidadDeCaida; // caidas
		this.piso = piso;
		this.img = Herramientas.cargarImagen("per.png");
//		this.imgescudo = Herramientas.cargarImagen("");
		this.banderaDeSaltoDePiso = banderaDeSaltoDePiso;
		this.banderaDeCaida = banderaDeCaida;
		this.direccion = true;

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

	public int getVelocidadDeCaida() {
		return velocidadDeCaida;
	}
//	}

	public int getPiso() {
		return piso;
	}

	public boolean getbanderaDeSaltoDePiso() {
		return banderaDeSaltoDePiso;
	}
	
	public boolean getbanderaDeCaida() {
		return banderaDeCaida;
	}
	
	public void quePiso() {
		if (y > 525) { 
			piso = 1;
		}else
		if (y > 467 && y < 470) {
			piso = 2;
		}else
		if (y > 397 && y < 400) {
			piso = 3;
		}else
		if (y > 297 && y < 300) {
			piso = 4;
		}else
		if (y > 197 && y < 200) {
			piso = 5;
		}else
		if (y > 97 && y < 100) {
			piso = 6;
		}else
			piso=0;
	}

	public void dibujarVikinga(Entorno e) {
		// e.dibujarTriangulo(x, y, alto, ancho, Math.PI/2, Color.CYAN);
		e.dibujarImagen(img, x, y, 0, 0.20);
	}

	public void moverHaciaIzquierda(Entorno e) {
		if (x > ancho / 2) {
			x -= velocidad;
			img = Herramientas.cargarImagen("peri.png");
			direccion = false;
		}
	}

	public void moverHaciaDerecha(Entorno e) {
		if (x < e.ancho() - ancho / 2) {
			x += velocidad;
			img = Herramientas.cargarImagen("per.png");
			direccion = true;
		}
	}

	public void saltar(Entorno e) { 
		
		y-=3;
		}
				
		// img = Herramientas.cargarImagen("img.png");

	

	public void banderaDeSaltoDePiso(Entorno e) {

		if (x < 85) {
			if (piso == 2 || piso == 4) {
				banderaDeSaltoDePiso = true;
			}
		}
		if (x > e.ancho() - 120) {
			if (piso == 1 || piso == 3) {
				banderaDeSaltoDePiso = true;
			}
		}

	}
	public void banderaDeCaida(Entorno e) {
		if (piso == 1) {
			banderaDeCaida = false;
		}else
		if (x < e.ancho() - 85  && piso == 2) {
			banderaDeCaida = false;
		}else
		if (x > 85 && piso == 3) {
			banderaDeCaida = false;
		}else
		if (x < e.ancho() - 85 && piso == 4) {
			banderaDeCaida = false;
		}else
		if (x > 85 && piso == 5) {
			banderaDeCaida = false;
		}else
		if (x < e.ancho() - 85 && piso == 6) {
			banderaDeCaida = false;
		}else
			banderaDeCaida = true;
	}
	public void subirDePiso (Entorno e) {
		if (piso == 1 || piso == 3) {
			if (banderaDeSaltoDePiso == true){
				x = x-3;
				y = y - velocidadDeCaida;
			}
		}
		if (piso == 2 || piso == 4) {
			if (banderaDeSaltoDePiso == true){
				x = x+3;
				y = y - velocidadDeCaida;
			}
		}
	}
	public void caer(Entorno e){
		if (banderaDeCaida == true && piso == 0) {
			y = y+velocidadDeCaida;
				
			
		}
	}


	public void escudo(Entorno e) {
		// e.dibujarImagen(imgescudo, x, y, 0, 0.20);
		if (direccion) {
			e.dibujarRectangulo(x + 40, y, 5, alto, 0, Color.cyan);
		} else {
			e.dibujarRectangulo(x - 40, y, 5, alto, 0, Color.cyan);
		}

	}

}
