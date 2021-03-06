package servicios;

import java.util.ArrayList;
import java.util.List;

import modelo.ItemVenta;
import modelo.Producto;
import modelo.Venta;
import persistencia.VentaMapper;

public class VentaServicio {
	
	private static VentaServicio instancia;
	
	public static VentaServicio getInstancia(){
		if(instancia == null){
			instancia = new VentaServicio();
		}
		return instancia;
	}
	
	public Venta buscarVenta(int idVenta){
		Venta v = null;
		v = VentaMapper.getInstancia().getVentaById(idVenta);
		return v;
	}
	
	public int generarVenta(Venta v){
		Producto p = null;
		List <ItemVenta> items = v.getItems();
		v.calcularTotal();//calculo el total, para luego aplicarle el descuento
		v.calcularDescuento();
		int nuevaVentaId = VentaMapper.getInstancia().insert(v);
		if(nuevaVentaId != -1){
			for(ItemVenta iterador : items){
				p = iterador.getProducto();
				p.setStock(p.getStock()-iterador.getCantidad());
				ProductoServicio.getInstancia().update(p);
				iterador.setCodItemVenta(nuevaVentaId);
				ItemVentaServicio.getInstancia().save(iterador);
			}
		}
		return nuevaVentaId;
	}

	public List<Venta> obtenerVentas(int inicio, int fin) {
		List <Venta> ventas = new ArrayList<Venta>();
		ventas = VentaMapper.getInstancia().getAll(inicio, fin);
		return ventas;
	}

	public List<Venta> nuevoReporteVentas(String fechaDesde, String fechaHasta) {
		List <Venta> ventas = new ArrayList<Venta>();
		ventas = VentaMapper.getInstancia().getReport(fechaDesde,fechaHasta);
		return ventas;
	}
	
}
