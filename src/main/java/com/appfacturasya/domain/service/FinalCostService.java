package com.appfacturasya.domain.service;

import com.appfacturasya.domain.model.FinalCost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface FinalCostService {
    ResponseEntity<?> deleteFinalCost(Long finalCostId, Long operationId);

    FinalCost updateFinalCost(Long finalCostId, Long operationId, FinalCost finalCostRequest);

    Page<FinalCost> getAllFinalCostsByOperationId(Long operationId, Pageable pageable);

    FinalCost createFinalCost(FinalCost finalCost,Long operationId);

    FinalCost getFinalCostById(Long finalCostId);

    Page<FinalCost> getAllFinalCosts(Pageable pageable);
}
