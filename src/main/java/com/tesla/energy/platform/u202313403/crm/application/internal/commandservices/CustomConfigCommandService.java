package com.tesla.energy.platform.u202313403.crm.application.internal.commandservices;

import com.tesla.energy.platform.u202313403.crm.domain.model.aggregates.CustomConfig;
import com.tesla.energy.platform.u202313403.crm.domain.model.valueobjects.BatteryLevel;
import com.tesla.energy.platform.u202313403.crm.domain.model.valueobjects.ChargeRate;
import com.tesla.energy.platform.u202313403.crm.domain.model.valueobjects.InstallationAddress;
import com.tesla.energy.platform.u202313403.crm.domain.model.repositories.CustomConfigRepository;
import com.tesla.energy.platform.u202313403.crm.domain.model.exceptions.DuplicateCustomConfigException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomConfigCommandService {

    private final CustomConfigRepository repository;

    public CustomConfigCommandService(CustomConfigRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public CustomConfig handle(CreateCustomConfigCommand command) {
        boolean exists = repository.existsByProductIdAndTeslaCustomerIdAndConfigName(
                command.getProductId(), command.getTeslaCustomerId(), command.getConfigName());
        if (exists) {
            throw new DuplicateCustomConfigException("CustomConfig already exists for productId=" + command.getProductId() + ", teslaCustomerId=" + command.getTeslaCustomerId() + ", configName=" + command.getConfigName());
        }
        CustomConfig aggregate = new CustomConfig(
                command.getProductId(),
                command.getTeslaCustomerId(),
                command.getConfigName(),
                BatteryLevel.of(command.getPreferredBatteryLevel()),
                ChargeRate.of(command.getPreferredChargeRate()),
                command.getScheduledTime(),
                InstallationAddress.of(command.getStreet(), command.getCity(), command.getPostalCode(), command.getCountry())
        );
        repository.save(aggregate);
        return aggregate;
    }
}
