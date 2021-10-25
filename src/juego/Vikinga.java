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
	private Image imagenDelEscudo; // imagenDelEscudo

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
		this.imagenDelEscudo = Herramientas.cargarImagen("escudo.png");

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

	public boolean getdireccion() {
		return direccion;
	}

	public void quePiso(Piso[] pisos) {
		piso = 1;
		for (int i = 1; i < pisos.length - 1; i++) {
			if (y  < pisos[i].getY() + pisos[i].getAlto() && y  - alto / 2> pisos[i + 1].getY() + pisos[i + 1].getAlto()) {
				piso += i;
				System.out.println("IF");
			}
		}
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
		y -= 10;
	} // img = Herramientas.cargarImagen("img.png");

	public boolean banderaDeSaltoDePiso() {

		if (x > 700 && x < 800) {
			if (piso == 1 || piso == 3 || piso == 5 || piso == 0) {
				banderaDeSaltoDePiso = true;
			} else
				banderaDeSaltoDePiso = false;
		} else

		if (x > 0 && x < 100) {
			if (piso == 2 || piso == 4 || piso == 0) {
				banderaDeSaltoDePiso = true;
			} else
				banderaDeSaltoDePiso = false;
		} else
			banderaDeSaltoDePiso = false;
		return banderaDeSaltoDePiso;

	}

	public boolean banderaDeCaida() {

		if (piso == 1) {
			banderaDeCaida = false;
		} else if (x < 700 && (piso == 2 || piso == 4 || piso == 6)) {
			banderaDeCaida = false;
		} else if (x > 100 && (piso == 3 || piso == 5)) {
			banderaDeCaida = false;

		} else
			banderaDeCaida = true;
		return banderaDeCaida;

	}

	public void caer(Entorno e) {

		y = y + 3; // modificado para prueba. ajustar velocidad de caida

	}

	public void escudo(Entorno e) {
		if (direccion) {
			e.dibujarImagen(imagenDelEscudo, x + 60, y, 0, 0.07);
			// e.dibujarRectangulo(x + 40, y, 5, alto, 0, Color.cyan);
		} else {
			e.dibujarImagen(imagenDelEscudo, x - 60, y, 0, 0.07);
			// e.dibujarRectangulo(x - 40, y, 5, alto, 0, Color.cyan);
		}

	}

	public boolean ChoqueRaptor(Velociraptor raptor) {
		return x < raptor.getX() + raptor.getAncho() - ancho / 2 && x > raptor.getX() - raptor.getAncho() + ancho / 2
				&& y < raptor.getY() + raptor.getAlto() / 2 && y > raptor.getY() - raptor.getAlto();
	}

	public void muerte() {
		x = 20;
		y = 550;
	}

}
