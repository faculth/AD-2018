package servicios;

import java.util.List;

import modelo.Cliente;
import persistencia.ClienteMapper;

public class ClienteServicio {
	private static ClienteServicio instancia;
	
	public static ClienteServicio getInstancia(){
		if(instancia == null){
			instancia = new ClienteServicio();
		}
		return instancia;
	}
	
	public Cliente buscarCliente(int dni){
		Cliente c = ClienteMapper.getInstancia().getClienteById(dni);
		return c;
	}
	
	public List<Cliente> obtenerClientes(){
		List <Cliente> clientes = null;
		clientes = ClienteMapper.getInstancia().getAll();
		return clientes;
	}
}
