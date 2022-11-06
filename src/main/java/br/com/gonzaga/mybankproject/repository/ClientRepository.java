package br.com.gonzaga.mybankproject.repository;

import br.com.gonzaga.mybankproject.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {


}
