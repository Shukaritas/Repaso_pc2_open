package com.tesla.energy.platform.u202313403.crm.domain.model.aggregates;

import com.tesla.energy.platform.u202313403.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.tesla.energy.platform.u202313403.crm.domain.model.valueobjects.BatteryLevel;
import com.tesla.energy.platform.u202313403.crm.domain.model.valueobjects.ChargeRate;
import com.tesla.energy.platform.u202313403.crm.domain.model.valueobjects.InstallationAddress;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Time;

@NoArgsConstructor
@Getter
public class CustomConfig extends AuditableAbstractAggregateRoot<CustomConfig> {
    private Long productId;
    private String teslaCustomerId;
    private String configName;
    private int preferredBatteryLevel;
    private double preferredChargeRate;
    private Time scheduledTime;
    private InstallationAddress installationAddress;

    public CustomConfig(Long productId, String teslaCustomerId, String configName, BatteryLevel batteryLevel,
                        ChargeRate chargeRate, Time scheduledTime, InstallationAddress installationAddress) {
        if (productId == null || productId <= 0) throw new IllegalArgumentException("productId must be positive");
        if (teslaCustomerId == null || teslaCustomerId.isBlank()) throw new IllegalArgumentException("teslaCustomerId cannot be blank");
        if (teslaCustomerId.length() > 50) throw new IllegalArgumentException("teslaCustomerId max length is 50");
        if (configName == null || configName.isBlank()) throw new IllegalArgumentException("configName cannot be blank");
        if (configName.length() > 100) throw new IllegalArgumentException("configName max length is 100");
        this.productId = productId;
        this.teslaCustomerId = teslaCustomerId;
        this.configName = configName;
        this.preferredBatteryLevel = batteryLevel.getValue();
        this.preferredChargeRate = chargeRate.getValue();
        this.scheduledTime = scheduledTime;
        this.installationAddress = installationAddress;
    }

    public void syncId(Long id) {
        // Usado por assembler para reflejar id generado si se desea
        try {
            var field = AuditableAbstractAggregateRoot.class.getDeclaredField("id");
            field.setAccessible(true);
            field.set(this, id);
        } catch (Exception ignored) { }
    }
}
