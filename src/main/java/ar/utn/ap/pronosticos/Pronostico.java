package ar.utn.ap.pronosticos;

public class Pronostico {
	private Partido partido; // Partido a evaluar en funcion de los goles que tiene "adentro"
	private Equipo equipo;
	/*
	 * resultadoProno: Resultado del pronóstico con el cual contrastar el resultado
	 * de "partido" a fin de obtener los puntos
	 */
	private EnumResultado resultadoProno;

	public Pronostico(Partido partido, Equipo equipo, EnumResultado resultado) {
		super();
		this.partido = partido;
		this.equipo = equipo;
		this.resultadoProno = resultado;
	}

	public Partido getPartido() {
		return this.partido;
	}

	public Equipo getEquipo() {
		return this.equipo;
	}

	public EnumResultado getResultado() {
		return this.resultadoProno;
	}

	public int puntos() {
		EnumResultado resultadoReal = this.partido.resultado(this.equipo);
		if (this.resultadoProno.equals(resultadoReal)) {
			return 1;
		} else {
			return 0;
		}
	}
}
