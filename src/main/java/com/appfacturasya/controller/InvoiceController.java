package com.appfacturasya.controller;

import com.appfacturasya.domain.model.Invoice;
import com.appfacturasya.domain.service.InvoiceService;
import com.appfacturasya.resource.InvoiceResource;
import com.appfacturasya.resource.SaveInvoiceResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "invoices", description = "Invoices API")
@RestController
@RequestMapping("/api")
public class InvoiceController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private InvoiceService invoiceService;

    @Operation(summary = "Get Invoices", description = "Get All Invoices by Pages", tags = { "invoices" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Invoices returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/invoices")
    public Page<InvoiceResource> getAllInvoices(Pageable pageable) {
        Page<Invoice> invoicePage = invoiceService.getAllInvoices(pageable);
        List<InvoiceResource> resources = invoicePage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }


    @Operation(summary = "Get Invoice by Id", description = "Get a Invoice by specifying Id", tags = { "invoices" })
    @GetMapping("/operations/invoices/{id}")
    public InvoiceResource getInvoiceById(
            @Parameter(description="Invoice Id")
            @PathVariable(name = "id") Long invoiceId) {
        return convertToResource(invoiceService.getInvoiceById(invoiceId));
    }

    //@Operation(security={ @SecurityRequirement(name="Authorization") })
    @PostMapping("/operations/{operationId}/invoices")
    public InvoiceResource createInvoices(@PathVariable(name = "operationId") Long operationId, @Valid @RequestBody SaveInvoiceResource resource)  {
        Invoice invoice = convertToEntity(resource);
        return convertToResource(invoiceService.createInvoice(invoice, operationId));
    }

    //@Operation(security={ @SecurityRequirement(name="Authorization") })
    @PutMapping("/operations/{operationId}/invoices/{id}")
    public InvoiceResource updateInvoice(@PathVariable(name = "id") Long invoiceId,@PathVariable(name = "operationId") Long operationId,
                                         @Valid @RequestBody SaveInvoiceResource resource) {
        Invoice invoice = convertToEntity(resource);
        return convertToResource(invoiceService.updateInvoice(invoiceId,operationId,invoice));
    }

    //@Operation(security={ @SecurityRequirement(name="Authorization") })
    @DeleteMapping("/operations/{operationId}/invoices/{id}")
    public ResponseEntity<?> deleteInvoice(@PathVariable(name = "id") Long invoiceId, @PathVariable(name = "operationId") Long operationId) {
        return invoiceService.deleteInvoice(invoiceId,operationId);
    }
    // Auto Mapper
    private Invoice convertToEntity(SaveInvoiceResource resource) {
        return mapper.map(resource, Invoice.class);
    }

    private InvoiceResource convertToResource(Invoice entity) {
        return mapper.map(entity, InvoiceResource.class);
    }
}
