package persistencia;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.PoolConnection;
import modelo.Venta;

public class VentaMapper {
	private static VentaMapper instancia;
	private static Connection con;
	
	public static VentaMapper getInstancia(){
		if(instancia == null){
			instancia = new VentaMapper();
		}
		con = PoolConnection.getInstance().getConnection();
		return instancia;
	}
	
	public Venta getVentaById(int idVenta){
		Venta recuperada = null;
		ResultSet resultado = null;
		try {
			PreparedStatement s = con.prepareStatement("SELECT * FROM ventas WHERE id_venta = ?");
			s.setInt(1, idVenta);
			resultado = s.executeQuery();
			if(resultado.next()){
				recuperada = new Venta();
				recuperada.setNumeroVenta(resultado.getInt(1));
				recuperada.setFechaVenta(resultado.getString(2));
				recuperada.setTotal(resultado.getFloat(3));
				recuperada.setCliente(ClienteMapper.getInstancia().getClienteById(resultado.getInt(4)));
				recuperada.setUsuario(UsuarioMapper.getInstancia().getUsrById(resultado.getInt(5)));
				recuperada.setItems(ItemVentaMapper.getInstancia().recuperarItemsVenta(idVenta));
				recuperada.setDescuento(resultado.getInt(6));
				if(resultado.getInt("envio_id") > 0){
					recuperada.setEnvio(EnvioMapper.getInstancia().getEnvioById(resultado.getInt(7)));
				}
				if(resultado.getInt("reclamo_id") > 0){
					recuperada.setReclamo(ReclamoMapper.getInstancia().getReclamoById(resultado.getInt(8)));
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
			PreparedStatement s = con.prepareStatement("SELECT top 1 id_venta FROM ventas ORDER BY id_venta DESC");
			resultado = s.executeQuery();
			if(resultado.next()){
				ultimoId = resultado.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ultimoId;
	}

	public int insert(Venta v) {
		try {
			PreparedStatement s = con.prepareStatement("INSERT INTO ventas (fecha,total,cliente_dni_cuit,usuario_dni,descuento,envio_id,reclamo_id) values (?,"
					+ "?,?,?,?,?,?)");
			s.setString(1, v.getFechaVenta());
			s.setFloat(2, v.getTotal());
			s.setInt(3, v.getCliente().getDni());
			s.setInt(4, v.getUsuario().getDni());
			s.setFloat(5, v.getDescuento());
			if(v.getEnvio() != null){
				s.setInt(6, v.getEnvio().getNumEnvio());
			}
			else{
				s.setNull(6, java.sql.Types.INTEGER);
			}
			if(v.getReclamo() != null){
				s.setInt(7, v.getReclamo().getNumeroReclamo());
			}
			else{
				s.setNull(7, java.sql.Types.INTEGER);
			}
			s.execute();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return VentaMapper.getInstancia().obtenerUltimoId();
	}
	

	public void update(Venta v) {
		try {
			PreparedStatement s = con.prepareStatement("UPDATE ventas set fecha = ?, total = ?, cliente_dni_cuit = ?,usuario_dni = ?,descuento = ?,envido_id = ?, reclamo_id = ? where id_venta = ?");
			s.setString(1, v.getFechaVenta());
			s.setFloat(2, v.getTotal());
			s.setInt(3, v.getCliente().getDni());
			s.setInt(4, v.getUsuario().getDni());
			s.setFloat(5, v.getDescuento());
			if(v.getEnvio() != null){
				s.setInt(6, v.getEnvio().getNumEnvio());
			}
			if(v.getReclamo() != null){
				s.setInt(7, v.getReclamo().getNumeroReclamo());
			}
			s.setInt(8, v.getNumeroVenta());
			s.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Venta> getAll() {
		List <Venta> ventas = new ArrayList<Venta>();
		Venta recuperada = null;
		ResultSet resultado = null;
		try {
			PreparedStatement s = con.prepareStatement("SELECT * FROM ventas");
			resultado = s.executeQuery();
			while(resultado.next()){
				recuperada = new Venta();
				recuperada.setNumeroVenta(resultado.getInt(1));
				recuperada.setFechaVenta(resultado.getString(2));
				recuperada.setTotal(resultado.getFloat(3));
				recuperada.setCliente(ClienteMapper.getInstancia().getClienteById(resultado.getInt(4)));
				recuperada.setUsuario(UsuarioMapper.getInstancia().getUsrById(resultado.getInt(5)));
				recuperada.setItems(ItemVentaMapper.getInstancia().recuperarItemsVenta(resultado.getInt(1)));
				recuperada.setDescuento(resultado.getInt(6));
				if(resultado.getInt("envio_id") > 0){
					recuperada.setEnvio(EnvioMapper.getInstancia().getEnvioById(resultado.getInt(7)));
				}
				if(resultado.getInt("reclamo_id") > 0){
					recuperada.setReclamo(ReclamoMapper.getInstancia().getReclamoById(resultado.getInt(8)));
				}
				ventas.add(recuperada);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ventas;
	}

	public List<Venta> getReport(String fechaDesde, String fechaHasta) {
		List <Venta> ventas = new ArrayList<Venta>();
		Venta recuperada = null;
		ResultSet resultado = null;
		try {
			PreparedStatement s = con.prepareStatement("SELECT * FROM ventas WHERE fecha >= '?' AND fecha <= '?'");
			s.setString(1, fechaDesde);
			s.setString(2, fechaHasta);
			resultado = s.executeQuery();
			while(resultado.next()){
				recuperada = new Venta();
				recuperada.setNumeroVenta(resultado.getInt(1));
				recuperada.setFechaVenta(resultado.getString(2));
				recuperada.setTotal(resultado.getFloat(3));
				recuperada.setCliente(ClienteMapper.getInstancia().getClienteById(resultado.getInt(4)));
				recuperada.setUsuario(UsuarioMapper.getInstancia().getUsrById(resultado.getInt(5)));
				recuperada.setItems(ItemVentaMapper.getInstancia().recuperarItemsVenta(resultado.getInt(1)));
				recuperada.setDescuento(resultado.getInt(6));
				if(resultado.getInt("envio_id") > 0){
					recuperada.setEnvio(EnvioMapper.getInstancia().getEnvioById(resultado.getInt(7)));
				}
				if(resultado.getInt("reclamo_id") > 0){
					recuperada.setReclamo(ReclamoMapper.getInstancia().getReclamoById(resultado.getInt(8)));
				}
				ventas.add(recuperada);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ventas;
	}
}