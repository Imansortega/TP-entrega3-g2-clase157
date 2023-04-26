package ar.utn.ap.pronosticos;
// Deshabilitado. Reemplazado por SubeDataSql
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//public class SubeData {
//	public Collection<Partido> cargaResultados(String args0) {
//
//		// Indices para resultados
//		int idxRonda = 0; // Ronda
//		int idxEquipo1 = 1; // equipo1
//		int idxEquipo2 = 4; // equipo2
//		int idxGolesEquipo1 = 2; // goles equipo1
//		int idxGolesEquipo2 = 3; // goles equipo2
//		int idxFase = 5; // Fase
//		Collection<Partido> listaPartidos = new ArrayList<Partido>();
//		Path pathResultados = Paths.get(args0);
//		List<String> lineasResultados = null;
//		// Leer resultados
//		try {
//			lineasResultados = Files.readAllLines(pathResultados);
//		} catch (IOException e) {
//			System.out.println("No se pudo leer la linea de resultados...");
//			System.out.println(e.getMessage());
//			System.exit(1);
//		}
//		boolean primera = true;
//		for (String lineaResultado : lineasResultados) {
//			if (primera) {
//				primera = false;
//			} else {
//				// Aquí se instancia y "llena" partidos
//				String[] campos = lineaResultado.split(",");
//				if (!Util.checkLine(lineaResultado, campos.length, idxGolesEquipo1, idxGolesEquipo2)) System.exit(1);
//				Equipo equipo1 = new Equipo(campos[idxEquipo1]);
//				Equipo equipo2 = new Equipo(campos[idxEquipo2]);
//				Partido elPartido = new Partido(equipo1, equipo2);
//				elPartido.setGolesEq1(Integer.parseInt(campos[idxGolesEquipo1]));
//				elPartido.setGolesEq2(Integer.parseInt(campos[idxGolesEquipo2]));
//				elPartido.setRonda(Integer.parseInt(campos[idxRonda]));
//				elPartido.setFase(Integer.parseInt(campos[idxFase]));
//				
//				listaPartidos.add(elPartido);
//			}
//		}
//		
//		return listaPartidos;
//	}
//}
