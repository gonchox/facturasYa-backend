package com.appfacturasya.controller;

import com.appfacturasya.domain.model.RateTerm;
import com.appfacturasya.domain.service.RateTermService;
import com.appfacturasya.resource.RateTermResource;
import com.appfacturasya.resource.SaveRateTermResource;
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

@Tag(name = "rateTerms", description = "RateTerms API")
@RestController
@RequestMapping("/api")
public class RateTermController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RateTermService rateTermService;

    @Operation(summary = "Get RateTerms", description = "Get All RateTerms by Pages", tags = { "rateTerms" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All RateTerms returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/rateTerms")
    public Page<RateTermResource> getAllUsers(Pageable pageable) {
        Page<RateTerm> rateTermPage = rateTermService.getAllRateTerms(pageable);
        List<RateTermResource> resources = rateTermPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }


    @Operation(summary = "Get RateTerm by Id", description = "Get a RateTerm by specifying Id", tags = { "rateTerms" })
    @GetMapping("/rateTerms/{id}")
    public RateTermResource getRateTermById(
            @Parameter(description="RateTerm Id")
            @PathVariable(name = "id") Long rateTermId) {
        return convertToResource(rateTermService.getRateTermById(rateTermId));
    }

    //@Operation(security={ @SecurityRequirement(name="Authorization") })
    @PostMapping("/rateTerms")
    public RateTermResource createRateTerm(@Valid @RequestBody SaveRateTermResource resource)  {
        RateTerm rateTerm = convertToEntity(resource);
        return convertToResource(rateTermService.createRateTerm(rateTerm));
    }

    //@Operation(security={ @SecurityRequirement(name="Authorization") })
    @PutMapping("/rateTerms/{id}")
    public RateTermResource updateRateTerm(@PathVariable(name = "id") Long rateTermId, @Valid @RequestBody SaveRateTermResource resource) {
        RateTerm rateTerm = convertToEntity(resource);
        return convertToResource(rateTermService.updateRateTerm(rateTermId, rateTerm));
    }

    //@Operation(security={ @SecurityRequirement(name="Authorization") })
    @DeleteMapping("/rateTerms/{id}")
    public ResponseEntity<?> deleteRateTerm(@PathVariable(name = "id") Long rateTermId) {
        return rateTermService.deleteRateTerm(rateTermId);
    }
    // Auto Mapper
    private RateTerm convertToEntity(SaveRateTermResource resource) {
        return mapper.map(resource, RateTerm.class);
    }

    private RateTermResource convertToResource(RateTerm entity) {
        return mapper.map(entity, RateTermResource.class);
    }
}
