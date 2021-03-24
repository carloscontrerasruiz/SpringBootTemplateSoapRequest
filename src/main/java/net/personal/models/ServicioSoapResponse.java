package net.personal.models;

import java.util.List;

import net.personal.clientesoap.bajaventa.BAPIRET2;

public class ServicioSoapResponse {
	
	private String matDoc;
	private String docYear;
	private List<BAPIRET2> listResponse;
	private boolean error;
	private String errorMessage;
	private int reintentos;
	
	public ServicioSoapResponse() {}

	public ServicioSoapResponse(String matDoc, String docYear, List<BAPIRET2> listResponse, 
			boolean error, String errorMessage, int reintentos) {
		super();
		this.matDoc = matDoc;
		this.docYear = docYear;
		this.listResponse = listResponse;
		this.error = error;
		this.errorMessage = errorMessage;
		this.reintentos = reintentos;
	}
	
	public int getReintentos() {
		return reintentos;
	}

	public void setReintentos(int reintentos) {
		this.reintentos = reintentos;
	}

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

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "ServicioSoapResponse [matDoc=" + matDoc + ", docYear=" + docYear + ", listResponse=" + listResponse
				+ ", error=" + error + ", errorMessage=" + errorMessage + ", reintentos=" + reintentos + "]";
	}
	
}
