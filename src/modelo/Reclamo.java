package modelo;

public class Reclamo {
	private int numeroReclamo;
	private String descripcion;
	private String estado;
	
	public Reclamo(){}

	public int getNumeroReclamo() {
		return numeroReclamo;
	}

	public void setNumeroReclamo(int numeroReclamo) {
		this.numeroReclamo = numeroReclamo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}
