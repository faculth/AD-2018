package persistencia;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



import db.DbConnection;
import modelo.Cliente;

public class ClienteMapper {
	private static ClienteMapper instancia;
	
	public static ClienteMapper getInstancia(){
		if(instancia == null){
			instancia = new ClienteMapper();
		}
		return instancia;
	}
	
	public Cliente getClienteById(int dni){
		Cliente recuperado = null;
		ResultSet resultado = null;
		try {
			DbConnection conexion = new DbConnection();
			resultado = (ResultSet) conexion.getResults("SELECT * FROM CLIENTES WHERE dni_cuit = " + String.valueOf(dni));
			if(resultado.next()){
				recuperado = new Cliente();
				recuperado.setDni(resultado.getInt("dni_cuit"));
				recuperado.setNombre(resultado.getString("nombre"));
				recuperado.setApellido(resultado.getString("apellido"));
				recuperado.setEmail(resultado.getString("email"));
				recuperado.setTelefono(resultado.getString("telefono"));
				recuperado.setTipoCliente(resultado.getString("particular"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return recuperado;
	}
	
	public List<Cliente> getAll(){
		List <Cliente> clientes = new ArrayList<Cliente>();
		Cliente cli = null;
		ResultSet resultado = null;
		try {
			DbConnection conexion = new DbConnection();
			resultado = (ResultSet) conexion.getResults("SELECT * FROM CLIENTES");
			while(resultado.next()){
				cli = new Cliente();
				cli.setDni(resultado.getInt("dni_cuit"));
				cli.setNombre(resultado.getString("nombre"));
				cli.setApellido(resultado.getString("apellido"));
				cli.setEmail(resultado.getString("email"));
				cli.setTelefono(resultado.getString("telefono"));
				cli.setTipoCliente(resultado.getString("particular"));
				clientes.add(cli);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientes;
	}
}
