package servicios;

import java.util.List;

import modelo.Envio;
import modelo.Venta;
import persistencia.EnvioMapper;

public class EnvioServicio {
	private static EnvioServicio instancia;
	
	public static EnvioServicio getInstancia(){
		if(instancia == null){
			instancia = new EnvioServicio();
		}
		return instancia;
	}
	
	public void registrarEnvio(Venta v){
		EnvioMapper.getInstancia().registrarEnvio(v);
	}
	
	public Envio buscarEnvio(int codigoEnvio){
		Envio env = EnvioMapper.getInstancia().getEnvioById(codigoEnvio);
		return env;
	}
	
	public void save(Envio env){
		EnvioMapper.getInstancia().save(env);
	}
	
	public void update(Envio env){
		EnvioMapper.getInstancia().update(env);
	}
	
	public List<Envio> obtenerEnvios(){
		List<Envio> envios = EnvioMapper.getInstancia().getAll();
		return envios;
	}
}
