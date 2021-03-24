package net.personal.repository;

import net.personal.exception.ServiceSoapException;
import net.personal.models.ClienteServicioRequest;
import net.personal.models.ServicioSoapResponse;

public interface SoapServiceBajaventaTARJETAS {
	public ServicioSoapResponse SoapBajaTARJETAS(ClienteServicioRequest request) throws ServiceSoapException;
}
