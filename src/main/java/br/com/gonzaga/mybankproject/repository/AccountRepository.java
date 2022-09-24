package br.com.gonzaga.mybankproject.repository;

import br.com.gonzaga.mybankproject.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
