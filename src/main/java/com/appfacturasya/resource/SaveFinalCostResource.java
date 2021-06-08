package com.appfacturasya.resource;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;

@Data
public class SaveFinalCostResource {
    @NotNull
    @NotBlank
    @Lob
    private String reason;

    @NotNull
    @NotBlank
    private String expressedName;

    @NotNull
    @NotBlank
    private Double expressedValue;
}
