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
		Producto recuperado = null;
		ResultSet resultado = null;
		try {
			PreparedStatement s = con.prepareStatement("SELECT * FROM PRODUCTOS");
			resultado = s.executeQuery();
			while(resultado.next()){
				recuperado = new Producto();
				recuperado = new Producto();
				recuperado.setCodigoProducto(resultado.getInt(1));
				recuperado.setNombre(resultado.getString(2));
				recuperado.setPrecio(resultado.getInt(3));
				recuperado.setDescripcion(resultado.getString(4));
				recuperado.setPiezas_disp(resultado.getInt(5));
				recuperado.setMarca(resultado.getString(6));
				recuperado.setModelo(resultado.getString(7));
				productos.add(recuperado);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productos;
	}

	public void update(Producto producto) {
		try {
			PreparedStatement s = con.prepareStatement("UPDATE productos SET nombre = ?, precio = ?, descripcion = ?, unidades_disponibles = ?, marca = ?, modelo = ? WHERE codigo = ?");
			s.setString(1, producto.getNombre());
			s.setFloat(2, producto.getPrecio());
			s.setString(3, producto.getDescripcion());
			s.setInt(4, producto.getPiezas_disp());
			s.setString(5, producto.getMarca());
			s.setString(6, producto.getModelo());
			s.setInt(7, producto.getCodigoProducto());
			s.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
