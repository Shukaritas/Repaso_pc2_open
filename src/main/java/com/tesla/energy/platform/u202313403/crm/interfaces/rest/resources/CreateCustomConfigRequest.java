package com.tesla.energy.platform.u202313403.crm.interfaces.rest.resources;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Schema(name = "CreateCustomConfigRequest")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomConfigRequest {
    @Schema(example = "CUST-12345", maxLength = 50)
    @NotBlank
    @Size(max = 50)
    private String teslaCustomerId;

    @Schema(example = "NightCharge", maxLength = 100)
    @NotBlank
    @Size(max = 100)
    private String configName;

    @Schema(example = "50")
    @Min(20) @Max(100)
    private int preferredBatteryLevel;

    @Schema(example = "1.2")
    @DecimalMin(value = "0.5") @DecimalMax(value = "2.0")
    private double preferredChargeRate;

    @Schema(example = "22:00:00")
    @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$", message = "scheduledTime must be HH:mm:ss")
    private String scheduledTime; // parsed later

    @NotBlank private String street;
    @NotBlank private String city;
    @NotBlank private String postalCode;
    @NotBlank private String country;
}

