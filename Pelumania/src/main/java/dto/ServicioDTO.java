package dto;

public class ServicioDTO {
	private int idServicio;
	private String nombre;
	private double precioLocal;
	private double precioDolar;
	private int puntos;
	
	public ServicioDTO(
			int idservicio,
			String nombre,
			double preciolocal,
			double preciodolar,
			int puntos) {
		
		this.idServicio = idservicio;
		this.nombre = nombre;
		this.precioLocal = preciolocal;
		this.precioDolar = preciodolar;
		this.puntos = puntos;
	}

	public int getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(int idServicio) {
		this.idServicio = idServicio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecioLocal() {
		return precioLocal;
	}

	public void setPrecioLocal(double precioLocal) {
		this.precioLocal = precioLocal;
	}

	public double getPrecioDolar() {
		return precioDolar;
	}

	public void setPrecioDolar(double precioDolar) {
		this.precioDolar = precioDolar;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}


}
