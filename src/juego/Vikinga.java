package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Vikinga {
	private double x;
	private double y;

	private int alto;
	private int ancho;

	private int velocidad;

	private boolean direccion; // true=derecha false=izquierda
	private boolean estaQuieta;
	private boolean estaAgachada;

	private Image img;
	private Image imagenDelEscudo;

	public Vikinga(double x, double y) {
		this.alto = 65;
		this.ancho = 50;
		this.x = x;
		this.y = y;
		this.velocidad = 5;
		this.img = Herramientas.cargarImagen("vikingaidle.gif");
		this.estaQuieta = true;
		this.direccion = true;
		this.imagenDelEscudo = Herramientas.cargarImagen("escudo.png");
		this.estaAgachada = false;
	}

	public void dibujar(Entorno e) {
		if (!estaQuieta) {
			if (estaAgachada && direccion) {
				img = Herramientas.cargarImagen("vikingaAgachada.png");
			} else if (estaAgachada && !direccion) {
				img = Herramientas.cargarImagen("vikingaAgachadaIzq.png");
			}
			if (direccion && !estaAgachada) {
				img = Herramientas.cargarImagen("vikingarun.gif");
			} else if (!direccion && !estaAgachada) {
				img = Herramientas.cargarImagen("vikingaizq.gif");
			}
		} else {
			if (direccion) {
				img = Herramientas.cargarImagen("vikingaidle.gif");
			} else {
				img = Herramientas.cargarImagen("vikingaidleizq.gif");
			}
		}
		e.dibujarImagen(img, x, y, 0, 0.20);
	}

	public void moverHaciaIzquierda(Entorno e) {
		if (x > ancho / 2) {
			if (estaAgachada) {
				x -= velocidad / 2;
			} else {
				x -= velocidad;
			}
			direccion = false;
			estaAgachada=false;
		}
	}

	public void moverHaciaDerecha(Entorno e) {
		if (x < e.ancho() - ancho / 2) {
			if (estaAgachada) {
				x += velocidad / 2;
			} else {
				x += velocidad;
			}
			direccion = true;
			estaAgachada=false;
		}
	}

	public void saltar(Entorno e) {
		y -= 9;
	}

	public void agacharse() {
		estaAgachada = true;
	}

	public boolean puedoSaltar(Piso[] pisos) {
		if (y - alto / 2 <= 0) {
			return false;
		}
		for (Piso p : pisos) {
			if (p.chocasteParteInferiorCon(x, y - alto / 2)) {
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

	public void quedarseQuieta() {
		estaQuieta = true;
	}
	
	public void moverse() {
		estaQuieta = false;
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
