package com.tesla.energy.platform.u202313403.crm.domain.model.valueobjects;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Locale;
import java.util.regex.Pattern;
import jakarta.persistence.Embeddable;

/**
 * Value Object representing an installation address.
 */
@Getter
@EqualsAndHashCode
@ToString
@Embeddable
public final class InstallationAddress {
    private static final Pattern USA_POSTAL_PATTERN = Pattern.compile("^[0-9]{5}$");
    private static final Pattern CANADA_POSTAL_PATTERN = Pattern.compile("^[A-Za-z0-9]{6}$");

    private final String street;
    private final String city;
    private final String postalCode;
    private final String country;

    private InstallationAddress(String street, String city, String postalCode, String country) {
        if (street == null || street.isBlank()) throw new IllegalArgumentException("Street cannot be blank");
        if (city == null || city.isBlank()) throw new IllegalArgumentException("City cannot be blank");
        if (postalCode == null || postalCode.isBlank()) throw new IllegalArgumentException("PostalCode cannot be blank");
        if (country == null || country.isBlank()) throw new IllegalArgumentException("Country cannot be blank");

        String normalizedCountry = country.toUpperCase(Locale.ROOT);
        if (normalizedCountry.equals("USA") || normalizedCountry.equals("EE.UU.")) {
            if (!USA_POSTAL_PATTERN.matcher(postalCode).matches()) {
                throw new IllegalArgumentException("PostalCode must be 5 numeric digits for USA");
            }
        } else if (normalizedCountry.equals("CANADA")) {
            if (!CANADA_POSTAL_PATTERN.matcher(postalCode).matches()) {
                throw new IllegalArgumentException("PostalCode must be 6 alphanumeric characters for Canada");
            }
        }
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }

    protected InstallationAddress() { // for JPA
        this.street = null;
        this.city = null;
        this.postalCode = null;
        this.country = null;
    }

    public static InstallationAddress of(String street, String city, String postalCode, String country) {
        return new InstallationAddress(street, city, postalCode, country);
    }
}
