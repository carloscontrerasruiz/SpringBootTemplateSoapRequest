package net.personal.repository;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

import net.personal.clientesoap.bajaventa.BAPIRET2;
import net.personal.clientesoap.bajaventa.ZMXMIMESSERIESTARJETAS;
import net.personal.clientesoap.bajaventa.ZMXMIMMFBAJAVENTATARJETAS;
import net.personal.clientesoap.bajaventa.ZMXMIMMFBAJAVENTATARJETASResponse;
import net.personal.clientesoap.bajaventa.ZMXMIMTYSERIESTARJETAS;
import net.personal.configurations.ConfigurationsValues;
import net.personal.exception.ServiceSoapException;
import net.personal.models.ClienteServicioRequest;
import net.personal.models.ServicioSoapResponse;
import net.personal.models.TiSeries;
import net.personal.utils.Utils;

@Component
public class SoapServiceBajaVentaImpl implements SoapServiceBajaventaTARJETAS {
	
	@Autowired
	WebServiceTemplate webServiceTemplate;
	
	@Autowired
	ConfigurationsValues configurationsValues;
	
	@Autowired
	Utils utils;
	
	Logger logger = LoggerFactory.getLogger(SoapServiceBajaVentaImpl.class);
	
	@Override
	public ServicioSoapResponse SoapBajaTARJETAS(ClienteServicioRequest clientRequest) throws ServiceSoapException {
		ZMXMIMMFBAJAVENTATARJETAS SoapRequest = new ZMXMIMMFBAJAVENTATARJETAS();
		int reintentos = 0;
		
		BigDecimal cantidadSoap = BigDecimal.valueOf(clientRequest.getCantidad());
		ZMXMIMTYSERIESTARJETAS seriesTARJETASList = new ZMXMIMTYSERIESTARJETAS();
		ZMXMIMESSERIESTARJETAS seriesTARJETASItem = new ZMXMIMESSERIESTARJETAS();
		
		for(TiSeries item: clientRequest.getTiSeries()) {
			seriesTARJETASItem.setLOTE(item.getLote());
			seriesTARJETASItem.setSERIE(item.getSerie());
			seriesTARJETASList.getItem().add(seriesTARJETASItem);
			seriesTARJETASItem = new ZMXMIMESSERIESTARJETAS();
		}
		
		SoapRequest.setPIALMACEN(clientRequest.getAlmacen());
		SoapRequest.setPICANTIDAD(cantidadSoap);
		SoapRequest.setPICENTRO(clientRequest.getCentro());
		SoapRequest.setPIFECHA(clientRequest.getFecha());
		SoapRequest.setPIFOLIO(clientRequest.getFOLIO());
		SoapRequest.setPIMATERIAL(clientRequest.getMaterial());
		SoapRequest.setPITIPOMOV(clientRequest.getTipoMovimiento().toUpperCase());
		SoapRequest.setPIUOM(clientRequest.getUom());
		SoapRequest.setTISERIES(seriesTARJETASList);
		ZMXMIMMFBAJAVENTATARJETASResponse response = null;
		try {
			logger.info(utils.formatRequestToSoap(SoapRequest));
			for(reintentos=0;reintentos<configurationsValues.getNumeroReintentos();reintentos++){
				response = (ZMXMIMMFBAJAVENTATARJETASResponse)webServiceTemplate.marshalSendAndReceive(SoapRequest);
				
				if(!response.getPOMATDOC().equalsIgnoreCase("") || !response.getPODOCYEAR().equalsIgnoreCase("0000")) {
					break;
				}
			}
			
			return parseToServicioSoapResponse(response,reintentos);
		}catch(Exception e) {
			throw new ServiceSoapException(String.format("%s",e.getMessage()));
		}
		
	}

	private ServicioSoapResponse parseToServicioSoapResponse(ZMXMIMMFBAJAVENTATARJETASResponse response, int reintentos) {
		boolean error = false;
		StringBuilder mensaje = new StringBuilder();
		
		if(response.getPOMATDOC().equalsIgnoreCase("") || response.getPODOCYEAR().equalsIgnoreCase("0000")) {
			error = true;
			for(BAPIRET2 item: response.getTORETURN().getItem()) {
				mensaje.append(item.getMESSAGE());
			}
		}
		
		
		return new ServicioSoapResponse(response.getPOMATDOC(), 
										response.getPODOCYEAR(), response.getTORETURN().getItem(), 
										error, mensaje.toString(),reintentos);
	}

}
