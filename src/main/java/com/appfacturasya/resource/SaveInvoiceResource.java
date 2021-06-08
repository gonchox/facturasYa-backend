package com.appfacturasya.resource;

import com.sun.istack.NotNull;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class SaveInvoiceResource {

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
}
