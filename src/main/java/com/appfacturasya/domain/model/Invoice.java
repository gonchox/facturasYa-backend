package com.appfacturasya.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "invoices")
@Data
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private Date issueDate;

    @NotNull
    @NotBlank
    private Date paymentDay;

    @NotNull
    @NotBlank
    private Double total;

    @NotNull
    @NotBlank
    private Double retention;

    @NotNull
    @NotBlank
    private String currency;

    @NotNull
    @NotBlank
    private Double debtorRUC;

    @NotNull
    @NotBlank
    @Size(max = 35)
    private String debtorName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "operation_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Operation operation;

}
