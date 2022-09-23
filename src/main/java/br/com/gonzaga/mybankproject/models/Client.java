package br.com.gonzaga.mybankproject.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

// Lombok
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

// Hibernate
@Entity
@Table(name = "client")

public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private Integer phone;

    private String document;

    private LocalDate birthdate;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;




}
