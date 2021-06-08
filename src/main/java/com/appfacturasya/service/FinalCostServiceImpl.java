package com.appfacturasya.service;

import com.appfacturasya.domain.model.FinalCost;
import com.appfacturasya.domain.model.InitialCost;
import com.appfacturasya.domain.model.Operation;
import com.appfacturasya.domain.repository.FinalCostRepository;
import com.appfacturasya.domain.repository.InitialCostRepository;
import com.appfacturasya.domain.repository.OperationRepository;
import com.appfacturasya.domain.service.FinalCostService;
import com.appfacturasya.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FinalCostServiceImpl implements FinalCostService {

    @Autowired
    private FinalCostRepository finalCostRepository;
    @Autowired
    private OperationRepository operationRepository;

    @Override
    public ResponseEntity<?> deleteFinalCost(Long finalCostId, Long operationId) {
        FinalCost finalCost = finalCostRepository.findById(finalCostId).orElseThrow(() -> new ResourceNotFoundException("FinalCost", "Id", finalCostId));
        finalCostRepository.delete(finalCost);
        return ResponseEntity.ok().build();
    }

    @Override
    public FinalCost updateFinalCost(Long finalCostId, Long operationId, FinalCost finalCostRequest) {
        FinalCost finalCost = finalCostRepository.findById(finalCostId)
                .orElseThrow(() -> new ResourceNotFoundException("FinalCost", "Id", finalCostId));
        finalCost.setExpressedName(finalCost.getExpressedName());
        finalCost.setReason(finalCost.getReason());
        finalCost.setExpressedValue(finalCost.getExpressedValue());
        return finalCostRepository.save(finalCost);
    }

    @Override
    public Page<FinalCost> getAllFinalCostsByOperationId(Long operationId, Pageable pageable) {
        return finalCostRepository.findByOperationId(operationId, pageable);
    }

    @Override
    public FinalCost createFinalCost(FinalCost finalCost, Long operationId) {
        Operation operation = operationRepository.findById(operationId).orElseThrow(() -> new ResourceNotFoundException("Operation", "Id", operationId));
        finalCost.setOperation(operation);
        return finalCostRepository.save(finalCost);
    }

    @Override
    public FinalCost getFinalCostById(Long finalCostId) {
        FinalCost finalCost = finalCostRepository.findById(finalCostId).orElseThrow(() -> new ResourceNotFoundException("FinalCost", "Id", finalCostId));
        return finalCost;
    }

    @Override
    public Page<FinalCost> getAllFinalCosts(Pageable pageable) {
        return finalCostRepository.findAll(pageable);
    }
}
