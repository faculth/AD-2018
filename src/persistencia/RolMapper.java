package persistencia;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.DbConnection;
import modelo.Rol;

public class RolMapper {
	
	private static RolMapper instancia;
	
	public static RolMapper getInstancia(){
		if(instancia == null){
			instancia = new RolMapper();
		}
		return instancia;
	}

	public Rol getRolById(int idRol){
		Rol recuperado = null;
		ResultSet resultado = null;
		try {
			DbConnection conexion = new DbConnection();
			resultado = (ResultSet) conexion.getResults("SELECT * FROM ROLES WHERE id = " + String.valueOf(idRol));
			if(resultado.next()){
				recuperado = new Rol();
				recuperado.setIdRol(resultado.getInt("id"));
				recuperado.setNombre(resultado.getString("nombre"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return recuperado;
	}
	
	public List<Rol> getAll(){
		List <Rol> roles = new ArrayList<Rol>();
		Rol rol = null;
		ResultSet resultado = null;
		try {
			DbConnection conexion = new DbConnection();
			resultado = (ResultSet) conexion.getResults("SELECT * FROM ROLES");
			while(resultado.next()){
				rol = new Rol();
				rol.setIdRol(resultado.getInt("id"));
				rol.setNombre(resultado.getString("nombre"));
				roles.add(rol);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roles;
	}
}
