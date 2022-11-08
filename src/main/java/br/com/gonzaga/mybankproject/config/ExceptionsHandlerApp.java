package br.com.gonzaga.mybankproject.config;

import br.com.gonzaga.mybankproject.exceptions.AccountAlreadyExistException;
import br.com.gonzaga.mybankproject.exceptions.AccountValidationException;
import br.com.gonzaga.mybankproject.exceptions.AddressNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandlerApp {

    // Manipulador de Exception
    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<String> handle(AddressNotFoundException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    @ExceptionHandler(AccountAlreadyExistException.class)
    public ResponseEntity<String> handle(AccountAlreadyExistException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    @ExceptionHandler(AccountValidationException.class)
    public ResponseEntity<String> handle(AccountValidationException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    // handle: sobrecarga de método
}
