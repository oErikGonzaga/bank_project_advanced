package br.com.gonzaga.mybankproject.exceptions;

public class AddressNotFoundException extends RuntimeException{

    // Criando minha propria Exception
    public AddressNotFoundException(String message){
        super(message);
    }
}
