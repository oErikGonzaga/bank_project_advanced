package br.com.gonzaga.mybankproject.services.impl;

import br.com.gonzaga.mybankproject.client.ViaCepClient;
import br.com.gonzaga.mybankproject.exceptions.AccountAlreadyExistException;
import br.com.gonzaga.mybankproject.exceptions.AccountValidationException;
import br.com.gonzaga.mybankproject.exceptions.AddressNotFoundException;
import br.com.gonzaga.mybankproject.model.Account;
import br.com.gonzaga.mybankproject.model.Address;
import br.com.gonzaga.mybankproject.model.Client;
import br.com.gonzaga.mybankproject.repository.AccountRepository;
import br.com.gonzaga.mybankproject.repository.AddressRepository;
import br.com.gonzaga.mybankproject.repository.ClientRepository;
import br.com.gonzaga.mybankproject.response.AddressResponse;
import br.com.gonzaga.mybankproject.services.BankService;
import br.com.gonzaga.mybankproject.util.AppUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import br.com.gonzaga.mybankproject.request.AccountRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static br.com.gonzaga.mybankproject.util.DateUtil.stringToLocalDate;
import static br.com.gonzaga.mybankproject.util.NumberUtil.generateRandomNumber;

@Slf4j
@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {

    private final ViaCepClient viaCepClient;
    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;
    private final AccountRepository accountRepository;


    @Override
    public Account createAccount(AccountRequest request) {
        log.info("BankServiceimpl.createAccount init - account={}", request);

        // todo -  o cep informado existe
        // todo - o cpf ou email ja tem uma conta
        // todo -  verificar se a senha só tem numeros

        accountRepository
                .findFirstByClientDocumentOrClientEmail(request.getDocument(), request.getEmail())
                .ifPresent(account -> {
                    String baseErrorMessage = "A conta já existe para CPF e Email informado. Numero da Conta: ";
                    throw new AccountAlreadyExistException(baseErrorMessage + account.getNumber());
                });

        AddressResponse addressResponse = getAddress(request)
                .orElseThrow(() -> new AddressNotFoundException("Address Not Found"));   // Lançando nossa Exception

        if (!AppUtil.isCpfValid(request.getDocument()))
            throw new AccountValidationException("CPF Inválido");

        Address address = Address
                .builder()
                .cep(request.getCep())
                .number(request.getNumber())
                .state(addressResponse.getUf())
                .city(addressResponse.getLocalidade())
                .address(addressResponse.getLogradouro())
                .secondAddress(request.getSecondAddress())
                .build();

        Address savedAddress = addressRepository.save(address);

        Client client = Client
                .builder()
                .address(savedAddress)
                .name(request.getName())
                .email(request.getEmail())
                .document(request.getDocument())
                .phone(Long.parseLong(request.getPhone()))
                .birthdate(stringToLocalDate(request.getBirthdate(), "dd/MM/yyyy"))
                .build();

        Client savedClient = clientRepository.save(client);

        Account account = Account
                .builder()
                .number(generateAccountNumber())
                .client(savedClient)
                .registration(LocalDateTime.now())
                .password(Long.parseLong(request.getPassword()))
                .build();

        Account savedAccount = accountRepository.save(account);
        log.info("BankServiceimpl.createAccount end - account={}", savedAccount);
        return account;
    }
    @Override
    public void deposit(Long accountNumber, BigDecimal value) {

        // buscar a conta no banco
        Account account = accountRepository
                .findFirstByNumber(accountNumber)
                .orElseThrow(() -> new AccountValidationException("Conta não localizada"));

        // verificar se a está ativa
        if (Objects.isNull(account.getDeactivation()))
            throw new AccountValidationException("Conta inativa");

        // adicionando valor
        BigDecimal total = account.getBalance().add(value);
        account.setBalance(total);

        // salvar a alteração
        accountRepository.save(account);
    }
    @Override
    public List<Account> listAll() {
        return null;
    }

    private Long generateAccountNumber() {
        Long number = generateRandomNumber();

        while (accountRepository.findFirstByNumber(number).isPresent()) {
            number = generateRandomNumber();
        }
        return number;
    }

    private Optional<AddressResponse> getAddress(AccountRequest request){

        try {
            AddressResponse addressResponse = viaCepClient.getAddressByCep(request.getCep());

            if (addressResponse.isErro()){          /*  quando dados são booleans, não utilizamos
                                                        o método 'get', utilizamos 'is' (isErro) */
                System.out.println("teve erro");
            }
            return Optional.of(addressResponse);

        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
