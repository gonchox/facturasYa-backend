package com.appfacturasya.resource;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class SaveInvoiceResource {

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date issueDate;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDay;

    @NotNull
    private Double total;

    @NotNull
    private Double retention;

    @NotNull
    @NotBlank
    private String currency;

    @NotNull
    private Double debtorRUC;

    @NotNull
    @NotBlank
    @Size(max = 35)
    private String debtorName;
}
