package br.com.gonzaga.mybankproject.api;

import br.com.gonzaga.mybankproject.model.Account;
import br.com.gonzaga.mybankproject.services.BankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.gonzaga.mybankproject.request.AccountRequest;

import java.util.Objects;

// Camada de Controle (Controller), conforme padrão M.V.C.

@Slf4j
@RestController
public class BankController {

    // Injetando o corpo da requisição em bankService
    @Autowired
    private BankService bankService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("api/create-account")

    // @RequestyBody: recebe as informações do PostMan e armazena no parâmetro request.
    public ResponseEntity<Account> createAccount(@RequestBody AccountRequest request) {
        log.info("BankController.createAccount - init");

        Account account = bankService.createAccount(request);

        if (Objects.isNull(account))
            return ResponseEntity.badRequest().body(null);

        log.info("BankController.createAccount - init");

        return ResponseEntity.ok(account);

    }

}