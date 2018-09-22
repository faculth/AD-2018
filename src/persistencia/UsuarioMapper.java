package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import db.PoolConnection;
import modelo.Usuario;

public class UsuarioMapper {
	private static UsuarioMapper instancia;
	private static Connection con;
	
	public static UsuarioMapper getInstancia(){
		if(instancia == null){
			instancia = new UsuarioMapper();
		}
		con = PoolConnection.getInstance().getConnection();
		return instancia;
	}

	public Usuario getUsrById(int idUsr){
		Usuario recuperado = null;
		ResultSet resultado = null;
		try {
			PreparedStatement s = con.prepareStatement("SELECT * FROM usuarios WHERE dni = ?");
			s.setInt(1, idUsr);
			resultado = s.executeQuery();
			if(resultado.next()){
				recuperado = new Usuario();
				recuperado.setDni(idUsr);
				recuperado.setNombre(resultado.getString(2));
				recuperado.setRol(RolMapper.getInstancia().getRolById(resultado.getInt(3)));
				recuperado.setPassword(resultado.getString(4));
				recuperado.setActivo(resultado.getBoolean(5));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return recuperado;
	}
}
