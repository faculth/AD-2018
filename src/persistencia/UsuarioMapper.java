package persistencia;

import java.sql.ResultSet;

import db.DbConnection;
import modelo.Rol;
import modelo.Usuario;

public class UsuarioMapper {
	private static UsuarioMapper instancia;
	
	public static UsuarioMapper getInstancia(){
		if(instancia == null){
			instancia = new UsuarioMapper();
		}
		return instancia;
	}

	public Usuario getUsrById(int idUsr){
		Usuario recuperado = null;
		ResultSet resultado = null;
		try {
			DbConnection conexion = new DbConnection();
			resultado = (ResultSet) conexion.getResults("SELECT * FROM USUARIOS WHERE dni = " + String.valueOf(idUsr));
			if(resultado.next()){
				recuperado = new Usuario();
				recuperado.setDni(resultado.getInt("dni"));
				recuperado.setNombre(resultado.getString("nombre"));
				recuperado.setPassword(resultado.getString("password"));
				recuperado.setActivo(resultado.getBoolean("activo"));
				Rol rolUsr = RolMapper.getInstancia().getRolById(resultado.getInt("rol_id"));
				recuperado.setRol(rolUsr);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return recuperado;
	}
}
