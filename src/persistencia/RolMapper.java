package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.PoolConnection;
import modelo.Rol;

public class RolMapper {
	
	private static RolMapper instancia;
	private static Connection con;
	
	public static RolMapper getInstancia(){
		if(instancia == null){
			instancia = new RolMapper();
		}
		con = PoolConnection.getInstance().getConnection();
		return instancia;
	}

	public Rol getRolById(int idRol){
		Rol recuperado = null;
		ResultSet resultado = null;
		try {
			PreparedStatement s = con.prepareStatement("SELECT * FROM roles WHERE id = ?");
			s.setInt(1, idRol);
			resultado = s.executeQuery();
			if(resultado.next()){
				recuperado = new Rol();
				recuperado.setIdRol(idRol);
				recuperado.setNombre(resultado.getString(2));
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
			PreparedStatement s = con.prepareStatement("SELECT * FROM roles");
			resultado = s.executeQuery();
			while(resultado.next()){
				rol = new Rol();
				rol.setIdRol(resultado.getInt(1));
				rol.setNombre(resultado.getString(2));
				roles.add(rol);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roles;
	}
}
