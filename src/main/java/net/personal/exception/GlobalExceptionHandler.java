package net.personal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import net.personal.models.ClienteServicioResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	//handle Method argument validation errors
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ClienteServicioResponse> handleGlobalException(MethodArgumentNotValidException exception){
		ClienteServicioResponse responseClientObject = new ClienteServicioResponse();
		responseClientObject.setReintentos(0);
		responseClientObject.setError(true);
		responseClientObject.setErrorMensaje(exception.getBindingResult().getFieldError() != null ? 
				exception.getBindingResult().getFieldError().getDefaultMessage() : "Error de validacion");
		responseClientObject.setDocYear("0000");
		responseClientObject.setMatDoc("");
		return new ResponseEntity<>(responseClientObject, HttpStatus.BAD_REQUEST);
	}

	//handle Global exception
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ClienteServicioResponse> handleGlobalException(Exception exception){
		ClienteServicioResponse responseClientObject = new ClienteServicioResponse();
		responseClientObject.setReintentos(0);
		responseClientObject.setError(true);
		responseClientObject.setErrorMensaje(exception.getMessage());
		responseClientObject.setDocYear("0000");
		responseClientObject.setMatDoc("");
		return new ResponseEntity<>(responseClientObject, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
