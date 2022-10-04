package request;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccountRequest implements Serializable {

    private String cep;
    private String name;
    private String phone;
    private String email;
    private String number;
    private String document;
    private String password;
    private String birthdate;
    private String secondAddress;
}
