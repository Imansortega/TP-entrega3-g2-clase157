package ar.utn.ap.pronosticos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Procesador {
	public static final String DB_URL = Config.getDB_URL();
	public static final String USER = Config.getUSER();
	public static final String PASS = Config.getCONTRASEÑA();

	public List<Aciertos> procesaPronostico() {

		int idxNombre = 0; // Indice Nombre en pronóstico
		int idxEquipo1Prono = 1; // Indice equipo1 en pronóstico
		int idxEquipo2Prono = 5; // Indice equipo2 en pronóstico
		int localProno = 2; // Indice de posición local en pronostico
		int empateProno = 3; // Indice de posición empate en pronostico
		int visitanteProno = 4; // Indice de posición visitante en pronostico
		int ronda = 0; // Indice de ronda en resultados
		int fase = 0; // Indice de fase en resultados
		String nombre = ""; // Nombre del apostador que está en posición 0 del pronóstico
		List<Aciertos> estadisticaUsuarios = new ArrayList<Aciertos>(); // *
		List<String> lineasPronostico = null;

		SubeDataSql resultadosSql = new SubeDataSql();
		Collection<Partido> listaPartidos = resultadosSql.cargaResultadosSql(DB_URL, USER, PASS);
		lineasPronostico = resultadosSql.cargaPronosticosSql(DB_URL, USER, PASS);

		for (String lineaPronostico : lineasPronostico) {
			lineaPronostico.replace("\r", "");

			String[] camposProno = lineaPronostico.split(",");
			Equipo equipo1Prono = new Equipo(camposProno[idxEquipo1Prono]);
			// Pongo un ".replace("\r", "")" para eliminar un "\r" al final del string
			Equipo equipo2Prono = new Equipo(camposProno[idxEquipo2Prono].replace("\r", ""));
			nombre = camposProno[idxNombre];
			Partido partidoAEvaluar = null;

			// Busco los partidos de la apuesta específica dentro de la colección
			// listaPartidos
			for (Partido partidoCol : listaPartidos) {
				if (partidoCol.getEquipo1().getNombre().equals(equipo1Prono.getNombre())
						&& partidoCol.getEquipo2().getNombre().equals(equipo2Prono.getNombre())) {
					partidoAEvaluar = partidoCol;
					ronda = partidoCol.getRonda();
					fase = partidoCol.getFase();
				}
			}
			EnumResultado apuesta = null;
			if ("X".equals(camposProno[localProno])) {
				apuesta = EnumResultado.GANADOR;
			}
			if ("X".equals(camposProno[empateProno])) {
				apuesta = EnumResultado.EMPATE;
			}
			if ("X".equals(camposProno[visitanteProno])) {
				apuesta = EnumResultado.PERDEDOR;
			}
			// Evaluo si hubo acierto
			if (apuesta.equals(partidoAEvaluar.resultado(partidoAEvaluar.getEquipo1()))) {

// 						****** IMPORTANTE !!! *******
// no paso el enum "ACIERTO" O "NOACIERTO" en "miApuesta", ya que si llegue aca sabemos
// que hubo acierto.
// Ahora hay que guardar info del usuario de nombre, tipo de acierto, osea
// "apuesta" (ganador, empate, perdedor), rondaX, faseY. ---> Está en estadisticaUsuarios !!!

				Aciertos miApuesta = new Aciertos(nombre, apuesta, ronda, fase); // *
				estadisticaUsuarios.add(miApuesta);
			}
			Util.ordenaTabla(estadisticaUsuarios);
		}
		return estadisticaUsuarios;
	}

	/**
	 * -- calculaPuntos -- -
	 * 
	 * @param estadisticaUsuarios
	 * @return
	 */
	@SuppressWarnings("unused")
	public ArrayList<PuntajeConsolidado> calculaPuntos(List<Aciertos> estadisticaUsuarios) {

		ArrayList<PuntajeConsolidado> listaPuntajeConsolidado = new ArrayList<PuntajeConsolidado>(); // Se llena aca
		Reglas misReglas = new Reglas(); // Para usar reglas
		PuntajeConsolidado objetito = new PuntajeConsolidado(); // objetito está para no llamar a misReglas mas de una
																// vez.
		int idx = 0; // indice para nueva tabla listaPuntajeConsolidado
		Util.ordenaTabla(estadisticaUsuarios); // Usar en caso de querer la tabla ordenada

		for (Aciertos p : estadisticaUsuarios) {

			switch (buscaUsuario(p.getNombre(), listaPuntajeConsolidado)) {

			case "Esta": { // Actualizar
				listaPuntajeConsolidado.set(idx, misReglas.actualizaObjeto(listaPuntajeConsolidado.get(idx)));
				break;
			}
			case "No esta": { // Crear nuevo
				objetito = misReglas.armaObjetoInicial(p);
				listaPuntajeConsolidado.add(objetito); // Añado a lista (idx aumenta en uno también)
				idx = listaPuntajeConsolidado.indexOf(objetito);
				// Importante el idx anterior !!!. Debe estar para el siguiente case, que va a
				// ser "está".
				// Esto se puede hacer con un if para el primer objeto y luego ++idx, etc
				break;
			}
			default: {
				System.out.println("Default");
				break;
			}
			}
		}
		// No hay ningun ganador
		if (listaPuntajeConsolidado.isEmpty()) {
			System.out.println("No hay ganadores !");
		}

		// Código entre brackets para distinguir del resto
		// Actualizamos con los puntos que se obtienen de los resultados tipo ronda/fase
		// completa
		{
			int loaderRonda = 0;
			int loaderFase = 0;
			for (PuntajeConsolidado q : listaPuntajeConsolidado) {
				loaderRonda = Util.devuelvePartidosAcertadosEnRonda(q.getNombre(), estadisticaUsuarios);
				loaderFase = Util.devuelvePartidosAcertadosEnFase(q.getNombre(), estadisticaUsuarios);
				q.setRondasCompletasAcertadas(loaderRonda);
				q.setFasesCompletasAcertadas(loaderFase);
				Ultima_llamada: misReglas.actualizaObjeto(q);
			}
		}

		return listaPuntajeConsolidado;
	}

	/**
	 * -- buscaUsuario -- Recorre la tabla listaPuntajeConsolidado que está en
	 * construcción para ver si existe el usuario especificado en name con el
	 * objetivo de introducir un nuevo registro o actulizar el existente.
	 * 
	 * @param nombre
	 * @param listaPuntajeConsolidado
	 * @return
	 */
	public String buscaUsuario(String nombre, ArrayList<PuntajeConsolidado> listaPuntajeConsolidado) {

		for (PuntajeConsolidado q : listaPuntajeConsolidado) {
			if (q.getNombre().equals(nombre)) {
				return "Esta"; // El nombre existe ---> modificarlo
			}
		}
		return "No esta"; // El nombre no existe, hay que crearlo
	}
}
