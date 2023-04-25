package ar.utn.ap.pronosticos;

/*
 * -- Reglas --
 * Si llamaron a esta clase es que hubo acierto
 */
public class Reglas {
	int puntaje = 0;
	int aciertos = 0;
	Aciertos miAcierto;

	public Reglas() {
	}

	/**
	 * -- aciertoSimple -- Se puede llamar en cualquier momento del procesamiento de
	 * las apuestas
	 * 
	 * @return: Devuelve 1 punto (se puede cambiar) y no hace nada mas.
	 */
	public int puntajeSimple() {
		return Config.getPUNTAJE_SIMPLE();
	}
	

	/**
	 * -- aciertoBonificado -- Se puede llamar en cualquier momento del
	 * procesamiento de las apuestas. Paga a piaccere.
	 * 
	 * @return: Devuelve en función de la apuesta hecha, ganador, empate o perdedor.
	 */
	public int puntajeBonificado(Aciertos miAcierto) {

		switch (miAcierto.getResultadoApuesta()) {

		case GANADOR:
			return Config.getPUNTAJE_BONIFICADO_GANADOR();
		case EMPATE:
			return Config.getPUNTAJE_BONIFICADO_EMPATE();
		case PERDEDOR:
			return Config.getPUNTAJE_BONIFICADO_PERDEDOR();
		default:
			break;
		}
		return 0;
	}
	

	/**
	 * -- plusRonda -- NO SE PUEDE llamar en cualquier momento del procesamiento de
	 * las apuestas. Tiene haber finalizado y consolidado en cuantos aciertos hay en
	 * rondas y fases. 
	 * Corrección, si se puede mientras el valor sea cero, como pasa en actualizaObjeto().
	 * @return; Algún puntaje extra
	 */
	public int plusRonda() {
		return Config.getPLUS_RONDA();
	}
	

	/**
	 * -- plusFase -- NO SE PUEDE llamar en cualquier momento del procesamiento de
	 * las apuestas. Tiene haber finalizado y consolidado en cuantos aciertos hay en
	 * rondas y fases. 
	 * Corrección, si se puede mientras el valor sea cero, como pasa en actualizaObjeto().
	 * @return; Algún puntaje extra
	 */
	public int plusFase() {
		return Config.getPLUS_FASE();
	}
	

	/**
	 * -- acierto -- Hasta nuevo cambio puntaje es igual a acierto. En general
	 * acierto = 1. Puntaje se puede cambiar. Es para el caso que el no acierto
	 * pague algo.... Se puede cambiar el puntaje a piaccere
	 * 
	 * @return acierto
	 */
	public int acierto() {
//		final int acierto = 1;
		return Config.getPUNTAJE_ACIERTO();
	}

	/**
	 * -- armaObjeto -- Se asigna un punto a puntaje y cantidad de aciertos pues si
	 * llegó acá es que hubo un acierto. Ojo !!! Cada vez que se llama a este método
	 * deja el objeto en Nombre, 1, 1 No incrementa nada, inicializa el objeto.
	 * 
	 * @param p
	 * @return
	 */
	public PuntajeConsolidado armaObjetoInicial(Aciertos p) {

		PuntajeConsolidado objeto = new PuntajeConsolidado();
		objeto.setNombre(p.getNombre());
		objeto.setPuntaje(puntajeSimple()-1);
		objeto.setCantAciertos(acierto()-1);
		return objeto;
	}

	/**
	 * -- actualizaObjeto -- Actualiza el objeto PuntajeConsolidado añadiendo los
	 * puntode puntajeSimple y acierto Ojo !!! Cada vez que se llama a este método
	 * incrementa los puntajes del objeto En caso de necesitar el objeto en mas de
	 * una oportunidad, crear un objeto temporario. Caso contrario estaría
	 * incrementando los contadores mas veces de lo necesario. Ojo !!! en la última
	 * llamada "Ultima_llamada" (ver label) ya están disponibles los datos de
	 * Aciertos de ronda y fase, entonces el puntaje final se actualiza. Antes los
	 * valores de ronda y fase están en cero y no aportan a la suma de puntos.
	 * 
	 * @param PuntajeConsolidado v
	 * @return PuntajeConsolidado v
	 */
	public PuntajeConsolidado actualizaObjeto(PuntajeConsolidado v) {
		// Puntaje nuevo + puntaje viejo
		v.setPuntaje(puntajeSimple() + +v.getRondasCompletasAcertadas() * plusRonda()
				+ v.getFasesCompletasAcertadas() * plusFase() + v.getPuntaje());
		v.setCantAciertos(acierto() + v.getCantAciertos());
		return v;
	}
}
