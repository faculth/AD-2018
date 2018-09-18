package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import db.PoolConnection;
import modelo.Cliente;

public class ClienteMapper {
	private static ClienteMapper instancia;
	private static Connection con;
	
	public static ClienteMapper getInstancia(){
		if(instancia == null){
			instancia = new ClienteMapper();
		}
		con = PoolConnection.getInstance().getConnection();
		return instancia;
	}
	
	public Cliente getClienteById(int dni){
		Cliente recuperado = null;
		ResultSet resultado = null;
		try {
			PreparedStatement s = con.prepareStatement("SELECT * FROM CLIENTES WHERE dni_cuit = ?");
			s.setInt(1, dni);
			resultado = s.executeQuery();
			if(resultado.next()){
				recuperado = new Cliente();
				recuperado.setDni(dni);
				recuperado.setNombre(resultado.getString(2));
				recuperado.setApellido(resultado.getString(3));
				recuperado.setDomicilio(resultado.getString(4));
				recuperado.setEmail(resultado.getString(5));
				recuperado.setTelefono(resultado.getString(6));
				recuperado.setTipoCliente(esParticular(resultado.getBoolean(7)));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return recuperado;
	}
/*	
	public List<Cliente> getAll(){
		List <Cliente> clientes = new ArrayList<Cliente>();
		Cliente cli = null;
		ResultSet resultado = null;
		try {
			//DbConnection conexion = new DbConnection();
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
	}*/
	
	private String esParticular(boolean valor){
		if(valor == true)
			return "Particular";
		else
			return "Compañia de seguro";
	}
}
