package net.personal.models;

public class TiSeries {
	
	private String serie;
	private String lote;
	
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getLote() {
		return lote;
	}
	public void setLote(String lote) {
		this.lote = lote;
	}
	
	@Override
	public String toString() {
		return "TiSeries [serie=" + serie + ", lote=" + lote + "]";
	}
	
}
