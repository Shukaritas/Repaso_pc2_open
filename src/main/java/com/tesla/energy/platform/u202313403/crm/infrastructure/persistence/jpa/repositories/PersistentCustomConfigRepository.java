package com.tesla.energy.platform.u202313403.crm.infrastructure.persistence.jpa.repositories;

import com.tesla.energy.platform.u202313403.crm.domain.model.aggregates.CustomConfig;
import com.tesla.energy.platform.u202313403.crm.domain.model.repositories.CustomConfigRepository;
import com.tesla.energy.platform.u202313403.crm.domain.model.valueobjects.BatteryLevel;
import com.tesla.energy.platform.u202313403.crm.domain.model.valueobjects.ChargeRate;
import com.tesla.energy.platform.u202313403.crm.infrastructure.persistence.jpa.entities.CustomConfigEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PersistentCustomConfigRepository implements CustomConfigRepository {

    private final JpaCustomConfigRepository jpaRepository;

    public PersistentCustomConfigRepository(JpaCustomConfigRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public boolean existsByProductIdAndTeslaCustomerIdAndConfigName(Long productId, String teslaCustomerId, String configName) {
        return jpaRepository.existsByProductIdAndTeslaCustomerIdAndConfigName(productId, teslaCustomerId, configName);
    }

    @Override
    public CustomConfig save(CustomConfig aggregate) {
        CustomConfigEntity entity = new CustomConfigEntity();
        entity.setProductId(aggregate.getProductId());
        entity.setTeslaCustomerId(aggregate.getTeslaCustomerId());
        entity.setConfigName(aggregate.getConfigName());
        entity.setPreferredBatteryLevel(aggregate.getPreferredBatteryLevel());
        entity.setPreferredChargeRate(aggregate.getPreferredChargeRate());
        entity.setScheduledTime(aggregate.getScheduledTime());
        entity.setInstallationAddress(aggregate.getInstallationAddress());
        CustomConfigEntity saved = jpaRepository.save(entity);
        aggregate.syncId(saved.getId());
        return aggregate;
    }

    @Override
    public Optional<CustomConfig> findById(Long id) {
        return jpaRepository.findById(id).map(e -> {
            CustomConfig aggregate = new CustomConfig(
                    e.getProductId(),
                    e.getTeslaCustomerId(),
                    e.getConfigName(),
                    BatteryLevel.of(e.getPreferredBatteryLevel()),
                    ChargeRate.of(e.getPreferredChargeRate()),
                    e.getScheduledTime(),
                    e.getInstallationAddress()
            );
            aggregate.syncId(e.getId());
            return aggregate;
        });
    }
}

