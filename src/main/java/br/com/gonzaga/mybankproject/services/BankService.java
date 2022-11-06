package br.com.gonzaga.mybankproject.services;

import br.com.gonzaga.mybankproject.model.Account;
import br.com.gonzaga.mybankproject.request.AccountRequest;

public interface BankService {

    Account createAccount(AccountRequest accountRequest);
}
