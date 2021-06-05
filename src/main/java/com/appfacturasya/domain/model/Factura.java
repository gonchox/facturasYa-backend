package com.appfacturasya.domain.model;

import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "factura")
@Data
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 15)
    @NaturalId
    @Column(unique = true)
    private String username;

    @NotNull
    @NotBlank
    @Size(max = 15, min = 5)
    private String password;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String email;

}
