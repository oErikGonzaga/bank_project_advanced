package br.com.gonzaga.bankproject.models;

import lombok.*;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Entity;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.io.Serializable;

// Lombok
@Getter
@Setter
@ToString
@EqualsAndHashCode

// Hibernate
@Entity
@Table(name = "address")

public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer cep;
    private String city;
    private String state;

    @Column(name = "house_number")
    private String number;
    private String address;
    private String secondAddress;

}
