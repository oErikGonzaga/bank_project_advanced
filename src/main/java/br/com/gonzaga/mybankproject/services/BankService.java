package br.com.gonzaga.mybankproject.services;

import br.com.gonzaga.mybankproject.model.Account;
import br.com.gonzaga.mybankproject.request.AccountRequest;

import java.math.BigDecimal;
import java.util.List;

public interface BankService {

    Account createAccount(AccountRequest accountRequest);
    void deposit (Long accountNumber, BigDecimal value);
    List<Account> listAll();

}
