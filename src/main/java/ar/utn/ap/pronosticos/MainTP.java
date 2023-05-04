package ar.utn.ap.pronosticos;

import java.util.List;

/*
 * TP Integrador, entrega 3, V 1.1 
 * Grupo 2, clase 157
 * Fabiana Zamboni - Ignacio Manso
 */
public class MainTP {
	public static final String VERSION = "TP Integrador, entrega 3, V 1.1";

	public static void main(String[] args) {
			
		// Carga configuracion desde config.ini
		Config.cargaConfig();
		
		// Declaración de lista e instanciación de la clase Procesador
		List<Aciertos> estadisticaUsuarios;
		Procesador procesa = new Procesador();
		
		// Obtiene e imprime listado de apuestas
		estadisticaUsuarios = procesa.procesaPronostico();
		Util.imprimeEstadisticaUsuarios(estadisticaUsuarios);	
		
		// Obtiene en imprime puntaje obtenido por los apostadores
		Util.imprimeListaPuntajeConsolidado(procesa.calculaPuntos(estadisticaUsuarios));
	}
}
