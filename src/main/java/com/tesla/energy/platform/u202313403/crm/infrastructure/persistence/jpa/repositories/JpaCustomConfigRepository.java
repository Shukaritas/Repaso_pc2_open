package com.tesla.energy.platform.u202313403.crm.infrastructure.persistence.jpa.repositories;

import com.tesla.energy.platform.u202313403.crm.infrastructure.persistence.jpa.entities.CustomConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCustomConfigRepository extends JpaRepository<CustomConfigEntity, Long> {
    boolean existsByProductIdAndTeslaCustomerIdAndConfigName(Long productId, String teslaCustomerId, String configName);
}

