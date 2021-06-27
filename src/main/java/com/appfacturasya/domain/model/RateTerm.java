package com.appfacturasya.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.NaturalId;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "rate_terms")
@Data
public class RateTerm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer daysPerYear;

    @NotNull
    @NotBlank
    private String rateTerm;

    @NotNull
    private Double effectiveRate;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat
    private Date discountDate;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "rateTerm")
    @JsonIgnore
    private Operation operation;

}
