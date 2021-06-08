package com.appfacturasya.resource;

import lombok.Data;

@Data
public class InitialCostResource {

    private Long id;
    private Long operationId;
    private String reason;
    private String expressedName;
    private Double expressedValue;
}
