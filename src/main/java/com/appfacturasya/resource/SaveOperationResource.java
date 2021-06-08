package com.appfacturasya.resource;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class SaveOperationResource {

    @NotNull
    @NotBlank
    private Date operationDate;

    @NotNull
    @NotBlank
    private Double totalAmount;

    @NotNull
    @NotBlank
    private Double tir;
}
