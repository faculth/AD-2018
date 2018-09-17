package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.Cliente;
import modelo.Envio;
import modelo.ItemVenta;
import modelo.Producto;
import modelo.Usuario;
import modelo.Venta;
import servicios.ClienteServicio;
import servicios.EnvioServicio;
import servicios.ProductoServicio;
import servicios.UsuarioServicio;
import servicios.VentaServicio;

public class test {

	public static void main(String[] args) throws ParseException {
		//buscarCliente(); //ok
		//buscarProducto(); //ok
		//actualizarStock(); //ok
		/*NOTA: NO VERIFICO EL STOCK CUANDO GENERO LA VENTA, YA QUE QUE SUPUESTAMENTE A 
		LA HORA DE APRETAR EL BOTON PARA AGREGAR EL PRODUCTO SE VERIFICA, Y SI NO TIENE NO TE LO DEJARIA AGREGAR A LA VENTA*/
		generarVenta(); //ok
		//buscarVenta(); //ok
		//registrarEnvio(); //ok
		//modificarEnvio(); //ok
		//ingresarSistema(); ESTO LO TENDRIA QUE PROTOTIPAR MARIANO
		listarVentas();//mati
		listarEnvios();//mati
		generarReporteVenta();//mati
	}

	private static void generarReporteVenta() {
		// TODO Auto-generated method stub
		
	}

	private static void listarEnvios() {
		// TODO Auto-generated method stub
		
	}

	private static void listarVentas() {
		// TODO Auto-generated method stub
		
	}

	private static void modificarEnvio() {
		Envio v = EnvioServicio.getInstancia().buscarEnvio(16);
		v.setEstado("Despachado");
		EnvioServicio.getInstancia().update(v);
		
	}

	private static void registrarEnvio() {
		Venta v = VentaServicio.getInstancia().buscarVenta(1);
		EnvioServicio.getInstancia().registrarEnvio(v);
		
	}

	private static void buscarVenta() {
		Venta v = VentaServicio.getInstancia().buscarVenta(1);
		System.out.print(v.getNumeroVenta()+" Total:"+v.getTotal());
		
	}

	private static void generarVenta() throws ParseException {
		Venta ventaNueva = new Venta();
		
		//TRAIGO AL USUARIO
		Usuario u = UsuarioServicio.getInstancia().buscarUsuario(1);
		//TRAIGO EL CLIENTE
		Cliente c = ClienteServicio.getInstancia().buscarCliente(32568788);
		
		//TRAIGO LOS PRODUCTOS
		Producto p1 = ProductoServicio.getInstancia().buscarProducto(1);
		Producto p2 = ProductoServicio.getInstancia().buscarProducto(2);
		Producto p3 = ProductoServicio.getInstancia().buscarProducto(3);
		
		//CREO LOS ITEMS
		List <ItemVenta> items = new ArrayList<ItemVenta>();
		ItemVenta i1 = new ItemVenta();
		ItemVenta i2 = new ItemVenta();
		ItemVenta i3 = new ItemVenta();
		i1.setProducto(p1);
		i1.setCantidad(1);
		i1.setPrecioUnit(p1.getPrecio());
		i2.setProducto(p2);
		i2.setCantidad(2);
		i2.setPrecioUnit(p2.getPrecio());
		i3.setProducto(p3);
		i3.setCantidad(2);
		i3.setPrecioUnit(p3.getPrecio());
		
		items.add(i1);
		items.add(i2);
		items.add(i3);
		
		//AGREGO LOS ITEMS A LA VENTA Y ADEMAS SIGO CON LOS OTROS DATOS
		ventaNueva.setItems(items);
		
		ventaNueva.setCliente(c);
		ventaNueva.setDescuento(15);
		ventaNueva.setFechaVenta(new Date());
		ventaNueva.setUsuario(u);
		
		VentaServicio.getInstancia().generarVenta(ventaNueva);
		
	}

	private static void actualizarStock() {
		int stockNuevo = 15;
		ProductoServicio.getInstancia().actualizarStock(3, stockNuevo);
	}

	private static void buscarProducto() {
		Producto p = ProductoServicio.getInstancia().buscarProducto(3);
		System.out.print(p.getNombre()+" "+p.getDescripcion());
		
	}

	private static void buscarCliente() {
		Cliente c = ClienteServicio.getInstancia().buscarCliente(39155848);
		System.out.print(c.getNombre());
		
	}

}
