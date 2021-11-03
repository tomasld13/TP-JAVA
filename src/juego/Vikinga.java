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
//	private Rayo rayo;
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

	public boolean puedoSaltar(Piso[] pisos) {
		if (y - alto / 2 <= 0) {
			return false;
		}
		for (Piso p : pisos) {
			if (p.chocasteParteInferiorCon(x, y - alto / 2)) {
				estaSaltando = false;
				return false;
			}
		}
		return true;
	}

	public boolean meSaliDelPiso(Piso[] pisos) {
		for (Piso p : pisos) {
			if (p.chocasteParteSuperiorCon(x, y + alto / 2)) {
				return false;
			}
		}
		return true;
	}

	public void caer(Entorno e) {
		y = y + 3;
	}

	public void escudo(Entorno e) {
		if (direccion) {
			e.dibujarImagen(imagenDelEscudo, x + 60, y, 0, 0.07);
		} else {
			e.dibujarImagen(imagenDelEscudo, x - 60, y, 0, 0.07);
		}

	}

	public boolean tuEscudoChocoConUnLaser(Laser laser) {
		return x - 60 < laser.getX() && x + 60 > laser.getX() && y > laser.getY() - laser.getAlto()
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
		return new Rayo(x, y, direccion);
	}

	public boolean chocasteConUnRaptor(Velociraptor raptor) {
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

	public boolean agarrasteSalud(Salud salud) {
		return x < salud.getX() + ancho / 2 && x > salud.getX() - ancho / 2 && y > salud.getY() - salud.getTamaño()
				&& y < salud.getY() + salud.getTamaño();

	}
}
