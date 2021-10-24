package juego;

import java.awt.Color;
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
	private int velocidady; // para las caidas
	private int piso;
	private boolean direccion; //true=derecha false=izquierda
	private Image img;
	private Image imgescudo;
	private boolean subir;		//agrego como bandera de salto de piso

	public Vikinga(int alto, int ancho, int vidas, double x, double y, int velocidad, int velocidady, int piso, boolean subir) {
		this.alto = alto;
		this.ancho = ancho;
		this.vidas = vidas;
		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
		this.velocidady = velocidady; // caidas
		this.piso = piso;
		this.img = Herramientas.cargarImagen("per.png");
		this.imgescudo = Herramientas.cargarImagen("");
		this.subir = subir;
		this.direccion=true;
		//flag salto de piso
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

	public int getVelocidady() { // caida
		return velocidady;
	}

	public int getPiso() {
		return piso;
	}
	
	public boolean getSubir() {
		return subir;	
	}

	public void quePiso() {
		if (y > 460 && y < 600) {			// Sugerencia: pasarlo a una clase
			piso = 1;
		}
		if (y > 380 && y < 470) {
			piso = 2;
		}
		if (y > 300 && y < 400) {
			piso = 3;
		}
		if (y > 200 && y < 300) {
			piso = 4;
		}
		if (y > 100 && y < 200) {
			piso = 5;
		}
		if (y > 0 && y < 100) {
			piso = 6;
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
			direccion=false;
		}
	}

	public void moverHaciaDerecha(Entorno e) {
		if (x < e.ancho() - ancho / 2) {
			x += velocidad;
			img = Herramientas.cargarImagen("per.png");
			direccion=true;
		}
	}	
	public void saltar(Entorno e) {												//salto
		if (x > e.ancho() - 100 && piso == 1 ){						//(el valor de Y no supera una X de la clase piso proximo) {			//(x < e.ancho() - ancho / 2 && y > 460) {							
			y -= velocidady;
			x -= 3;
		
		//	img = Herramientas.cargarImagen("img.png");				
			{
		//	piso = 2;		
		}
		if (x < 85 && piso == 2) {
			y -= velocidady;
			x += velocidad;
		//	y = 400 - alto / 2 - 10;
		//	x = 120;
		}
		if (x > e.ancho() - 85 && piso == 3) {
			y -= velocidady;
			x -= velocidad;
		//	y = 300 - alto / 2 - 10;
		//	x = e.ancho() - 120;
		}
		if (x < 85 && piso == 4) {
			y -= velocidady;
			x += velocidad;	
		//	y = 200 - alto / 2 - 10;
		//	x = 120;
		}
		if (x > e.ancho() - 85 && piso == 5) {
			y -= velocidady;
			x -= velocidad;
		//	y = 100 - alto / 2 - 10;
		//	x = e.ancho() - 120;
		}
		
		subir= false;
		
		//si subir(zona de salto) es verdadero y  
		
	}
	}
	

	public void caer(Entorno e) {
		if (x > e.ancho() - 85 && piso == 2) {
			y =465 + alto;
		}
		if (x < 85 && piso == 3) {
			y = 395 + alto;
		}
		if (x > e.ancho() - 85 && piso == 4) {
			y = 295 + alto;

		}
		if (x < 85 && piso == 5) {
			y = 195 + alto;
		}
		if (x > e.ancho() - 85 && piso == 6) {
			y = 95 + alto;
		}
	}
	
	public void escudo(Entorno e) {
		//e.dibujarImagen(imgescudo, x, y, 0, 0.20);
		if(direccion) {
			e.dibujarRectangulo(x+40, y, 5, alto, 0, Color.cyan);
		}
		else {
			e.dibujarRectangulo(x-40, y, 5, alto, 0, Color.cyan);
		}
		
	}
	

}
