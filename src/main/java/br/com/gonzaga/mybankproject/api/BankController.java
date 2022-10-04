package br.com.gonzaga.mybankproject.api;

import br.com.gonzaga.mybankproject.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import request.AccountRequest;

@RestController
public class BankController {

    // Injetando o corpo da requisição em bankService
    @Autowired
    private BankService bankService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("api/create-account")

    // @RequestyBody: recebe as informações do PostMan e armazena no parâmetro request.
    public String createAccount(@RequestBody AccountRequest request){
        System.out.println("entrou no método createAccount");

        // passando o parâmetro para bankService
        bankService.createAccount(request);
        return "sucess";
    }
}
