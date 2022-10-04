package br.com.gonzaga.mybankproject;

import br.com.gonzaga.mybankproject.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankProjectApplication implements CommandLineRunner {

	@Autowired
	private AddressRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(BankProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		Address address = Address
//				.builder()
//				.cep("02542110")
//				.city("Sao Paulo")
//				.state("SP")
//				.number("641")
//				.address("Rua Abura")
//				.secondAddress("A")
//				.build();
//
//		Address addressSaved = repository.save(address);
//
//		System.out.println(addressSaved);

	}
}
