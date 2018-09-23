package modelo;

public class ItemVenta {
	private int codItemVenta;
	private Producto producto;
	private int cantidad;
	private float precioUnit;
	
	public ItemVenta(){}
	
	public ItemVenta( Producto producto, int cantidad, float precioUnit) {
		super();
		this.producto = producto;
		this.cantidad = cantidad;
		this.precioUnit = precioUnit;
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((producto == null) ? 0 : producto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemVenta other = (ItemVenta) obj;
		if (producto == null) {
			if (other.producto != null)
				return false;
		} else if (!producto.equals(other.producto))
			return false;
		return true;
	}

	
}
