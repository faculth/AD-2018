package servicios;

import modelo.ItemVenta;
import persistencia.ItemVentaMapper;

public class ItemVentaServicio {

	private static ItemVentaServicio instancia;
	
	public static ItemVentaServicio getInstancia() {
		if(instancia == null){
			instancia = new ItemVentaServicio();
		}
		return instancia;
	}

	public void save(ItemVenta iterador) {
		ItemVentaMapper.getInstancia().save(iterador);
	}

}
