package com.appfacturasya.domain.repository;

import com.appfacturasya.domain.model.FinalCost;
import com.appfacturasya.domain.model.InitialCost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinalCostRepository extends JpaRepository<FinalCost, Long> {
    Page<FinalCost> findByOperationId(Long operationId, Pageable pageable);
}
