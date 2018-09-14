package persistencia;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.DbConnection;
import modelo.Producto;

public class ProductoMapper {
private static ProductoMapper instancia;
	
	public static ProductoMapper getInstancia(){
		if(instancia == null){
			instancia = new ProductoMapper();
		}
		return instancia;
	}
	
	public Producto getProductoByCodigo(int codigoProducto){
		Producto recuperado = null;
		ResultSet resultado = null;
		try {
			DbConnection conexion = new DbConnection();
			resultado = conexion.getResults("SELECT * FROM PRODUCTOS WHERE codigo = " + String.valueOf(codigoProducto));
			if(resultado.next()){
				recuperado = new Producto();
				recuperado.setCodigoProducto(codigoProducto);
				recuperado.setNombre(resultado.getString("nombre"));
				recuperado.setDescripcion(resultado.getString("descripcion"));
				recuperado.setMarca(resultado.getString("marca"));
				recuperado.setModelo(resultado.getString("modelo"));
				recuperado.setPiezas_disp(resultado.getInt("unidades_disponibles"));
				recuperado.setPrecio(resultado.getInt("precio"));
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
