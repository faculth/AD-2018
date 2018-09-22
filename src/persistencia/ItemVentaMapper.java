package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import db.PoolConnection;
import modelo.ItemVenta;
import modelo.Producto;

public class ItemVentaMapper {
	private static ItemVentaMapper instancia;
	private static Connection con;
	
	public static ItemVentaMapper getInstancia(){
		if(instancia == null){
			instancia = new ItemVentaMapper();
		}
		con = PoolConnection.getInstance().getConnection();
		return instancia;
	}
	
	public void save(ItemVenta item){
		try {
			PreparedStatement s = con.prepareStatement("INSERT INTO item_venta (venta_id, producto_codigo, producto_cantidad, precio_unidad) VALUES (?,?,?,?)");
			s.setInt(1, item.getCodItemVenta());
			s.setInt(2, item.getProducto().getCodigoProducto());
			s.setInt(3, item.getCantidad());
			s.setFloat(4, item.getPrecioUnit());
			s.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(ItemVenta item){
		try {
			PreparedStatement s = con.prepareStatement("UPDATE item_venta SET producto_cantidad = ?, precio_unidad = ? WHERE venta_id = ?");
			s.setInt(1, item.getCantidad());
			s.setFloat(2, item.getPrecioUnit());
			s.setInt(3, item.getCodItemVenta());
			s.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<ItemVenta> recuperarItemsVenta(int idVenta) {
		List <ItemVenta> items = new ArrayList<ItemVenta>();
		ResultSet resultado = null;
		ItemVenta item = null;
		try {
			PreparedStatement s = con.prepareStatement("SELECT * FROM item_venta WHERE venta_id =  ?");
			s.setInt(1, idVenta);
			resultado = s.executeQuery();
			while(resultado.next()){
				item = new ItemVenta();
				item.setCodItemVenta(idVenta);
				item.setCantidad(resultado.getInt(3));
				Producto pItem = ProductoMapper.getInstancia().getProductoByCodigo(resultado.getInt(2));
				item.setProducto(pItem);
				item.setPrecioUnit(resultado.getFloat(4));
				items.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return items;
	}
}
