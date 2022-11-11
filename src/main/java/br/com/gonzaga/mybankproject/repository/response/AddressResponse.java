package br.com.gonzaga.mybankproject.repository.response;

import lombok.Data;
import java.io.Serializable;

@Data
public class AddressResponse implements Serializable {

    private String uf;
    private String cep;
    private String localidade;
    private String logradouro;
    private boolean erro;
        //  Lembrete: primitivos quando são criados, nascem como 0 ou false.
}
