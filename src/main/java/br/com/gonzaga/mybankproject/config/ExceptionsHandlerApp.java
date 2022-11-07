package br.com.gonzaga.mybankproject.config;

import br.com.gonzaga.mybankproject.exceptions.AccountAlreadyExistException;
import br.com.gonzaga.mybankproject.exceptions.AddressNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandlerApp {

    // Manipulador de Exception
    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<String> addressNotFoundException(AddressNotFoundException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    @ExceptionHandler(AccountAlreadyExistException.class)
    public ResponseEntity<String> accountNotFoundException(AccountAlreadyExistException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
