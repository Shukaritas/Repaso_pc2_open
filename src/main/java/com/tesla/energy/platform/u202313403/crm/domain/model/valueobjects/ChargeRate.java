package com.tesla.energy.platform.u202313403.crm.domain.model.valueobjects;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Value Object representing a preferred charge rate in kW.
 */
@Getter
@EqualsAndHashCode
@ToString
public final class ChargeRate {
    private final double value;
    private ChargeRate(double value) {
        if (value < 0.5 || value > 2.0) {
            throw new IllegalArgumentException("ChargeRate must be between 0.5 and 2.0 inclusive");
        }
        this.value = value;
    }
    public static ChargeRate of(double value) { return new ChargeRate(value); }
}

