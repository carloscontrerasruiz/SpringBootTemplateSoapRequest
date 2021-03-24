package net.personal.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import net.personal.clientesoap.bajaventa.BAPIRET2;
import net.personal.models.ServicioSoapResponse;

@Component
public class EmuladorSoapServiceImpl implements EmuladorSoapService {

	@Override
	public ServicioSoapResponse emuladorSoapServiceBajaTARJETAS(String tipoRespuesta, int reintentos) {
		if(tipoRespuesta.equalsIgnoreCase("S")) {
			//Respuesta Success
			return successResponse();
		}else {
			return errorResponse(tipoRespuesta, reintentos);
		}
	}
	
	private ServicioSoapResponse successResponse() {
		List<BAPIRET2> list = new ArrayList<>();
		return new ServicioSoapResponse("4900021084","2021",list,false,"",0);
	}
	
	private ServicioSoapResponse errorResponse(String tipoRespuesta, int reintentos) {
		List<BAPIRET2> list = new ArrayList<>();
		BAPIRET2 item = new BAPIRET2();
		item.setTYPE(tipoRespuesta);
		item.setID("M7");
		item.setNUMBER("53");
		item.setMESSAGE("Solo es posible contabilizar en los per�odos 2021/01 y 2020/12 de la sociedad MX01|");
		item.setLOGNO("1");
		item.setLOGMSGNO("0");
		item.setMESSAGEV1("Error1");
		item.setMESSAGEV2("");
		item.setMESSAGEV3("");
		item.setMESSAGEV4("");
		item.setPARAMETER("GOODSMVT_ITEM");
		item.setROW(1);
		item.setFIELD("");
		item.setSYSTEM("EDMCLNT102");
		list.add(item);
		return new ServicioSoapResponse("","0000",list,
				true,"Solo es posible contabilizar en los per�odos 2021/01 y 2020/12 de la sociedad MX01", reintentos);
	}

}
