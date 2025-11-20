package com.tesla.energy.platform.u202313403.crm.application.internal.commandservices;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Time;

@Getter
@AllArgsConstructor
public class CreateCustomConfigCommand {
    private final Long productId;
    private final String teslaCustomerId;
    private final String configName;
    private final int preferredBatteryLevel;
    private final double preferredChargeRate;
    private final Time scheduledTime;
    private final String street;
    private final String city;
    private final String postalCode;
    private final String country;
}

