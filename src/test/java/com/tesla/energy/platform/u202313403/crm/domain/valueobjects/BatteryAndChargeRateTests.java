package com.tesla.energy.platform.u202313403.crm.domain.valueobjects;

import com.tesla.energy.platform.u202313403.crm.domain.model.valueobjects.BatteryLevel;
import com.tesla.energy.platform.u202313403.crm.domain.model.valueobjects.ChargeRate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BatteryAndChargeRateTests {

    @Test
    void batteryLevelValid() {
        BatteryLevel level = BatteryLevel.of(50);
        Assertions.assertEquals(50, level.getValue());
    }

    @Test
    void batteryLevelInvalidLow() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> BatteryLevel.of(10));
    }

    @Test
    void chargeRateValid() {
        ChargeRate rate = ChargeRate.of(1.5);
        Assertions.assertEquals(1.5, rate.getValue());
    }

    @Test
    void chargeRateInvalidHigh() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> ChargeRate.of(3.0));
    }
}

