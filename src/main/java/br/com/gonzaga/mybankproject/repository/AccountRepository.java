package br.com.gonzaga.mybankproject.repository;

import br.com.gonzaga.mybankproject.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository                         /* Herança é representada por extends,
seguida do nome da classe da qual se deseja herdar.*/

public interface AccountRepository extends JpaRepository<Account, Long> {

    // findFirstBy é do SpringData, encontra o primeiro de Account pelo número.
    // O Spring monta uma query aqui.
    Optional<Account> findFirstByNumber(Long n);


    //Query:    SELECT * FROM account a INNER JOIN client c ON (a.client_id = c.id)
    //          WHERE c.email = 'email@email.com' OR c.document = '00000000000';
    Optional<Account> findFirstByClientDocumentOrClientEmail(String document, String email);
}
