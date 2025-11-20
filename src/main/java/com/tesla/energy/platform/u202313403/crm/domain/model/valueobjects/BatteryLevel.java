package com.tesla.energy.platform.u202313403.crm.domain.model.valueobjects;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Value Object representing a preferred battery level percentage.
 */
@Getter
@EqualsAndHashCode
@ToString
public final class BatteryLevel {
    private final int value;
    private BatteryLevel(int value) {
        if (value < 20 || value > 100) {
            throw new IllegalArgumentException("BatteryLevel must be between 20 and 100 inclusive");
        }
        this.value = value;
    }
    public static BatteryLevel of(int value) { return new BatteryLevel(value); }
}

