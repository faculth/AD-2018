package persistencia;



import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import db.DbConnection;
import modelo.Venta;

public class VentaMapper {
	private static VentaMapper instancia;
	
	public static VentaMapper getInstancia(){
		if(instancia == null){
			instancia = new VentaMapper();
		}
		return instancia;
	}
	
	public Venta getVentaById(int idVenta){
		Venta recuperada = null;
		ResultSet resultado = null;
		
		try {
			DbConnection conexion = new DbConnection();
			resultado = (ResultSet) conexion.getResults("SELECT * FROM ventas WHERE id = " + String.valueOf(idVenta));
			if(resultado.next()){
				recuperada = new Venta();
				recuperada.setNumeroVenta(resultado.getInt("id"));
				recuperada.setTotal(resultado.getFloat("total"));
				recuperada.setFechaVenta(resultado.getDate("fecha"));
				recuperada.setUsuario(UsuarioMapper.getInstancia().getUsrById(resultado.getInt("usuario_dni")));
				recuperada.setItems(ItemVentaMapper.getInstancia().recuperarItemsVenta(idVenta));
				recuperada.setCliente(ClienteMapper.getInstancia().getClienteById(resultado.getInt("cliente_dni_cuit")));
				recuperada.setDescuento(resultado.getInt("descuento"));
				if(resultado.getInt("envio_id") > 0){
					recuperada.setEnvio(EnvioMapper.getInstancia().getEnvioById(resultado.getInt("envio_id")));
				}
				if(resultado.getInt("reclamo_id") > 0){
					recuperada.setReclamo(ReclamoMapper.getInstancia().getReclamoById(resultado.getInt("reclamo_id")));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return recuperada;
	}
	
	public int obtenerUltimoId(){
		int ultimoId = 0;
		ResultSet resultado = null;
		try {
			DbConnection conexion = new DbConnection();
			resultado = conexion.getResults("select id from ventas order by id desc Limit 1");
			if(resultado.next()){
				ultimoId = 1 + resultado.getInt("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ultimoId;
	}

	public int insert(Venta v) {
		try {
			DbConnection conexion = new DbConnection();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			String strReclamo;
			if(v.getReclamo() != null){
				int reclamoId = v.getReclamo().getNumeroReclamo();
				strReclamo = "'"+String.valueOf(reclamoId)+"'";
			}else {
				strReclamo = null;
			}
			String strEnvio;
			if(v.getEnvio() != null){
				int envioId = v.getEnvio().getNumEnvio();
				strEnvio = "'"+String.valueOf(envioId)+"'";
			}else {
				strEnvio = null;
			}
			
			String query = "INSERT INTO ventas(fecha, total, cliente_dni_cuit, usuario_dni, descuento, envio_id, reclamo_id) VALUES('"+ sdf.format(v.getFechaVenta()) +"', "
					+ ""+ v.getTotal() +", "+ v.getCliente().getDni() +", "
							+ ""+ v.getUsuario().getDni() +", "+ v.getDescuento() +", "+ strEnvio +", reclamo_id ="+ strReclamo +")";
			return conexion.execute(query);

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	

	public void update(Venta v) {
		try {
			DbConnection conexion = new DbConnection();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			

			String strReclamo;
			if(v.getReclamo() != null){
				int reclamoId = v.getReclamo().getNumeroReclamo();
				strReclamo = "'"+String.valueOf(reclamoId)+"'";
			}else {
				strReclamo = null;
			}
			String strEnvio;
			if(v.getEnvio() != null){
				int envioId = v.getEnvio().getNumEnvio();
				strEnvio = "'"+String.valueOf(envioId)+"'";
			}else {
				strEnvio = null;
			}

			String query = "UPDATE ventas SET fecha = '"+ sdf.format(v.getFechaVenta()) +"', total = "+ v.getTotal() +", "
					+ "cliente_dni_cuit = '"+ v.getCliente().getDni() +"', usuario_dni = "+ v.getUsuario().getDni() +","
							+ " descuento = '"+ v.getDescuento() +"', envio_id ="+ strEnvio +", reclamo_id =" + strReclamo 
							+" WHERE id = "+ v.getNumeroVenta();
			conexion.execute(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
