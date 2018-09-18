package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import db.PoolConnection;
import modelo.Producto;

public class ProductoMapper {
	private static ProductoMapper instancia;
	private static Connection con;
	
	public static ProductoMapper getInstancia(){
		if(instancia == null){
			instancia = new ProductoMapper();
		}
		con = PoolConnection.getInstance().getConnection();
		return instancia;
	}
	
	public Producto getProductoByCodigo(int codigoProducto){
		Producto recuperado = null;
		ResultSet resultado = null;
		try {
			PreparedStatement s = con.prepareStatement("SELECT * FROM PRODUCTOS WHERE codigo =  ?");
			resultado = s.executeQuery();
			if(resultado.next()){
				recuperado = new Producto();
				recuperado.setCodigoProducto(resultado.getInt(1));
				recuperado.setNombre(resultado.getString(2));
				recuperado.setPrecio(resultado.getInt(3));
				recuperado.setDescripcion(resultado.getString(4));
				recuperado.setPiezas_disp(resultado.getInt(5));
				recuperado.setMarca(resultado.getString(6));
				recuperado.setModelo(resultado.getString(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return recuperado;
	}
	
	public List<Producto> getAll(){
		List <Producto> productos = new ArrayList<Producto>();
		Producto prod = null;
		ResultSet resultado = null;
		try {
			DbConnection conexion = new DbConnection();
			resultado = (ResultSet) conexion.getResults("SELECT * FROM PRODUCTOS");
			while(resultado.next()){
				prod = new Producto();
				prod.setCodigoProducto(resultado.getInt("codigo"));
				prod.setNombre(resultado.getString("nombre"));
				prod.setDescripcion(resultado.getString("descripcion"));
				prod.setMarca(resultado.getString("marca"));
				prod.setModelo(resultado.getString("modelo"));
				prod.setPiezas_disp(resultado.getInt("unidades_disponibles"));
				prod.setPrecio(resultado.getInt("precio"));
				productos.add(prod);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productos;
	}

	public void update(Producto producto) {
		try {
			DbConnection conexion = new DbConnection();
			String query = "UPDATE productos SET nombre= '"+ producto.getNombre() +"', precio= "+ producto.getPrecio() +", "
					+ "descripcion = '"+ producto.getDescripcion() +"', unidades_disponibles = "+ producto.getPiezas_disp() +","
							+ " marca = '"+ producto.getMarca() +"', modelo='"+ producto.getModelo() +"' WHERE codigo = "+ producto.getCodigoProducto();
			conexion.execute(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
