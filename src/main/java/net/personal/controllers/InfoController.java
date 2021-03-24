package net.personal.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.com.security.DesencriptaPropiedades;

@RestController
@RequestMapping(value="tarjetas/api/v1")
public class InfoController {
	
	@Autowired
	private ServerProperties serverProperties;
	
	Logger logger = LoggerFactory.getLogger(InfoController.class);
	
	@GetMapping(value = "/info")
	public String initialDefaultRequest() {
		return "Aplicacion corriendo en el puerto "+serverProperties.getPort();
	}
	
	//Esta funcion encriptara un password usando la libreria DSSCEncriptionPlaceHolder
	//Por seguridad el password solo se mostrara como log en el servidor
	@GetMapping(value = "/encriptarPassword")
	public String encriptPassword(@RequestParam("salt") String key, @RequestParam("pass") String cifrado) {
		try {
			cifrado = DesencriptaPropiedades.encripta(key, cifrado);
			logger.info(String.format("valor desde la ruta TarjetasvTARJETAS/api/v1/encriptarPassword %s", cifrado));
			return "Accion realizada correctamente";
		} catch (Exception e) {
			return "Error al realizar la accion "+e.getMessage();
		}
	}
}
