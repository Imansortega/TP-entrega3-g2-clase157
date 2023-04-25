package ar.utn.ap.pronosticos;

public class Aciertos {
	private String nombreApostador;
	private EnumResultado miApuesta;
	private int ronda;
	private int fase;
	private int nroRondas; // Número total de Rondas
	private int nroFases; // Número total de Fases
	
	public Aciertos() {
	}
	
	public Aciertos(String nombreApostador, EnumResultado resultadoApuesta, int ronda, int fase) {
		this.nombreApostador = nombreApostador;
		this.miApuesta = resultadoApuesta;
		this.ronda = ronda;
		this.fase = fase;
	}
	
	public Aciertos(String nombreApostador, EnumResultado resultadoApuesta, int ronda, int fase, int nroRondas, int nroFases) {
		this.nombreApostador = nombreApostador;
		this.miApuesta = resultadoApuesta;
		this.ronda = ronda;
		this.fase = fase;
		this.nroRondas = nroRondas;
		this.nroFases = nroFases;
	}

	public String getNombre() {
		return nombreApostador;
	}

	public void setNombre(String nombre) {
		this.nombreApostador = nombre;
	}

	public EnumResultado getResultadoApuesta() {
		return miApuesta;
	}

	public void setResultadoApuesta(EnumResultado resultadoApuesta) {
		this.miApuesta = resultadoApuesta;
	}

	public int getRonda() {
		return ronda;
	}

	public void setRonda(int ronda) {
		this.ronda = ronda;
	}
	public int getFase() {
		return fase;
	}

	public void setFase(int fase) {
		this.fase = fase;
	}

	public int getNroRondas() {
		return nroRondas;
	}
	public int getNroFases() {
		return nroFases;
	}

}
