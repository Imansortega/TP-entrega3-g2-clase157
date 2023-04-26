package ar.utn.ap.pronosticos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SubeDataSql {
	
	public Collection<Partido> cargaResultadosSql(String DB_URL, String USER, String PASS) {

		Connection conexion = null;
		Statement consulta = null;
		ArrayList<Partido> lineasResultados = new ArrayList<Partido>();

		try {
			// Abrir la conexión
			conexion = DriverManager.getConnection(DB_URL, USER, PASS);
			
			// Ejecutar una consulta
			consulta = conexion.createStatement();
			
			String sql = "SELECT Ronda, Equipo1, Goles1, Goles2, Equipo2, Fase FROM tpintegrador.resultados";
			
			// En la variable resultado obtendremos las distintas filas que nos devolvía la base
			ResultSet resultado = consulta.executeQuery(sql);
			// Obtener las distintas filas de la consulta
			while (resultado.next()) {

				Equipo miEquipo1 = new Equipo();
				Equipo miEquipo2 = new Equipo();
				Partido miPartido = new Partido();

				miEquipo1.setNombre(resultado.getString("Equipo1"));
				miEquipo2.setNombre(resultado.getString("Equipo2"));

				miPartido.setRonda(resultado.getInt("Ronda"));
				miPartido.setEquipo1(miEquipo1);
				miPartido.setGolesEq1(resultado.getInt("Goles1"));
				miPartido.setGolesEq2(resultado.getInt("Goles2"));
				miPartido.setEquipo2(miEquipo2);
				miPartido.setFase(resultado.getInt("Fase"));

				lineasResultados.add(miPartido);

			}

			// Cerrar la conexión con la base de datos
			resultado.close();
			consulta.close();
			conexion.close();
		} catch (SQLException se) {
			// Excepción ante problemas de conexión
			se.printStackTrace();
		} finally {
			// Esta sentencia es para que ante un problema con la base igual se cierren las
			// conexiones
			try {
				if (consulta != null)
					consulta.close();
			} catch (SQLException se2) {
			}
			try {
				if (conexion != null)
					conexion.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return lineasResultados;
	}

	public void imprimeResultados(int Ronda, String Equipo1, int Goles1, int Goles2, String Equipo2, int Fase) {
		// Mostrar los valores obtenidos
		System.out.print("Ronda: " + Ronda);
		System.out.print(", Equipo1: " + Equipo1);
		System.out.print(", Goles1: " + Goles1);
		System.out.print(", Goles2: " + Goles2);
		System.out.print(", Equipo2: " + Equipo2);
		System.out.println(" Fase: " + Fase);
	}

	public List<String> cargaPronosticosSql(String DB_URL, String USER, String PASS) {
		Connection conexion = null;
		Statement consulta = null;
		List<String> lineasPronostico = new ArrayList<String>();

		try {
			// Abrir la conexión
			conexion = DriverManager.getConnection(DB_URL, USER, PASS);
			// Ejecutar una consulta
			consulta = conexion.createStatement();
			String sql;
			sql = "SELECT Apostador, Equipo1, Local, Empate, Visitante, Equipo2 FROM tpintegrador.pronosticos";
			// En la variable resultado obtendremos las distintas filas que nos devolvía la base
			ResultSet resultado = consulta.executeQuery(sql);
			// Obtener las distintas filas de la consulta
			String stringProno;
			while (resultado.next()) {

				stringProno = resultado.getString("Apostador") + "," +
						resultado.getString("Equipo1") + "," +
						resultado.getString("Local") + "," +
						resultado.getString("Empate") + "," +
						resultado.getString("Visitante") + "," +
						resultado.getString("Equipo2");
				lineasPronostico.add(stringProno);
				
			}
			// Cerrar la conexión con la base de datos
			resultado.close();
			consulta.close();
			conexion.close();
		} catch (SQLException se) {
			// Excepción ante problemas de conexión
			se.printStackTrace();
		} finally {
			// Esta sentencia es para que ante un problema con la base igual se cierren las
			// conexiones
			try {
				if (consulta != null)
					consulta.close();
			} catch (SQLException se2) {
			}
			try {
				if (conexion != null)
					conexion.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		
		return lineasPronostico;
	}
}