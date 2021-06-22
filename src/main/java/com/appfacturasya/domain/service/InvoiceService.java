package com.appfacturasya.domain.service;

import com.appfacturasya.domain.model.Invoice;
import com.appfacturasya.domain.model.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface InvoiceService {

    ResponseEntity<?> deleteInvoice(Long invoiceId);

    Invoice updateInvoice(Long invoiceId, Invoice invoiceRequest);

    Page<Invoice> getAllInvoicesByOperationId(Long operationId, Pageable pageable);

    Invoice createInvoice(Invoice invoice,Long operationId);

    Invoice getInvoiceById(Long invoiceId);

    Page<Invoice> getAllInvoices(Pageable pageable);
}
