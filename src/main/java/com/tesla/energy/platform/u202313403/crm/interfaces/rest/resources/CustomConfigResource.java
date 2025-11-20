package com.tesla.energy.platform.u202313403.crm.interfaces.rest.resources;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(name = "CustomConfigResource")
public class CustomConfigResource {
    private Long id;
    private Long productId;
    private String teslaCustomerId;
    private String configName;
    private int preferredBatteryLevel;
    private double preferredChargeRate;
    private String scheduledTime;
    private String street;
    private String city;
    private String postalCode;
    private String country;
}

