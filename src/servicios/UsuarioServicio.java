package servicios;

import persistencia.UsuarioMapper;
import modelo.Usuario;

public class UsuarioServicio {
	private static UsuarioServicio instancia;
	
	public static UsuarioServicio getInstancia(){
		if(instancia == null){
			instancia = new UsuarioServicio();
		}
		return instancia;
	}

	public Usuario buscarUsuario(int id) {
		Usuario u = UsuarioMapper.getInstancia().getUsrById(id);
		return u;
	}

	public int validarUser(String[] loginInfo) {
		int existe = UsuarioMapper.getInstancia().usuarioValido(loginInfo);
		return existe;
	}
	
	
}
