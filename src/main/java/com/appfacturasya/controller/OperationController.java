package com.appfacturasya.controller;

import com.appfacturasya.domain.model.Operation;
import com.appfacturasya.domain.service.OperationService;
import com.appfacturasya.resource.OperationResource;
import com.appfacturasya.resource.SaveOperationResource;
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

@Tag(name = "operations", description = "Operations API")
@RestController
@RequestMapping("/api")
public class OperationController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private OperationService operationService;

    @io.swagger.v3.oas.annotations.Operation(security={ @SecurityRequirement(name="Authorization") })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Operations returned", content = @Content(mediaType = "application/json"))
    })

    @GetMapping("/users/{userId}/operations")
    public Page<OperationResource> getAllOperationsByUserId(@PathVariable(name = "userId") Long userId, Pageable pageable) {
        List<OperationResource> operations = operationService.getAllOperationsByUserId(userId,pageable)
                .getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        int count = operations.size();
        return new PageImpl<>(operations, pageable, count);
    }
    @io.swagger.v3.oas.annotations.Operation(security={ @SecurityRequirement(name="Authorization") })
    @GetMapping("/operations/{id}")
    public OperationResource getOperationById(
            @Parameter(description="Operation Id")
            @PathVariable(name = "id") Long operationId) {
        return convertToResource(operationService.getOperationById(operationId));
    }

   // @GetMapping("/users/{userId}/posts/{postId}")
    //public PostResource getPostByIdAndUserId(@PathVariable(name = "userId") Long userId,
     //@PathVariable(name = "postId") Long postId) {
     //   return convertToResource(postService.getPostByIdAndUserId(userId, postId));
   // }

    @io.swagger.v3.oas.annotations.Operation(security={ @SecurityRequirement(name="Authorization") })
    @PostMapping("/users/{userId}/rateTerms/{rateTermId}/operations")
    public OperationResource createOperation(@PathVariable(name = "userId") Long userId, @PathVariable(name = "rateTermId") Long rateTermId,
                                   @Valid @RequestBody SaveOperationResource resource) {
        return convertToResource(operationService.createOperation(convertToEntity(resource),userId,rateTermId));

    }
    @io.swagger.v3.oas.annotations.Operation(security={ @SecurityRequirement(name="Authorization") })
    @PutMapping("/users/{userId}/rateTerms/{rateTermId}/operations/{operationId}")
    public OperationResource updateOperation(@PathVariable(name = "operationId") Long operationId,
                                             @Valid @RequestBody SaveOperationResource resource) {
        return convertToResource(operationService.updateOperation(operationId, convertToEntity(resource)));
    }

    @io.swagger.v3.oas.annotations.Operation(security={ @SecurityRequirement(name="Authorization") })
    @DeleteMapping("/users/{userId}/rateTerms/{rateTermsId}/posts/{postId}")
    public ResponseEntity<?> deleteOperation(
                                        @PathVariable(name = "operationId") Long operationId) {
        return operationService.deleteOperation(operationId);
    }

    @io.swagger.v3.oas.annotations.Operation(security={ @SecurityRequirement(name="Authorization") })
    @GetMapping("/operations")
    public Page<OperationResource> getAllOperations(
            @Parameter(description="Pageable Parameter")
                    Pageable pageable) {
        Page<Operation> operationsPage = operationService.getAllOperations(pageable);
        List<OperationResource> resources = operationsPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());

        return new PageImpl<OperationResource>(resources,pageable , resources.size());
    }


    private Operation convertToEntity(SaveOperationResource resource) {
        return mapper.map(resource, Operation.class);
    }

    private OperationResource convertToResource(Operation entity) {
        return mapper.map(entity, OperationResource.class);
    }
}
