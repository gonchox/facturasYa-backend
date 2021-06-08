package com.appfacturasya.domain.service;

import com.appfacturasya.domain.model.Operation;
import com.appfacturasya.domain.model.RateTerm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface OperationService {
    ResponseEntity<?> deleteOperation(Long operationId, Long userId, Long rateTermId);

    Page<Operation> getAllOperationsByUserId(Long userId, Pageable pageable);

    Operation updateOperation(Long operationId, Operation operationRequest, Long userId, Long rateTermId);

    Operation createOperation(Operation operation, Long userId, Long rateTermId);

    Operation getOperationById(Long operationId);

    Page<Operation> getAllOperations(Pageable pageable);
}
