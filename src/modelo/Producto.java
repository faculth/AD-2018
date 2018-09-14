package modelo;


public class Producto {
	private int codigoProducto;
	private String nombre;
	private String descripcion;
	private int precio;
	private String marca;
	private String modelo;
	private int piezas_disp;
	
	
	public Producto(){
	};
	
	
	public int getCodigoProducto() {
		return codigoProducto;
	}
	public void setCodigoProducto(int codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public int getPiezas_disp() {
		return piezas_disp;
	}
	public void setPiezas_disp(int piezas_disp) {
		this.piezas_disp = piezas_disp;
	}

	public boolean tieneStock(int cantidad) {
		if(this.piezas_disp - cantidad >= 0){
			return true;
		}
		return false;
	}

	public void actualizarStock(int cantidad) {
		this.piezas_disp += cantidad;
	}
}
