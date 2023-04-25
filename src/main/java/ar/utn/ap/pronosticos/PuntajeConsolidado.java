package ar.utn.ap.pronosticos;

public class PuntajeConsolidado {
	private String nombre;
	private int puntaje;
	private int cantAciertos;
	private int rondasCompletasAcertadas;
	private int fasesCompletasAcertadas;
	
	public PuntajeConsolidado() {
		this.nombre = null;
		this.puntaje = 0;
		this.cantAciertos = 0;
		this.rondasCompletasAcertadas =0; // Se incrementa solo si se acierta todos los resultados de la ronda
		this.fasesCompletasAcertadas = 0; // Se incrementa solo si se acierta todos los resultados de la fase
	}
	
	public int getRondasCompletasAcertadas() {
		return rondasCompletasAcertadas;
	}
	public int getFasesCompletasAcertadas() {
		return fasesCompletasAcertadas;
	}
	
	public void setRondasCompletasAcertadas(int rondasCompletasAcertadas) {
		this.rondasCompletasAcertadas = rondasCompletasAcertadas;
	}
	public void setFasesCompletasAcertadas(int fasesCompletasAcertadas) {
		this.fasesCompletasAcertadas = fasesCompletasAcertadas;
	}
	public String getNombre() {
		return nombre;
	}
	public int getPuntaje() {
		return puntaje;
	}
	public int getCantAciertos() {
		return cantAciertos;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	public void setCantAciertos(int cantAciertos) {
		this.cantAciertos = cantAciertos;
	}	

}
