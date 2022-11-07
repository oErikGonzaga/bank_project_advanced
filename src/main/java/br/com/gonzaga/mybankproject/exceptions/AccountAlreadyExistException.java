package br.com.gonzaga.mybankproject.exceptions;

public class AccountAlreadyExistException extends RuntimeException{

    public AccountAlreadyExistException(String message){
        super(message);
    }
}
