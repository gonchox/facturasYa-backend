package com.appfacturasya.controller;

import com.appfacturasya.domain.model.InitialCost;
import com.appfacturasya.domain.model.Invoice;
import com.appfacturasya.domain.service.InitialCostService;
import com.appfacturasya.domain.service.InvoiceService;
import com.appfacturasya.resource.InitialCostResource;
import com.appfacturasya.resource.InvoiceResource;
import com.appfacturasya.resource.SaveInitialCostResource;
import com.appfacturasya.resource.SaveInvoiceResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

@Tag(name = "initialCosts", description = "InitialCosts API")
@RestController
@RequestMapping("/api")
public class InitialCostController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private InitialCostService initialCostService;

    @Operation(summary = "Get InitialCosts", description = "Get All InitialCosts by Pages", tags = { "initialCosts" },security={ @SecurityRequirement(name="Authorization") })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All InitialCosts returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("operations/initialCosts")
    public Page<InitialCostResource> getAllInitialCosts(Pageable pageable) {
        Page<InitialCost> initialCostPage = initialCostService.getAllInitialCosts(pageable);
        List<InitialCostResource> resources = initialCostPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }


    @Operation(summary = "Get Initial Cost by Id", description = "Get a Initial Cost by specifying Id", tags = { "initialCosts" },security={ @SecurityRequirement(name="Authorization") })
    @GetMapping("operations/initialCosts/{id}")
    public InitialCostResource getInitialCostById(
            @Parameter(description="InitialCost Id")
            @PathVariable(name = "id") Long initialCostId) {
        return convertToResource(initialCostService.getInitialCostById(initialCostId));
    }

    @Operation(security={ @SecurityRequirement(name="Authorization") })
    @PostMapping("/operations/{operationId}/initialCosts")
    public InitialCostResource createInitialCost(@PathVariable(name = "operationId") Long operationId, @Valid @RequestBody SaveInitialCostResource resource)  {
        InitialCost initialCost = convertToEntity(resource);
        return convertToResource(initialCostService.createInitialCost(initialCost, operationId));
    }

    @Operation(security={ @SecurityRequirement(name="Authorization") })
    @PutMapping("/operations/{operationId}/initialCosts/{id}")
    public InitialCostResource updateInitialCost(@PathVariable(name = "id") Long initialCostId,
                                         @Valid @RequestBody SaveInitialCostResource resource) {
        InitialCost initialCost = convertToEntity(resource);
        return convertToResource(initialCostService.updateInitialCost(initialCostId,initialCost));
    }

    @Operation(security={ @SecurityRequirement(name="Authorization") })
    @DeleteMapping("/operations/{operationId}/initialCosts/{id}")
    public ResponseEntity<?> deleteInitialCost(@PathVariable(name = "id") Long initialCostId) {
        return initialCostService.deleteInitialCost(initialCostId);
    }
    // Auto Mapper
    private InitialCost convertToEntity(SaveInitialCostResource resource) {
        return mapper.map(resource, InitialCost.class);
    }

    private InitialCostResource convertToResource(InitialCost entity) {
        return mapper.map(entity, InitialCostResource.class);
    }
}
