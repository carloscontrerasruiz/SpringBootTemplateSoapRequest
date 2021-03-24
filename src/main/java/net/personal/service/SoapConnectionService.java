package net.personal.service;

import net.personal.models.ClienteServicioRequest;
import net.personal.models.ClienteServicioResponse;

public interface SoapConnectionService {
	public ClienteServicioResponse notificateSoap(ClienteServicioRequest clienteServicioRequest);
}
