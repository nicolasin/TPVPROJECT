package es.nicolas.uca.tpv.security.Handler;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import es.nicolas.uca.tpv.exceptions.EntityNotFoundException;
import es.nicolas.uca.tpv.exceptions.ExceptionResponse;



@ControllerAdvice
public class ErrorHandlerController {
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleException(EntityNotFoundException e) {
		ExceptionResponse er = new ExceptionResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(),
				System.currentTimeMillis());
		return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(SQLException.class)
	public ResponseEntity<ExceptionResponse> handleException(SQLException e) {
		ExceptionResponse er = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(),
				System.currentTimeMillis());
		return new ResponseEntity<>(er, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
