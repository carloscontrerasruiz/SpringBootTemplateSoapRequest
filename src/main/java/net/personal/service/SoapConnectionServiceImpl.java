package net.personal.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.personal.configurations.ConfigurationsValues;
import net.personal.exception.ServiceSoapException;
import net.personal.models.ClienteServicioRequest;
import net.personal.models.ClienteServicioResponse;
import net.personal.models.ServicioSoapResponse;
import net.personal.repository.EmuladorSoapService;
import net.personal.repository.SoapServiceBajaventaTARJETAS;
import net.personal.utils.Utils;

@Service
public class SoapConnectionServiceImpl implements SoapConnectionService {
	
	@Autowired
	ConfigurationsValues configurationsValues;
	
	@Autowired
	SoapServiceBajaventaTARJETAS SoapServiceBajaventaTARJETAS;
	
	@Autowired
	EmuladorSoapService emuladorSoapService;
	
	@Autowired
	Utils utils;
	
	Logger logger = LoggerFactory.getLogger(SoapConnectionServiceImpl.class);
	
	@Override
	public ClienteServicioResponse notificateSoap(ClienteServicioRequest clienteServicioRequest) {
		ServicioSoapResponse responseFromRepository = new ServicioSoapResponse();
		
		try {
			if(clienteServicioRequest.getCantidad() != clienteServicioRequest.getTiSeries().size()) {
				throw new ServiceSoapException("Revisar valores de envio Cantidad!=TISERIES");
			}
			
			if(configurationsValues.isEmulador()) {
				logger.info("Emulador Service");
				responseFromRepository = emuladorSoapService.emuladorSoapServiceBajaTARJETAS(configurationsValues.getTipoRespuesta(), configurationsValues.getNumeroReintentos());
			}else {
				//Aqui deberan estar las validaciones de longitud
				if(clienteServicioRequest.getMaterial().length() < 18) {
					String formatted = ("000000000000000000" + clienteServicioRequest.getMaterial()).substring(clienteServicioRequest.getMaterial().length());
					clienteServicioRequest.setMaterial(formatted);
				}
				
				if(clienteServicioRequest.getFecha().indexOf('/') >= 0) {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date d = sdf.parse(clienteServicioRequest.getFecha());
					sdf.applyPattern("dd.MM.yyyy");
					clienteServicioRequest.setFecha(sdf.format(d));
				}
				responseFromRepository = SoapServiceBajaventaTARJETAS.SoapBajaTARJETAS(clienteServicioRequest);
				logger.info(utils.formatResponseFromSoap(responseFromRepository));
			}
			
		}catch(Exception e) {
			logger.info(String.format("%s",e.getMessage()));
			return responseErrorService(String.format("%s",e.getMessage()));
		}
		
		//Se cambian los objetos de Soap para respuesta al cliente
		return parseResponseSoap(responseFromRepository);
	}

	
	
	private ClienteServicioResponse responseErrorService(String format) {
		ClienteServicioResponse responseClientObject = new ClienteServicioResponse();
		responseClientObject.setReintentos(0);
		responseClientObject.setError(true);
		responseClientObject.setErrorMensaje(format);
		responseClientObject.setDocYear("0000");
		responseClientObject.setMatDoc("");
		return responseClientObject;
	}



	private ClienteServicioResponse parseResponseSoap(ServicioSoapResponse responseFromSoap) {
		ClienteServicioResponse responseClientObject = new ClienteServicioResponse();
		responseClientObject.setReintentos(responseFromSoap.getReintentos());
		if(!responseFromSoap.isError()) {
			responseClientObject.setError(false);
			responseClientObject.setErrorMensaje("");
		}else{
			responseClientObject.setError(true);
			responseClientObject.setErrorMensaje(responseFromSoap.getErrorMessage());
		}
		responseClientObject.setMatDoc(responseFromSoap.getMatDoc());
		responseClientObject.setDocYear(responseFromSoap.getDocYear());
		responseClientObject.setListResponse(responseFromSoap.getListResponse());
		
		return responseClientObject;
	}
}
