package com.appfacturasya.service;

import com.appfacturasya.domain.model.Operation;
import com.appfacturasya.domain.model.RateTerm;
import com.appfacturasya.domain.model.User;
import com.appfacturasya.domain.repository.OperationRepository;
import com.appfacturasya.domain.repository.RateTermRepository;
import com.appfacturasya.domain.repository.UserRepository;
import com.appfacturasya.domain.service.OperationService;
import com.appfacturasya.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RateTermRepository rateTermRepository;

    @Override
    public ResponseEntity<?> deleteOperation(Long operationId) {
        Operation operation = operationRepository.findById(operationId).orElseThrow(() -> new ResourceNotFoundException("Operation", "Id", operationId));
        operationRepository.delete(operation);
        return ResponseEntity.ok().build();
    }

    @Override
    public Page<Operation> getAllOperationsByUserId(Long userId, Pageable pageable) {
        return operationRepository.findByUserId(userId, pageable);
    }

    @Override
    public Operation updateOperation(Long operationId, Operation operationRequest) {
        Operation operation = operationRepository.findById(operationId)
                .orElseThrow(() -> new ResourceNotFoundException("Operation", "Id", operationId));
        operation.setOperationDate(operationRequest.getOperationDate());
        operation.setTir(operationRequest.getTir());
        operation.setTotalAmount(operationRequest.getTotalAmount());
        return operationRepository.save(operation);
    }

    @Override
    public Operation createOperation(Operation operation, Long userId, Long rateTermId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        operation.setUser(user);
        RateTerm rateTerm = rateTermRepository.findById(rateTermId).orElseThrow(() -> new ResourceNotFoundException("RateTerm", "Id", rateTermId));
        operation.setRateTerm(rateTerm);
        return operationRepository.save(operation);
    }

    @Override
    public Operation getOperationById(Long operationId) {
        Operation operation = operationRepository.findById(operationId).orElseThrow(() -> new ResourceNotFoundException("Operation", "Id", operationId));
        return operation;
    }

    @Override
    public Page<Operation> getAllOperations(Pageable pageable) {
        return operationRepository.findAll(pageable);
    }
}
