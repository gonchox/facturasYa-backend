package com.appfacturasya.controller;

import com.appfacturasya.domain.model.FinalCost;
import com.appfacturasya.domain.model.InitialCost;
import com.appfacturasya.domain.service.FinalCostService;
import com.appfacturasya.domain.service.InitialCostService;
import com.appfacturasya.resource.FinalCostResource;
import com.appfacturasya.resource.InitialCostResource;
import com.appfacturasya.resource.SaveFinalCostResource;
import com.appfacturasya.resource.SaveInitialCostResource;
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

@Tag(name = "finalCosts", description = "FinalCosts API")
@RestController
@RequestMapping("/api")
public class FinalCostController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private FinalCostService finalCostService;

    @Operation(summary = "Get Final Costs", description = "Get All Final Costs by Pages", tags = { "finalCosts" },security={ @SecurityRequirement(name="Authorization") })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Final Costs returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("operations/finalCosts")
    public Page<FinalCostResource> getAllFinalCosts(Pageable pageable) {
        Page<FinalCost> finalCostPage = finalCostService.getAllFinalCosts(pageable);
        List<FinalCostResource> resources = finalCostPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }


    @Operation(summary = "Get Final Cost by Id", description = "Get a Final Cost by specifying Id", tags = { "finalCosts" },security={ @SecurityRequirement(name="Authorization") })
    @GetMapping("operations/finalCosts/{id}")
    public FinalCostResource getFinalCostById(
            @Parameter(description="FinalCost Id")
            @PathVariable(name = "id") Long finalCostId) {
        return convertToResource(finalCostService.getFinalCostById(finalCostId));
    }

    @Operation(security={ @SecurityRequirement(name="Authorization") })
    @PostMapping("/operations/{operationId}/finalCosts")
    public FinalCostResource createFinalCost(@PathVariable(name = "operationId") Long operationId, @Valid @RequestBody SaveFinalCostResource resource)  {
        FinalCost finalCost = convertToEntity(resource);
        return convertToResource(finalCostService.createFinalCost(finalCost, operationId));
    }

    @Operation(security={ @SecurityRequirement(name="Authorization") })
    @PutMapping("/operations/{operationId}/finalCosts/{id}")
    public FinalCostResource updateFinalCost(@PathVariable(name = "id") Long finalCostId,@PathVariable(name = "operationId") Long operationId,
                                             @Valid @RequestBody SaveFinalCostResource resource) {
        FinalCost finalCost = convertToEntity(resource);
        return convertToResource(finalCostService.updateFinalCost(finalCostId,operationId,finalCost));
    }

    @Operation(security={ @SecurityRequirement(name="Authorization") })
    @DeleteMapping("/operations/{operationId}/finalCosts/{id}")
    public ResponseEntity<?> deleteFinalCost(@PathVariable(name = "id") Long finalCostId, @PathVariable(name = "operationId") Long operationId) {
        return finalCostService.deleteFinalCost(finalCostId,operationId);
    }
    // Auto Mapper
    private FinalCost convertToEntity(SaveFinalCostResource resource) {
        return mapper.map(resource, FinalCost.class);
    }

    private FinalCostResource convertToResource(FinalCost entity) {
        return mapper.map(entity, FinalCostResource.class);
    }
}
