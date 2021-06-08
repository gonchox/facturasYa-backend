package com.appfacturasya.resource;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class SaveRateTermResource {
    @NotNull
    private Integer daysPerYear;

    @NotNull
    @NotBlank
    private Integer rateTerm;

    @NotNull
    @NotBlank
    private Double effectiveRate;

    @NotNull
    @NotBlank
    @DateTimeFormat
    private Date discountDate;
}
