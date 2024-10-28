package Conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
	
	private static final String controlador = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/sistemadegestionpsicoterapeutica";
	private static final String usuario = "root";
	private static final String contraseña = "Krypserv432!";
	
	static {
		try {
			Class.forName(controlador);
		}catch(ClassNotFoundException e){
			System.out.println("Error al cargar el controlador");
			e.printStackTrace();
		}
	}
	
	public Connection conectar() {
		Connection conexion = null;
		try {	
			conexion = DriverManager.getConnection(URL, usuario, contraseña);	
			System.out.println("conexion OK");
		}  catch (SQLException e) {
			System.out.println("Error en la conexion");
			e.printStackTrace();
		}
		return conexion;
		
	}
	
}
