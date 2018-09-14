package servicios;

import java.util.List;

import modelo.Producto;
import persistencia.ProductoMapper;

public class ProductoServicio {
	private static ProductoServicio instancia;
	
	public static ProductoServicio getInstancia(){
		if(instancia == null){
			instancia = new ProductoServicio();
		}
		return instancia;
	}
	
	public Producto buscarProducto(int codigoProducto){
		Producto p = null;
		p = ProductoMapper.getInstancia().getProductoByCodigo(codigoProducto);
		return p;
	}
	
	public List <Producto> obtenerProductos(){
		List<Producto> productos = null;
		productos = ProductoMapper.getInstancia().getAll();
		return productos;
	}
	
	public boolean agregarProductoVenta(int codigoProducto,int cantidad){
		Producto p = null;
		p = buscarProducto(codigoProducto);
		if(p != null){
			if(p.tieneStock(cantidad)){
				return true;
			}
		}
		return false;
	}
	
	public void update(Producto prod){
		ProductoMapper.getInstancia().update(prod);
	}
	
	public void actualizarStock(int codigoProducto,int cantidad){
		Producto p = ProductoMapper.getInstancia().getProductoByCodigo(codigoProducto);
		if(p != null){
			p.actualizarStock(cantidad);
			ProductoMapper.getInstancia().update(p);
		}
	}
}
