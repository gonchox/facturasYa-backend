package com.appfacturasya.resource;

import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class RateTermResource {

    private Long id;
    private Integer daysPerYear;
    private String rateTerm;
    private Double effectiveRate;
    private Date discountDate;
}
