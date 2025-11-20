package com.tesla.energy.platform.u202313403.crm.domain.model.repositories;

import com.tesla.energy.platform.u202313403.crm.domain.model.aggregates.CustomConfig;

import java.util.Optional;

public interface CustomConfigRepository {
    boolean existsByProductIdAndTeslaCustomerIdAndConfigName(Long productId, String teslaCustomerId, String configName);
    CustomConfig save(CustomConfig aggregate);
    Optional<CustomConfig> findById(Long id);
}

