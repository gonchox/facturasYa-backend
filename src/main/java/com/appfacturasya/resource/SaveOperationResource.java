package com.appfacturasya.resource;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class SaveOperationResource {

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date operationDate;

    @NotNull
    private Double totalAmount;

    @NotNull
    private Double tir;
}
