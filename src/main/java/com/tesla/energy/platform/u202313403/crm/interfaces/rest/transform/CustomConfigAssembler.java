package com.tesla.energy.platform.u202313403.crm.interfaces.rest.transform;

import com.tesla.energy.platform.u202313403.crm.domain.model.aggregates.CustomConfig;
import com.tesla.energy.platform.u202313403.crm.interfaces.rest.resources.CustomConfigResource;

public class CustomConfigAssembler {
    public static CustomConfigResource toResource(CustomConfig aggregate) {
        return new CustomConfigResource(
                aggregate.getId(),
                aggregate.getProductId(),
                aggregate.getTeslaCustomerId(),
                aggregate.getConfigName(),
                aggregate.getPreferredBatteryLevel(),
                aggregate.getPreferredChargeRate(),
                aggregate.getScheduledTime() != null ? aggregate.getScheduledTime().toString() : null,
                aggregate.getInstallationAddress().getStreet(),
                aggregate.getInstallationAddress().getCity(),
                aggregate.getInstallationAddress().getPostalCode(),
                aggregate.getInstallationAddress().getCountry()
        );
    }
}

