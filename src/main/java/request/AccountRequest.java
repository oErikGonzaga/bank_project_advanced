package request;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccountRequest implements Serializable {

    private String name;
    private String document;
    private String birthdate;
    private String phone;
    private String email;
    private String cep;
    private String number;
    private String secondAddress;
    private String password;
}
