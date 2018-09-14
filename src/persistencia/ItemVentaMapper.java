package persistencia;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.DbConnection;
import modelo.ItemVenta;
import modelo.Producto;

public class ItemVentaMapper {
	private static ItemVentaMapper instancia;
	
	public static ItemVentaMapper getInstancia(){
		if(instancia == null){
			instancia = new ItemVentaMapper();
		}
		return instancia;
	}
	
	public void save(ItemVenta item){
		try {
			DbConnection conexion = new DbConnection();
			String query = "INSERT INTO item_venta(venta_id, producto_codigo, producto_cantidad, precio_unidad) "
					+ "VALUES("+ item.getCodItemVenta() +", "+ item.getProducto().getCodigoProducto() +", "+ item.getCantidad() +", "+ item.getProducto().getPrecio() +")";
			conexion.execute(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(ItemVenta item){
		try {
			DbConnection conexion = new DbConnection();
			String query = "UPDATE item_venta SET producto_codigo="+ item.getProducto().getCodigoProducto() +
					 ", producto_cantidad="+ item.getCantidad() +", "
					 		+ "precio_unidad="+ item.getProducto().getPrecio() +" WHERE venta_id="+item.getCodItemVenta();
			conexion.execute(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<ItemVenta> recuperarItemsVenta(int idVenta) {
		List <ItemVenta> items = new ArrayList<ItemVenta>();
		ResultSet resultado = null;
		ItemVenta item = null;
		try {
			DbConnection conexion = new DbConnection();
			resultado = (ResultSet) conexion.getResults("SELECT * FROM item_venta WHERE venta_id = " + String.valueOf(idVenta));
			while(resultado.next()){
				item = new ItemVenta();
				item.setCodItemVenta(resultado.getInt("venta_id"));
				item.setCantidad(resultado.getInt("producto_cantidad"));
				Producto pItem = ProductoMapper.getInstancia().getProductoByCodigo(resultado.getInt("producto_codigo"));
				item.setProducto(pItem);
				item.setPrecioUnit(pItem.getPrecio());
				items.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return items;
	}
}
