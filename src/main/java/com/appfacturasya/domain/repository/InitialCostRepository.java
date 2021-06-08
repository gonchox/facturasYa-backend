package com.appfacturasya.domain.repository;

import com.appfacturasya.domain.model.InitialCost;
import com.appfacturasya.domain.model.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InitialCostRepository extends JpaRepository<InitialCost, Long> {
    Page<InitialCost> findByOperationId(Long operationId, Pageable pageable);
}
