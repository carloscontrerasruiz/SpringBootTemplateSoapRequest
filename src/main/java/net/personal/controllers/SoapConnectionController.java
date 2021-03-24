package net.personal.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.personal.models.ClienteServicioRequest;
import net.personal.models.ClienteServicioResponse;
import net.personal.service.SoapConnectionService;

@RestController
@RequestMapping(value="tarjetas/api/v1")
public class SoapConnectionController {
	
	@Autowired
	SoapConnectionService SoapConnectionService;
	
	Logger logger = LoggerFactory.getLogger(SoapConnectionController.class);
	
	@PostMapping(value="/soap-notification",consumes="application/json",produces="application/json")
	@ResponseBody
	public ResponseEntity<ClienteServicioResponse> notificateSoap(@Valid @RequestBody ClienteServicioRequest request) {
		ClienteServicioResponse response;
		HttpStatus responseStatus;
		logger.info("INICIA LA PETICION");
		response = SoapConnectionService.notificateSoap(request);
		if(response.isError()) {
			responseStatus = HttpStatus.SERVICE_UNAVAILABLE;
		}else {
			responseStatus =  HttpStatus.OK;
		}
		logger.info("TERMINA LA PETICION");
		return new ResponseEntity<>(response, responseStatus);
	}
}
