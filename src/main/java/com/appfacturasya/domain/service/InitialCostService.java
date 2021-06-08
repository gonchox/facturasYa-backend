package com.appfacturasya.domain.service;

import com.appfacturasya.domain.model.InitialCost;
import com.appfacturasya.domain.model.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface InitialCostService {
    ResponseEntity<?> deleteInitialCost(Long initialCostId, Long operationId);

    InitialCost updateInitialCost(Long initialCostId, Long operationId, InitialCost initialCostRequest);

    Page<InitialCost> getAllInitialCostsByOperationId(Long operationId, Pageable pageable);

    InitialCost createInitialCost(InitialCost initialCost,Long operationId);

    InitialCost getInitialCostById(Long initialCostId);

    Page<InitialCost> getAllInitialCosts(Pageable pageable);
}
