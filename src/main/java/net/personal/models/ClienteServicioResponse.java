package net.personal.models;

import java.util.List;

import net.personal.clientesoap.bajaventa.BAPIRET2;

public class ClienteServicioResponse {
	
	private String matDoc;
	private String docYear;
	private List<BAPIRET2> listResponse;
	private boolean error;
	private String errorMensaje;
	private int reintentos;
	
	public String getMatDoc() {
		return matDoc;
	}
	public void setMatDoc(String matDoc) {
		this.matDoc = matDoc;
	}
	public String getDocYear() {
		return docYear;
	}
	public void setDocYear(String docYear) {
		this.docYear = docYear;
	}
	public List<BAPIRET2> getListResponse() {
		return listResponse;
	}
	public void setListResponse(List<BAPIRET2> listResponse) {
		this.listResponse = listResponse;
	}
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public String getErrorMensaje() {
		return errorMensaje;
	}
	public void setErrorMensaje(String errorMensaje) {
		this.errorMensaje = errorMensaje;
	}
	public int getReintentos() {
		return reintentos;
	}
	public void setReintentos(int reintentos) {
		this.reintentos = reintentos;
	}
	@Override
	public String toString() {
		return "ClienteServicioResponse [matDoc=" + matDoc + ", docYear=" + docYear + ", listResponse=" + listResponse
				+ ", error=" + error + ", errorMensaje=" + errorMensaje + ", reintentos=" + reintentos + "]";
	}
	
	
}
