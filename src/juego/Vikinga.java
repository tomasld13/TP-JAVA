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

		this.direccion = false;
		this.imagenDelEscudo = Herramientas.cargarImagen("escudo.png");

	}

	public void dibujar(Entorno e) {
		e.dibujarImagen(img, x, y, 0, 0.20);
		
		if (direccion) {img = Herramientas.cargarImagen("vikingaizq.gif");	
		} else {
			img = Herramientas.cargarImagen("vikinga.gif");
		}
		if (estaQuieta) {
			img = Herramientas.cargarImagen("vikingaidel.gif");
		}
		
		if (estaSaltando && direccion) {
			img = Herramientas.cargarImagen("vikingajump.gif");
		} else {
			img = Herramientas.cargarImagen("vikingajumpizq.gif");
		}
		// e.dibujarTriangulo(x, y, alto, ancho, Math.PI / 2, Color.CYAN);
		// colisiones
		
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
			img = Herramientas.cargarImagen("vikingarun.gif");
			direccion = true;
		}
	}

	public void saltar(Entorno e) {
		y -= 9;
		estaSaltando = true;
	}

	// FIXME
	public boolean banderaDeSalto(Piso[] pisos) {
		if (pisos[0].tocaPiso(y - alto / 2, x) || pisos[1].tocaPiso(y - alto / 2, x)
				|| pisos[2].tocaPiso(y - alto / 2, x) || pisos[3].tocaPiso(y - alto / 2, x)
				|| pisos[4].tocaPiso(y - alto / 2, x) || pisos[5].tocaPiso(y - alto / 2, x)) {
			return false;
		}
		return true;
	}

	// !estasParadeEnUnPiso()
	// CHECKME
	public boolean banderaDeCaida(Piso[] pisos) {
		if (pisos[0].chocasteParteSuperiorCon(y + alto / 2, x) || pisos[1].chocasteParteSuperiorCon(y + alto / 2, x)
				|| pisos[2].chocasteParteSuperiorCon(y + alto / 2, x) || pisos[3].chocasteParteSuperiorCon(y + alto / 2, x)
				|| pisos[4].chocasteParteSuperiorCon(y + alto / 2, x) || pisos[5].chocasteParteSuperiorCon(y + alto / 2, x)) {
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
	
	public void disparar (Rayo rayo) {
		rayo = new Rayo(x, y, direccion);
	}

	public boolean ChoqueRaptor(Velociraptor raptor) {  // chocasteConUnRaptor
		return x < raptor.getX() + raptor.getAncho() - ancho / 2 && x > raptor.getX() - raptor.getAncho() + ancho / 2
				&& y < raptor.getY() + raptor.getAlto() / 2 && y > raptor.getY() - raptor.getAlto();
	}

	public void respawn() {
		x = 20;
		y = 550;
	}
	
	public boolean recuperasteCommodore(Commodore commodore) {
		return x < commodore.getX() + ancho / 2 && x > commodore.getX() - ancho / 2 && y > commodore.getY() - commodore.getTamaño()
		&& y < commodore.getY() + commodore.getTamaño();
	}

}
