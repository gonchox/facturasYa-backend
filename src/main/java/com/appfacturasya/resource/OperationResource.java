package com.appfacturasya.resource;

import lombok.Data;
import java.util.Date;

@Data
public class OperationResource {

    private Long id;
    private Long userId;
    private Long rateTermId;
    private Date operationDate;
    private Double totalAmount;
    private Double tir;

}
