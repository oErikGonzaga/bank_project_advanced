package br.com.gonzaga.mybankproject.response;

import lombok.Data;
import java.io.Serializable;

@Data
public class AddressResponse implements Serializable {

    private String uf;
    private String cep;
    private String localidade;
    private String logradouro;
}
