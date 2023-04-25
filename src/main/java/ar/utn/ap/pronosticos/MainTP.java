package ar.utn.ap.pronosticos;

import java.util.List;

/*
 * TP-entrega3-g2-clase157 
 */
public class MainTP {

	public static void main(String[] args) {
		
		// Carga configuracion desde config.ini
		Config.cargaConfig();
		
		// Declaración de lista e instanciación de la clase Procesador
		List<Aciertos> estadisticaUsuarios;
		Procesador procesa = new Procesador();
		
		// Obtiene e imprime listado de apuestas
		estadisticaUsuarios = procesa.procesaPronostico();
		Util.imprimeEstadisticaUsuarios(procesa.procesaPronostico());	
		System.out.println("--------------------------------------------");
		
		// Obtiene en imprime puntaje obtenido por los apostadores
		Util.imprimeListaPuntajeConsolidado(procesa.calculaPuntos(estadisticaUsuarios));
	}
}
