package com.tesla.energy.platform.u202313403.crm.interfaces.rest;

import com.tesla.energy.platform.u202313403.crm.application.internal.commandservices.CreateCustomConfigCommand;
import com.tesla.energy.platform.u202313403.crm.application.internal.commandservices.CustomConfigCommandService;
import com.tesla.energy.platform.u202313403.crm.domain.model.aggregates.CustomConfig;
import com.tesla.energy.platform.u202313403.crm.interfaces.rest.resources.CreateCustomConfigRequest;
import com.tesla.energy.platform.u202313403.crm.interfaces.rest.resources.CustomConfigResource;
import com.tesla.energy.platform.u202313403.crm.interfaces.rest.transform.CustomConfigAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;

@RestController
@RequestMapping(value = "/api/v1/products/{productId}/custom-configs")
public class CustomConfigController {

    private final CustomConfigCommandService commandService;

    public CustomConfigController(CustomConfigCommandService commandService) {
        this.commandService = commandService;
    }

    @Operation(summary = "Create a Custom Configuration", responses = {
            @ApiResponse(responseCode = "201", description = "Created", content = @Content(schema = @Schema(implementation = CustomConfigResource.class))),
            @ApiResponse(responseCode = "400", description = "Validation error", content = @Content),
            @ApiResponse(responseCode = "409", description = "Duplicate configuration", content = @Content)
    })
    @PostMapping
    public ResponseEntity<CustomConfigResource> create(@PathVariable Long productId, @Valid @RequestBody CreateCustomConfigRequest request) {
        Time scheduled = request.getScheduledTime() != null ? Time.valueOf(request.getScheduledTime()) : null;
        CreateCustomConfigCommand command = new CreateCustomConfigCommand(
                productId,
                request.getTeslaCustomerId(),
                request.getConfigName(),
                request.getPreferredBatteryLevel(),
                request.getPreferredChargeRate(),
                scheduled,
                request.getStreet(),
                request.getCity(),
                request.getPostalCode(),
                request.getCountry()
        );
        CustomConfig aggregate = commandService.handle(command);
        CustomConfigResource resource = CustomConfigAssembler.toResource(aggregate);
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
    }
}

