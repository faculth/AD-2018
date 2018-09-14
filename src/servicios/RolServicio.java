package servicios;

import modelo.Reclamo;
import modelo.Rol;

public class RolServicio {

private static RolServicio instancia;
	
	public static RolServicio getInstancia(){
		if(instancia == null){
			instancia = new RolServicio();
		}
		return instancia;
	}
	
	public void registrarRol(Rol r){
		
	}
	
	public Reclamo getRolById(int idRol){
		//No entra en el prototipo
		return null;
	}
}
