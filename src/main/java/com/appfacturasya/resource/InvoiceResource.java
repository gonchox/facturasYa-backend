package com.appfacturasya.resource;

import lombok.Data;
import java.util.Date;

@Data
public class InvoiceResource {

    private Long id;
    private Long operationId;
    private Date issueDate;
    private Date paymentDay;
    private Double total;
    private Double retention;
    private String currency;
    private Double debtorRUC;
    private String debtorName;
}
