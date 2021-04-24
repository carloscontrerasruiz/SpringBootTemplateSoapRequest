package net.personal.configurations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigurationsValues {
	
	Logger logger = LoggerFactory.getLogger(ConfigurationsValues.class);
	
	@Value("${testproperties.emulador}")
	private boolean emulador;
	
	@Value("${testproperties.tipoRespuesta}")
	private String tipoRespuesta;
	
	@Value("${testproperties.numeroReintentos}")
	private int numeroReintentos;
	
	@Value("${testproperties.Soap.url}")
	private String SoapUrl;
	
	@Value("${testproperties.Soap.username}")
	private String SoapUsername;
	
	@Value("${testproperties.Soap.pass}")
	private String SoapPass;
	
	@Value("${testproperties.Soap.key}")
	private String SoapKey;

	@Value("${testproperties.connectiontimeout}")
	private int connectionTimeout;
	
	@Value("${testproperties.readtimeout}")
	private int readTimeout;

	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public int getReadTimeout() {
		return readTimeout;
	}

	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}
	
	public String getSoapKey() {
		return SoapKey;
	}

	public void setSoapKey(String SoapKey) {
		this.SoapKey = SoapKey;
	}

	public String getSoapPass() {
		return SoapPass;
	}

	public void setSoapPass(String SoapPass) {
		this.SoapPass = SoapPass;
	}

	public String getSoapUrl() {
		return SoapUrl;
	}

	public void setSoapUrl(String SoapUrl) {
		this.SoapUrl = SoapUrl;
	}

	public String getSoapUsername() {
		return SoapUsername;
	}

	public void setSoapUsername(String SoapUsername) {
		this.SoapUsername = SoapUsername;
	}

	

	public int getNumeroReintentos() {
		return numeroReintentos;
	}

	public void setNumeroReintentos(int numeroReintentos) {
		this.numeroReintentos = numeroReintentos;
	}

	public boolean isEmulador() {
		return emulador;
	}

	public void setEmulador(boolean emulador) {
		this.emulador = emulador;
	}

	public String getTipoRespuesta() {
		return tipoRespuesta;
	}

	public void setTipoRespuesta(String tipoRespuesta) {
		this.tipoRespuesta = tipoRespuesta;
	}

	
	
}
