package net.personal.repository;

import net.personal.models.ServicioSoapResponse;

public interface EmuladorSoapService {
	public ServicioSoapResponse emuladorSoapServiceBajaTARJETAS(String tipoRespuesta, int reintentos);
}
