package servicios;

import modelo.Reclamo;

public class ReclamoServicio {
	
	private static ReclamoServicio instancia;
	
	public static ReclamoServicio getInstancia(){
		if(instancia == null){
			instancia = new ReclamoServicio();
		}
		return instancia;
	}
	
	public void registrarReclamo(Reclamo r){
		
	}
	
	public Reclamo getReclamoById(int idReclamo){
		//No entra en el prototipo
		return null;
	}
	
	public void save(Reclamo rec){
		//No entra en el prototipo
	}
	
	public void update(Reclamo rec){
		//No entra en el prototipo
	}
}
