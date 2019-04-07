package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.PoolConnection;
import modelo.Envio;
import modelo.Venta;

public class EnvioMapper {
	
	private static EnvioMapper instancia;
	private static Connection con;
	
	public static EnvioMapper getInstancia(){
		if(instancia == null){
			instancia = new EnvioMapper();
		}
		con = PoolConnection.getInstance().getConnection();
		return instancia;
	}
	
	public Envio getEnvioById(int numEnvio){
		Envio recuperado = null;
		ResultSet resultado = null;
		try {
			PreparedStatement s = con.prepareStatement("SELECT * FROM envios WHERE id_envio = ?");
			s.setInt(1, numEnvio);
			resultado = s.executeQuery();
			if(resultado.next()){
				recuperado = new Envio();
				recuperado.setNumEnvio(resultado.getInt(1));
				recuperado.setEstado(resultado.getString(2));
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
			PreparedStatement s = con.prepareStatement("SELECT * FROM envios");
			resultado = s.executeQuery();
			while(resultado.next()){
				env = new Envio();
				env.setNumEnvio(resultado.getInt(1));
				env.setEstado(resultado.getString(2));
				envios.add(env);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return envios;
	}

	public void save(Envio env){
		try {
			PreparedStatement s = con.prepareStatement("INSERT INTO envios(estado) VALUES(?)");
			s.setString(1, env.getEstado());
			s.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(Envio v){
		try {
			PreparedStatement s = con.prepareStatement("UPDATE envios SET estado = ? where id_envio = ?");
			s.setString(1, v.getEstado());
			s.setInt(2, v.getNumEnvio());
			s.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private int obtenerUltimoEnvio(){
		int ultimoId = 0;
		ResultSet resultado = null;
		try {
			PreparedStatement s = con.prepareStatement("SELECT TOP 1 id_envio FROM envios ORDER BY id_envio DESC");
			resultado = s.executeQuery();
			if(resultado.next()){
				ultimoId = resultado.getInt(1);
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
		nuevo.setEstado("Despachado");
		this.save(nuevo);
		v.setEnvio(nuevo);
		VentaMapper.getInstancia().update(v);
	}
}
