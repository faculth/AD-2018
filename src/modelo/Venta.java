package modelo;

import java.util.Date;
import java.util.List;

public class Venta {
	private int numeroVenta;
	private float total;
	private String fechaVenta;
	private Usuario usuario;
	private Cliente cliente;
	private Envio envio;
	private List <ItemVenta> items;
	private Reclamo reclamo;
	private int descuento;
	
	public Venta(){}

	public Venta(int numeroVenta, float total, String fechaVenta, Usuario usuario, Cliente cliente, Envio envio,
			List<ItemVenta> items, Reclamo reclamo, int descuento) {
		super();
		this.numeroVenta = numeroVenta;
		this.total = total;
		this.fechaVenta = fechaVenta;
		this.usuario = usuario;
		this.cliente = cliente;
		this.envio = envio;
		this.items = items;
		this.reclamo = reclamo;
		this.descuento = descuento;
	}

	public Venta(String fechaVenta, Usuario usuario, Cliente cliente,	List<ItemVenta> items, int descuento) {
		super();
		this.fechaVenta = fechaVenta;
		this.usuario = usuario;
		this.cliente = cliente;
		this.items = items;
		this.descuento = descuento;
	}

	public int getNumeroVenta() {
		return numeroVenta;
	}

	public void setNumeroVenta(int numeroVenta) {
		this.numeroVenta = numeroVenta;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public String getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(String fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Envio getEnvio() {
		return envio;
	}

	public void setEnvio(Envio envio) {
		this.envio = envio;
	}

	public List <ItemVenta> getItems() {
		return items;
	}

	public void setItems(List <ItemVenta> items) {
		this.items = items;
	}

	public Reclamo getReclamo() {
		return reclamo;
	}

	public void setReclamo(Reclamo reclamo) {
		this.reclamo = reclamo;
	}
	
	public float getDescuento() {
		return descuento;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}
	
	public void calcularTotal(){
		float total = 0;
		for(ItemVenta iterador : this.items){
			total += iterador.calcularSubtotal(); 
		}
		this.total = total;
	}

	public void calcularDescuento(){
		float desc = (this.total*this.descuento)/100;
		this.total = this.total-desc;
	}

}
