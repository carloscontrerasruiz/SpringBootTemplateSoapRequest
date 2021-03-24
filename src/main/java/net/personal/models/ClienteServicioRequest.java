package net.personal.models;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ClienteServicioRequest {
	
	@NotNull(message="Fecha requerida")
	@NotBlank(message="Fecha requerida")
	@Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$", message="Formato de fecha incorrecto")
	private String fecha;
	
	@NotNull(message="Centro requerido")
	@NotBlank(message="Centro requerido")
	private String centro;
	
	@NotNull(message="Almacen requerido")
	@NotBlank(message="Almacen requerido")
	private String almacen;
	
	@NotNull(message="Material requerido")
	@NotBlank(message="Material requerido")
	private String material;
	
	@Min(message="Cantidad minima invalida.", value = 0)
	private int cantidad;
	
	@NotNull(message="Unidad de medida requerida")
	@NotBlank(message="Unidad de medida requerida")
	private String uom;
	
	@NotNull(message="Folio Tarjetas requerido")
	@NotBlank(message="Folio Tarjetas requerido")
	private String FOLIO;
	
	@NotNull(message="Tipo de movimiento requerido")
	@NotBlank(message="Tipo de movimiento requerido")
	@Size(message="Tipo de movimiento longitud no valida", max= 1, min=1)
	private String tipoMovimiento;
	
	@NotNull(message="Lista de series requeridas")
	private List<TiSeries> tiSeries;
	
	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getCentro() {
		return centro;
	}

	public void setCentro(String centro) {
		this.centro = centro;
	}

	public String getAlmacen() {
		return almacen;
	}

	public void setAlmacen(String almacen) {
		this.almacen = almacen;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public String getFOLIO() {
		return FOLIO;
	}

	public void setFOLIO(String FOLIO) {
		this.FOLIO = FOLIO;
	}

	public List<TiSeries> getTiSeries() {
		return tiSeries;
	}

	public void setTiSeries(List<TiSeries> tiSeries) {
		this.tiSeries = tiSeries;
	}
	

	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	@Override
	public String toString() {
		return "ClienteServicioRequest [fecha=" + fecha + ", centro=" + centro + ", almacen=" + almacen + ", material="
				+ material + ", cantidad=" + cantidad + ", uom=" + uom + ", FOLIO=" + FOLIO
				+ ", tiSeries=" + tiSeries + "]";
	}
	
	
	
}
