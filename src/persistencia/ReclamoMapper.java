package persistencia;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.DbConnection;
import modelo.Reclamo;

public class ReclamoMapper {
	
	private static ReclamoMapper instancia;
	
	public static ReclamoMapper getInstancia(){
		if(instancia == null){
			instancia = new ReclamoMapper();
		}
		return instancia;
	}

	public Reclamo getReclamoById(int idReclamo){
		Reclamo recuperado = null;
		ResultSet resultado = null;
		try {
			DbConnection conexion = new DbConnection();
			resultado = (ResultSet) conexion.getResults("SELECT * FROM RECLAMOS WHERE id = " + String.valueOf(idReclamo));
			if(resultado.next()){
				recuperado = new Reclamo();
				recuperado.setNumeroReclamo(resultado.getInt("id"));
				recuperado.setDescripcion(resultado.getString("descripcion"));
				recuperado.setEstado(resultado.getString("estado"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return recuperado;
	}
	
	public List<Reclamo> getAll(){
		List <Reclamo> reclamos = new ArrayList<Reclamo>();
		Reclamo reclamo = null;
		ResultSet resultado = null;
		try {
			DbConnection conexion = new DbConnection();
			resultado = (ResultSet) conexion.getResults("SELECT * FROM RECLAMOS");
			while(resultado.next()){
				reclamo = new Reclamo();
				reclamo.setNumeroReclamo(resultado.getInt("id"));
				reclamo.setDescripcion(resultado.getString("descripcion"));
				reclamo.setEstado(resultado.getString("estado"));
				reclamos.add(reclamo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reclamos;
	}
	
	public void save(Reclamo rec){
		//No entra en el prototipo
	}
	
	public void update(Reclamo rec){
		//No entra en el prototipo
	}
}
