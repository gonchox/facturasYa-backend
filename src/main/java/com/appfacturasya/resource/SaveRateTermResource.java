package com.appfacturasya.resource;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class SaveRateTermResource {
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
}
