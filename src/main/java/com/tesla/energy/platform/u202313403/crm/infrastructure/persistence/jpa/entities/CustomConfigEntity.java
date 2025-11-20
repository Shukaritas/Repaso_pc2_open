package com.tesla.energy.platform.u202313403.crm.infrastructure.persistence.jpa.entities;

import com.tesla.energy.platform.u202313403.shared.domain.model.entities.AuditableModel;
import com.tesla.energy.platform.u202313403.crm.domain.model.valueobjects.InstallationAddress;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

@Entity
@Table(name = "custom_configs")
@Getter
@Setter
@NoArgsConstructor
public class CustomConfigEntity extends AuditableModel {
    @Column(nullable = false)
    private Long productId;
    @Column(nullable = false, length = 50)
    private String teslaCustomerId;
    @Column(nullable = false, length = 100)
    private String configName;
    @Column(nullable = false)
    private int preferredBatteryLevel;
    @Column(nullable = false)
    private double preferredChargeRate;
    @Column(nullable = true)
    private Time scheduledTime;

    @Embedded
    private InstallationAddress installationAddress;
}

