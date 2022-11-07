package br.com.gonzaga.mybankproject.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

// Camada de Modelo (Model), conforme padrão M.V.C.

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

    @Column(nullable = false, length = 150)
    private String name;

    private String email;

    private Long phone;

    private String document;

    private LocalDate birthdate;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;




}
