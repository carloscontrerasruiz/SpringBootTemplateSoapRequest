package net.personal.utils;

import org.springframework.stereotype.Component;

import net.personal.clientesoap.bajaventa.ZMXMIMESSERIESTARJETAS;
import net.personal.clientesoap.bajaventa.ZMXMIMMFBAJAVENTATARJETAS;
import net.personal.models.ServicioSoapResponse;

@Component
public class Utils {
	
	public String formatResponseFromSoap(ServicioSoapResponse response) {
		return "Trama Respuesta Soap MATDOC="+ response.getMatDoc()+" , "+
				"DOCYEAR="+response.getDocYear()+" , "+
				"ERRORMESSAGE="+response.getErrorMessage()+" , "+
				"ERROR="+response.isError()+" , "+
				"REINTENTOS="+response.getReintentos();
	}

	public String formatRequestToSoap(ZMXMIMMFBAJAVENTATARJETAS request) {
		StringBuilder serieLote =  new StringBuilder();
		for(ZMXMIMESSERIESTARJETAS item:request.getTISERIES().getItem()) {
			serieLote.append("[LOTE="+item.getLOTE() + " SERIE=" + item.getSERIE()+"]");
		}
		return "Trama envio Soap PIALMACEN="+request.getPIALMACEN()+" , "+
				"PICANTIDAD="+request.getPICANTIDAD()+" , "+
				"PICENTRO="+request.getPICENTRO()+" , "+
				"PIFECHA="+request.getPIFECHA()+" , "+
				"PIFOLIO="+request.getPIFOLIO()+" , "+
				"PIMATERIAL="+request.getPIMATERIAL()+" , "+
				"PITIPOMOV="+request.getPITIPOMOV()+" , "+
				"PIUOM="+request.getPIUOM()+" , "+
				"PISERIELOTE="+serieLote.toString();
	}
}
