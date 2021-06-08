package com.appfacturasya.service;

import com.appfacturasya.domain.model.Invoice;
import com.appfacturasya.domain.model.Operation;
import com.appfacturasya.domain.model.RateTerm;
import com.appfacturasya.domain.model.User;
import com.appfacturasya.domain.repository.InvoiceRepository;
import com.appfacturasya.domain.repository.OperationRepository;
import com.appfacturasya.domain.repository.RateTermRepository;
import com.appfacturasya.domain.service.InvoiceService;
import com.appfacturasya.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private OperationRepository operationRepository;

    @Override
    public ResponseEntity<?> deleteInvoice(Long invoiceId, Long operationId) {
        Invoice invoice = invoiceRepository.findById(invoiceId).orElseThrow(() -> new ResourceNotFoundException("Invoice", "Id", invoiceId));
        invoiceRepository.delete(invoice);
        return ResponseEntity.ok().build();
    }

    @Override
    public Invoice updateInvoice(Long invoiceId,Long operationId, Invoice invoiceRequest) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice", "Id", invoiceId));
       invoice.setCurrency(invoice.getCurrency());
       invoice.setDebtorName(invoice.getDebtorName());
       invoice.setDebtorRUC(invoice.getDebtorRUC());
       invoice.setIssueDate(invoice.getIssueDate());
       invoice.setPaymentDay(invoice.getPaymentDay());
       invoice.setTotal(invoice.getTotal());
       invoice.setRetention(invoice.getRetention());

        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice createInvoice(Invoice invoice, Long operationId) {
        Operation operation = operationRepository.findById(operationId).orElseThrow(() -> new ResourceNotFoundException("Operation", "Id", operationId));
        invoice.setOperation(operation);
        return invoiceRepository.save(invoice);
    }

    @Override
    public Page<Invoice> getAllInvoicesByOperationId(Long operationId, Pageable pageable) {
        return invoiceRepository.findByOperationId(operationId, pageable);
    }

    @Override
    public Invoice getInvoiceById(Long invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId).orElseThrow(() -> new ResourceNotFoundException("Invoice", "Id", invoiceId));
        return invoice;
    }

    @Override
    public Page<Invoice> getAllInvoices(Pageable pageable) {
        return invoiceRepository.findAll(pageable);
    }
}
