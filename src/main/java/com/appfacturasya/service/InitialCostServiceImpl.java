package com.appfacturasya.service;

import com.appfacturasya.domain.model.InitialCost;
import com.appfacturasya.domain.model.Operation;
import com.appfacturasya.domain.repository.InitialCostRepository;
import com.appfacturasya.domain.repository.OperationRepository;
import com.appfacturasya.domain.service.InitialCostService;
import com.appfacturasya.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class InitialCostServiceImpl implements InitialCostService {

    @Autowired
    private InitialCostRepository initialCostRepository;
    @Autowired
    private OperationRepository operationRepository;

    @Override
    public ResponseEntity<?> deleteInitialCost(Long initialCostId, Long operationId) {
        InitialCost initialCost = initialCostRepository.findById(initialCostId).orElseThrow(() -> new ResourceNotFoundException("InitialCost", "Id", initialCostId));
        initialCostRepository.delete(initialCost);
        return ResponseEntity.ok().build();
    }

    @Override
    public InitialCost updateInitialCost(Long initialCostId, Long operationId, InitialCost initialCostRequest) {
        InitialCost initialCost = initialCostRepository.findById(initialCostId)
                .orElseThrow(() -> new ResourceNotFoundException("InitialCost", "Id", initialCostId));
        initialCost.setExpressedName(initialCost.getExpressedName());
        initialCost.setReason(initialCost.getReason());
        initialCost.setExpressedValue(initialCost.getExpressedValue());
        return initialCostRepository.save(initialCost);
    }

    @Override
    public Page<InitialCost> getAllInitialCostsByOperationId(Long operationId, Pageable pageable) {
        return initialCostRepository.findByOperationId(operationId, pageable);
    }

    @Override
    public InitialCost createInitialCost(InitialCost initialCost, Long operationId) {
        Operation operation = operationRepository.findById(operationId).orElseThrow(() -> new ResourceNotFoundException("Operation", "Id", operationId));
        initialCost.setOperation(operation);
        return initialCostRepository.save(initialCost);
    }

    @Override
    public InitialCost getInitialCostById(Long initialCostId) {
        InitialCost initialCost = initialCostRepository.findById(initialCostId).orElseThrow(() -> new ResourceNotFoundException("InitialCost", "Id", initialCostId));
        return initialCost;
    }

    @Override
    public Page<InitialCost> getAllInitialCosts(Pageable pageable) {
        return initialCostRepository.findAll(pageable);
    }
}
