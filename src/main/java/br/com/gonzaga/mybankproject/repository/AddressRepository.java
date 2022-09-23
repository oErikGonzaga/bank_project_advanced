package br.com.gonzaga.mybankproject.repository;

import br.com.gonzaga.mybankproject.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
