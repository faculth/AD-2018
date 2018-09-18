package modelo;

public class ItemVenta {
	private int codItemVenta;
	private Producto producto;
	private int cantidad;
	private float precioUnit;
	
	public ItemVenta(){}

	public int getCodItemVenta() {
		return codItemVenta;
	}

	public void setCodItemVenta(int codItemVenta) {
		this.codItemVenta = codItemVenta;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float calcularSubtotal() {
		return this.cantidad*this.precioUnit;
	}

	public float getPrecioUnit() {
		return precioUnit;
	}

	public void setPrecioUnit(float precioUnit) {
		this.precioUnit = precioUnit;
	}
	
	
}
