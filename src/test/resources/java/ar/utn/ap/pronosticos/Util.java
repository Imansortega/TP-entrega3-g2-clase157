package ar.utn.ap.pronosticos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Util {
/**
 * -- checkline -- Checkea que el número de campos sea el correcto y que no haya
 * un numero no entero. Si hay decimales vienen con punto. Con coma imposible
 * pues el split lo remueve. Si aún así pasara una coma, la cosa se detecta por
 * número incorrecto de campos. Los strings no numéricos también se detectan.
 * @param line,      la línea con los campos delimitados por coma.
 * @param numFields, la cantidad de campos que tiene que haber en "line"
 * @param idx3,      idx4 son las columnas donde están los campos a evaluar.
 * @dependencias No tiene
 * @return boolean
 */
	public static boolean checkLine(String line, int numFields, int idx3, int idx4) {
		String laLinea[] = line.split(",");
		if (laLinea.length != numFields) {
			System.out.println("Incorrecto número de campos");
			return false;
		}
		if (laLinea[idx3] != null && laLinea[idx3].matches("[0-9]+") && laLinea[idx4] != null
				&& laLinea[idx4].matches("[0-9]+")) {
		} else {
			System.out.println("Número de goles no entero");
			return false;
		}

		return true;
	}

/*
 * -- imprimePartido --
 */
	public static void imprimePartido(Partido partido) {
		System.out.println("Equipo1: " + partido.getEquipo1().getNombre() + 
				" Equipo2: " + partido.getEquipo2().getNombre());

		System.out.println("Goles1: " + partido.getGolesEq1() + " Goles2: " + partido.getGolesEq2());
		System.out.println("Ronda: " + partido.getRonda() + " Fase: " + partido.getFase());
		System.out.println("----------------------");
	}

/*
 * -- imprimeEstadisticaUsuarios --
 */
	public static void imprimeEstadisticaUsuarios(Collection<Aciertos> estadisticaUsuarios) {
		System.out.println("EstadisticaUsuarios");
		System.out.println("*******************");
		for (Aciertos p : estadisticaUsuarios) {			
			System.out.print("Nombre: " + p.getNombre());
			System.out.print(" Apuesta: " + p.getResultadoApuesta());
			System.out.print(" Ronda: " + p.getRonda());
			System.out.println(" Fase: " + p.getFase());
		}
	}
	
	public static void imprimeListaPuntajeConsolidado(ArrayList<PuntajeConsolidado> listaPuntajeConsolidado) {
		System.out.println("ListaPuntajeConsolidado");
		System.out.println("***********************");
		for (PuntajeConsolidado p : listaPuntajeConsolidado) {
			System.out.print("Nombre: " + p.getNombre());
			System.out.print(" Puntaje: " + p.getPuntaje());
			System.out.print(" cantAciertos: " + p.getCantAciertos());
			System.out.print(" Rondas-Pleno : " + p.getRondasCompletasAcertadas());
			System.out.println(" Fases-Pleno : " + p.getFasesCompletasAcertadas());
		}		
	}
	
/**
 * 
 * Comparator para ordenar la lista de objetos "estadisticaUsuarios" en función del nombre.
 * Revisar si estadisticaUsuarios está poblada al llamar a este método...
 * @param estadisticaUsuarios
 */
	public static void ordenaTabla(List<Aciertos> estadisticaUsuarios) {
		
		Collections.sort(estadisticaUsuarios, new Comparator <Aciertos>() {
		@Override
		public int compare(Aciertos o1, Aciertos o2) {
			
			if (o1.getNombre().compareToIgnoreCase(o2.getNombre()) < 0) { 
				return -1; 
			} else if (o1.getNombre().compareToIgnoreCase(o2.getNombre()) > 0){
				return 1; 
			} else 
				return 0;				
		}		
	}
	);
		
	}
	
	public static void ordenaTablaPorRonda(List<Aciertos> estadisticaUsuarios) {

		Collections.sort(estadisticaUsuarios, new Comparator<Aciertos>() {
			@Override
			public int compare(Aciertos o1, Aciertos o2) {

				if (o1.getRonda() < (o2.getRonda())) {
					return -1;
				} else if (o1.getRonda() > (o2.getRonda())) {
					return 1;
				} else
					return 0;
			}
		});
	}

	public static void ordenaTablaPorFase(List<Aciertos> estadisticaUsuarios) {

		Collections.sort(estadisticaUsuarios, new Comparator<Aciertos>() {
			@Override
			public int compare(Aciertos o1, Aciertos o2) {

				if (o1.getFase() < (o2.getFase())) {
					return -1;
				} else if (o1.getFase() > (o2.getFase())) {
					return 1;
				} else
					return 0;
			}
		});
	}
	
	public static int fixture(int nroEquipos) {
		int partidosPorFecha = nroEquipos/2; // O número de partidos por ronda
//		int nroTotalPartidos = nroEquipos*(nroEquipos-1) / 2; // No borrar, para futuros usos!!
//		int nroRondas = nroTotalPartidos/partidosPorFecha; // No borrar, para futuros usos!!
//		int partidosPorFechaIdaVuelta = nroEquipos/2; // No borrar, para futuros usos!!
//		int nroTotalPartidosIdaVuelta = nroEquipos*(nroEquipos-1); // No borrar, para futuros usos!!
//		int nroRondasIdaVuelta = nroTotalPartidos/partidosPorFecha; // No borrar, para futuros usos!!	
		return partidosPorFecha;
	}
	
	public static int devuelvePartidosAcertadosEnRonda (String nombre, List<Aciertos> estadisticaUsuarios ) {
		int nroRondas = (Config.getCANTIDAD_DE_EQUIPOS()-1)*2; // 6
		int partidosPorRonda = Config.getCANTIDAD_DE_EQUIPOS() / 2; // 2
		int ptosRonda = 0;
		int cantAciertosRondasCompletas = 0;
		ordenaTablaPorRonda(estadisticaUsuarios);
		
		for (int ronda = 1; ronda <= nroRondas; ++ronda) {
			for (Aciertos b : estadisticaUsuarios) {
				if (b.getNombre().equals(nombre) && b.getRonda() == ronda) {
					++ptosRonda;
				} else {
					cantAciertosRondasCompletas = ptosRonda / partidosPorRonda + cantAciertosRondasCompletas; // Entero, corta decimales
					ptosRonda = 0;
				}
			}																								
		}
		return cantAciertosRondasCompletas ;
	}
	
	public static int devuelvePartidosAcertadosEnFase (String nombre, List<Aciertos> estadisticaUsuarios ) {
		int partidosPorFase = Config.getCANTIDAD_DE_EQUIPOS()*(Config.getCANTIDAD_DE_EQUIPOS()-1)/2; // 6			
		int ptosFase = 0;
		int cantAciertosFasesCompletas = 0;
		int nroFases = Config.getCANTIDAD_DE_FASES();
		ordenaTabla(estadisticaUsuarios);
		
		for (int fase = 1; fase <= nroFases; ++fase) {
			for (Aciertos b : estadisticaUsuarios) {
				if (b.getNombre().equals(nombre) && b.getFase() == fase) {
					++ptosFase;
				} else {
					cantAciertosFasesCompletas = ptosFase / partidosPorFase + cantAciertosFasesCompletas; // Entero, corta decimales
					ptosFase = 0;
				}
			}																								
		}
		return cantAciertosFasesCompletas ;
	}
	
}
