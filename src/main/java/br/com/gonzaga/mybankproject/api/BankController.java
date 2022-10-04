package br.com.gonzaga.mybankproject.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("api/create-account")
    public String createAccount(){
        return "sucess";
    }
}
