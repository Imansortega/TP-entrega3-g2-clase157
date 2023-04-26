package ar.utn.ap.pronosticos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.Test;

/*
 * TP-Fork V 2.0. - Test !!! 
 */
public class MainTP {
	@Test
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
		boolena caca = true;
		assert.assertTrue(caca);
	}
}
