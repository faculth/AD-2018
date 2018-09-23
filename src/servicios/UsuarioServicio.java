package servicios;

import java.util.List;

import modelo.Usuario;
import persistencia.UsuarioMapper;

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
	
	public List<Usuario> obtenerUsuarios(){
		List<Usuario> usuarios = null;
		usuarios = UsuarioMapper.getInstancia().getAll();
		return usuarios;
	}
	
	
}
