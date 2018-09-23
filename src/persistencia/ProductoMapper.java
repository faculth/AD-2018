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
			s.setInt(1, codigoProducto);
			resultado = s.executeQuery();
			if(resultado.next()){
				recuperado = new Producto();
				recuperado.setCodigoProducto(resultado.getInt(1));
				recuperado.setNombre(resultado.getString(2));
				recuperado.setPrecio(resultado.getInt(3));
				recuperado.setDescripcion(resultado.getString(4));
				recuperado.setStock(resultado.getInt(5));
				recuperado.setMarca(resultado.getString(6));
				recuperado.setModelo(resultado.getString(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return recuperado;
	}
	
	public List<Producto> getAll(int inicio, int fin){
		List <Producto> productos = new ArrayList<Producto>();
		Producto recuperado = null;
		ResultSet resultado = null;
		try {
			PreparedStatement s = con.prepareStatement("SELECT * FROM productos ORDER BY codigo OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");
			s.setInt(1, inicio);
			s.setInt(2, fin);
			resultado = s.executeQuery();
			while(resultado.next()){
				recuperado = new Producto();
				recuperado.setCodigoProducto(resultado.getInt(1));
				recuperado.setNombre(resultado.getString(2));
				recuperado.setPrecio(resultado.getInt(3));
				recuperado.setDescripcion(resultado.getString(4));
				recuperado.setStock(resultado.getInt(5));
				recuperado.setMarca(resultado.getString(6));
				recuperado.setModelo(resultado.getString(7));
				productos.add(recuperado);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productos;
	}

	public boolean update(Producto producto) {
		try {
			PreparedStatement s = con.prepareStatement("UPDATE productos SET nombre = ?, precio = ?, descripcion = ?, unidades_disponibles = ?, marca = ?, modelo = ? WHERE codigo = ?");
			s.setString(1, producto.getNombre());
			s.setFloat(2, producto.getPrecio());
			s.setString(3, producto.getDescripcion());
			s.setInt(4, producto.getStock());
			s.setString(5, producto.getMarca());
			s.setString(6, producto.getModelo());
			s.setInt(7, producto.getCodigoProducto());
			s.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public int getCantProd() {
		try {
			PreparedStatement s = con.prepareStatement("SELECT COUNT(*) FROM productos");
			ResultSet resultado = null;
			resultado = s.executeQuery();
			if(resultado.next()){
				return resultado.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
