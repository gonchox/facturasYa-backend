package com.appfacturasya.domain.repository;

import com.appfacturasya.domain.model.Invoice;
import com.appfacturasya.domain.model.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Page<Invoice> findByOperationId(Long operationId, Pageable pageable);
}
