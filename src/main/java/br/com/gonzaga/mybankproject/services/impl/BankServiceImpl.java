package br.com.gonzaga.mybankproject.services.impl;

import br.com.gonzaga.mybankproject.models.Account;
import br.com.gonzaga.mybankproject.models.Address;
import br.com.gonzaga.mybankproject.models.Client;
import br.com.gonzaga.mybankproject.repository.AccountRepository;
import br.com.gonzaga.mybankproject.services.BankService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import request.AccountRequest;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {

    private final AccountRepository accountRepository;

    @Override
    public Account createAccount(AccountRequest request) {
        log.info("BankServiceimpl.createAccount init - account={}", request);

        Address address = Address
                .builder()
                .city("São Paulo")
                .state("SP")
                .address("Rua Teste")
                .cep(request.getCep())
                .number(request.getNumber())
                .secondAddress(request.getSecondAddress())
                .build();

        Client client = Client
                .builder()
                .address(address)
                .name(request.getName())
                .document(request.getDocument())
                .build();

        Account account = Account
                .builder()
                .number(1001L)
                .client(client)
                .registration(LocalDateTime.now())
                .password(Long.parseLong(request.getPassword()))
                .build();

        Account savedAccount = accountRepository.save(account);

        log.info("BankServiceimpl.createAccount end - account={}", savedAccount);

        return account;
    }
}
