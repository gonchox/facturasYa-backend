package com.appfacturasya.domain.repository;

import com.appfacturasya.domain.model.Operation;
import com.appfacturasya.domain.model.RateTerm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
    Page<Operation> findByUserId(Long userId, Pageable pageable);
    Page<Operation> findByRateTermId(Long rateTermId, Pageable pageable);

}
