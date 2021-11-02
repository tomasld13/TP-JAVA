package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Vikinga {

	private double x;
	private double y;

	private int alto; // tamaño, size
	private int ancho; // 0.5 * size

	private int velocidad;

	private boolean direccion; // true=derecha false=izquierda
	private Rayo rayo;
	private boolean estaSaltando;
	private boolean estaQuieta;

	private Image img; // img
	private Image imagenDelEscudo; // imagenDelEscudo

	public Vikinga(double x, double y) {
		this.alto = 65;
		this.ancho = 50;
		this.x = x;
		this.y = y;
		this.velocidad = 5;
		this.img = Herramientas.cargarImagen("vikingaidle.gif");
		this.estaSaltando = false;
		this.estaQuieta = true;
		this.direccion = true;
		this.imagenDelEscudo = Herramientas.cargarImagen("escudo.png");

	}

	public void dibujar(Entorno e) {
		e.dibujarImagen(img, x, y, 0, 0.20);
		if (!estaQuieta) {
			if (direccion) {
				img = Herramientas.cargarImagen("vikingarun.gif");

			} else {
				img = Herramientas.cargarImagen("vikingaizq.gif");

			}
			if (estaSaltando && direccion) {
				img = Herramientas.cargarImagen("vikingajump.gif");
			} else if (estaSaltando && !direccion) {
				img = Herramientas.cargarImagen("vikingajumpizq.gif");
			}
		} else {
			if (direccion) {
				img = Herramientas.cargarImagen("vikingaidle.gif");
			} else {
				img = Herramientas.cargarImagen("vikingaidleizq.gif");
			}
		}
	}

	public void moverHaciaIzquierda(Entorno e) {
		if (x > ancho / 2) {
			x -= velocidad;
			direccion = false;
		}
	}

	public void moverHaciaDerecha(Entorno e) {
		if (x < e.ancho() - ancho / 2) {
			x += velocidad;
			direccion = true;
		}
	}

	public void saltar(Entorno e) {
		y -= 9;
		estaSaltando = true;
	}

	public boolean banderaDeSalto(Piso[] pisos) {
		if (y - alto / 2 <= 0) {
			return false;
		}
		if (pisos[0].tocaPiso(x, y - alto / 2) || pisos[1].tocaPiso(x, y - alto / 2)
				|| pisos[2].tocaPiso(x, y - alto / 2) || pisos[3].tocaPiso(x, y - alto / 2)
				|| pisos[4].tocaPiso(x, y - alto / 2) || pisos[5].tocaPiso(x, y - alto / 2)) {
			estaSaltando = false;
			return false;
		}
		return true;
	}

	// !estasParadeEnUnPiso()
	// CHECKME
	public boolean banderaDeCaida(Piso[] pisos) {
		if (pisos[0].chocasteParteSuperiorCon(x, y + alto / 2) || pisos[1].chocasteParteSuperiorCon(x, y + alto / 2)
				|| pisos[2].chocasteParteSuperiorCon(x, y + alto / 2)
				|| pisos[3].chocasteParteSuperiorCon(x, y + alto / 2)
				|| pisos[4].chocasteParteSuperiorCon(x, y + alto / 2)
				|| pisos[5].chocasteParteSuperiorCon(x, y + alto / 2)) {
			return false;
		}
		return true;
	}

	public void caer(Entorno e) {
		y = y + 3;
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

	public boolean tuEscudoChocoConUnLaser(Laser laser) {
		return x - 60 < laser.getX()  && x + 60 > laser.getX() && y > laser.getY() - laser.getAlto()
				&& y < laser.getY() + laser.getAlto();
	}
	public void quieta(boolean noSeMueve) {
		if (noSeMueve) {
			estaQuieta = true;
		} else {
			estaQuieta = false;
		}
	}

	public Rayo disparar() {
		rayo = new Rayo(x, y, direccion);
		return rayo;
	}

	public boolean ChoqueRaptor(Velociraptor raptor) { // chocasteConUnRaptor
		return x < raptor.getX() + raptor.getAncho() - ancho / 2 && x > raptor.getX() - raptor.getAncho() + ancho / 2
				&& y < raptor.getY() + raptor.getAlto() / 2 && y > raptor.getY() - raptor.getAlto();
	}

	public void respawn() {
		x = 20;
		y = 550;
	}

	public boolean recuperasteCommodore(Commodore commodore) {
		return x < commodore.getX() + ancho / 2 && x > commodore.getX() - ancho / 2
				&& y > commodore.getY() - commodore.getTamaño() && y < commodore.getY() + commodore.getTamaño();
	}

	public boolean chocasteUnLaser(Laser laser) {
		return x < laser.getX() + ancho / 2 && x > laser.getX() - ancho / 2 && y > laser.getY() - laser.getAlto()
				&& y < laser.getY() + laser.getAlto();
	}

}
