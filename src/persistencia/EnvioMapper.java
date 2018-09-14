package persistencia;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.DbConnection;
import modelo.Envio;
import modelo.Venta;

public class EnvioMapper {
	
	private static EnvioMapper instancia;
	
	public static EnvioMapper getInstancia(){
		if(instancia == null){
			instancia = new EnvioMapper();
		}
		return instancia;
	}
	
	public Envio getEnvioById(int numEnvio){
		Envio recuperado = null;
		ResultSet resultado = null;
		try {
			DbConnection conexion = new DbConnection();
			resultado = (ResultSet) conexion.getResults("SELECT * FROM ENVIOS WHERE id = " + String.valueOf(numEnvio));
			if(resultado.next()){
				recuperado = new Envio();
				recuperado.setNumEnvio(resultado.getInt("id"));
				recuperado.setEstado(resultado.getString("estado"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return recuperado;
	}
	
	public List<Envio> getAll(){
		List <Envio> envios = new ArrayList<Envio>();
		Envio env = null;
		ResultSet resultado = null;
		try {
			DbConnection conexion = new DbConnection();
			resultado = (ResultSet) conexion.getResults("SELECT * FROM ENVIOS");
			while(resultado.next()){
				env = new Envio();
				env.setNumEnvio(resultado.getInt("id"));
				env.setEstado(resultado.getString("estado"));
				envios.add(env);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return envios;
	}

	public void save(Envio env){
		try {
			String query = "INSERT INTO envios(estado) VALUES('"+ env.getEstado() +"')";
			DbConnection conexion = new DbConnection();
			conexion.execute(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(Envio v){
		try {
			String query = "UPDATE envios SET estado= '"+ v.getEstado() +"' WHERE id = "+v.getNumEnvio();
			DbConnection conexion = new DbConnection();
			conexion.execute(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private int obtenerUltimoEnvio(){
		int ultimoId = 0;
		ResultSet resultado = null;
		try {
			DbConnection conexion = new DbConnection();
			resultado = (ResultSet) conexion.getResults("SELECT id FROM envios ORDER BY id DESC LIMIT 1");
			if(resultado.next()){
				ultimoId = resultado.getInt("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ultimoId;
	}
	
	public void registrarEnvio(Venta v) {
		int ultimoId = obtenerUltimoEnvio();
		Envio nuevo = new Envio();
		nuevo.setNumEnvio(ultimoId+1);
		nuevo.setEstado("Enviado");
		this.save(nuevo);
		v.setEnvio(nuevo);
		VentaMapper.getInstancia().update(v);
	}
}
