package ar.utn.ap.pronosticos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@SuppressWarnings("unused")
public class Config {
	
	private static final Path CONFIG = Paths.get("src\\main\\java\\ar\\utn\\ap\\pronosticos\\config.ini");
	private static int CANTIDAD_DE_EQUIPOS;
	private static int CANTIDAD_DE_FASES; // ida y vuelta	
	private static Path ARCH_RESULTADOS;
	private static Path ARCH_PRONOSTICOS;
	private static int PUNTAJE_ACIERTO;
	private static int PUNTAJE_SIMPLE;
	private static int PUNTAJE_BONIFICADO_GANADOR;
	private static int PUNTAJE_BONIFICADO_EMPATE;
	private static int PUNTAJE_BONIFICADO_PERDEDOR;
	private static int PLUS_RONDA;
	private static int PLUS_FASE;
	public static String DB_URL;
	public static String USER;
	public static String CONTRASEÑA;
	private static List <String> lineasResultados = null;
	
	public static int getCANTIDAD_DE_EQUIPOS() {
		return CANTIDAD_DE_EQUIPOS;
	}

	public static int getCANTIDAD_DE_FASES() {
		return CANTIDAD_DE_FASES;
	}

	public static Path getARCH_RESULTADOS() {
		return ARCH_RESULTADOS;
	}

	public static Path getARCH_PRONOSTICOS() {
		return ARCH_PRONOSTICOS;
	}

	public static int getPUNTAJE_ACIERTO() {
		return PUNTAJE_ACIERTO;
	}

	public static int getPUNTAJE_SIMPLE() {
		return PUNTAJE_SIMPLE;
	}

	public static int getPUNTAJE_BONIFICADO_GANADOR() {
		return PUNTAJE_BONIFICADO_GANADOR;
	}

	public static int getPUNTAJE_BONIFICADO_EMPATE() {
		return PUNTAJE_BONIFICADO_EMPATE;
	}

	public static int getPUNTAJE_BONIFICADO_PERDEDOR() {
		return PUNTAJE_BONIFICADO_PERDEDOR;
	}

	public static int getPLUS_RONDA() {
		return PLUS_RONDA;
	}

	public static int getPLUS_FASE() {
		return PLUS_FASE;
	}

	public static String getDB_URL() {
		return DB_URL;
	}

	public static String getUSER() {
		return USER;
	}

	public static String getCONTRASEÑA() {
		return CONTRASEÑA;
	}

	public static List<String> getLineasResultados() {
		return lineasResultados;
	}

	public static void cargaConfig() {
		// Leer resultados
		try {
			lineasResultados = Files.readAllLines(CONFIG);
			for (String lineaResultado : lineasResultados) {
				String[] campos = lineaResultado.split(",");

				switch (campos[0]) {

				case "[CANTIDAD_DE_EQUIPOS]":
					CANTIDAD_DE_EQUIPOS = Integer.parseInt(campos[1]);
					break;
				case "[CANTIDAD_DE_FASES]":
					CANTIDAD_DE_FASES = Integer.parseInt(campos[1]);
					break;
				case "[ARCH_RESULTADOS]":
					ARCH_RESULTADOS = Paths.get(campos[1]);
					break;
				case "[ARCH_PRONOSTICOS]":
					ARCH_PRONOSTICOS = Paths.get(campos[1]);
					break;
				case "[PUNTAJE_ACIERTO]":
					PUNTAJE_ACIERTO = Integer.parseInt(campos[1]);
					break;
				case "[PUNTAJE_SIMPLE]":
					PUNTAJE_SIMPLE = Integer.parseInt(campos[1]);
					break;
				case "[PUNTAJE_BONIFICADO_GANADOR]":
					PUNTAJE_BONIFICADO_GANADOR = Integer.parseInt(campos[1]);
					break;
				case "[PUNTAJE_BONIFICADO_EMPATE]":
					PUNTAJE_BONIFICADO_GANADOR = Integer.parseInt(campos[1]);
					break;
				case "[PUNTAJE_BONIFICADO_PERDEDOR]":
					PUNTAJE_BONIFICADO_PERDEDOR = Integer.parseInt(campos[1]);
					break;
				case "[PLUS_RONDA]":
					PLUS_RONDA = Integer.parseInt(campos[1]);
					break;
				case "[PLUS_FASE]":
					PLUS_FASE = Integer.parseInt(campos[1]);
					break;
				case "[DB_URL]":
					DB_URL = campos[1];
					break;
				case "[USER]":
					USER = campos[1];
					break;
				case "[CONTRASEÑA]":
					CONTRASEÑA = campos[1];
					break;
				default:
					System.out.println("Default");
					break;
				}
			}
					
		} catch (IOException e) {
			System.out.println("No se pudo leer la linea de resultados...");
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
	

//	public static void main(String[] args) {
//		Config.cargaConfig();
//		System.out.println("Cantidad de equipos: " + CANTIDAD_DE_EQUIPOS);
//		System.out.println("Cantidad de fases: " + CANTIDAD_DE_FASES);
//		System.out.println("Archivo resultados: " + ARCH_RESULTADOS);
//	}

}
