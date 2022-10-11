package br.com.gonzaga.mybankproject.repository;

import br.com.gonzaga.mybankproject.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository                         /* Herança é representada por extends,
seguida do nome da classe da qual se deseja herdar.*/

public interface AccountRepository  extends JpaRepository<Account, Long> {

    // findFirstBy é do SpringData, encontra o primeiro de Account pelo numero.
    // O Spring monta uma query aqui.
    Optional<Account> findFirstByNumber(Long n);

}
