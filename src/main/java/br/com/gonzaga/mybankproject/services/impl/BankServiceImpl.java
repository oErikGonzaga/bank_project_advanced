package br.com.gonzaga.mybankproject.services.impl;

import br.com.gonzaga.mybankproject.client.ViaCepClient;
import br.com.gonzaga.mybankproject.models.Account;
import br.com.gonzaga.mybankproject.models.Address;
import br.com.gonzaga.mybankproject.models.Client;
import br.com.gonzaga.mybankproject.repository.AccountRepository;
import br.com.gonzaga.mybankproject.repository.AddressRepository;
import br.com.gonzaga.mybankproject.repository.ClientRepository;
import br.com.gonzaga.mybankproject.response.AddressResponse;
import br.com.gonzaga.mybankproject.services.BankService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import request.AccountRequest;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import static br.com.gonzaga.mybankproject.Utils.DateUtil.stringToLocalDate;
import static br.com.gonzaga.mybankproject.Utils.NumberUtil.generateRandomNumber;

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

        var addressResponse = getAddress(request).orElse(null);

        if (Objects.isNull(addressResponse)) {
            return null;
        }

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

            if (addressResponse.isErro()){
                System.out.println("teve erro");
            }

            return Optional.of(addressResponse);

        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
