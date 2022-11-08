package br.com.gonzaga.mybankproject.exceptions;

public class AccountValidationException extends RuntimeException{

    public AccountValidationException(String message){
        super(message);
    }

}
