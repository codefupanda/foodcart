package com.foodcart.account.domain.specification;

import com.foodcart.account.domain.Address;

import java.math.BigDecimal;
import java.util.Set;

public class AddressSpecification extends AbstractSpecification<Address> {

    private static final int ADDRESS_LINE_1_MIN_LENGTH = 10;
    private static final Set<String> STATES = Set.of("KAR", "ANDRA", "TM", "MAHA");
    private static final int CITY_MIN_LENGTH = 3;
    private static final Set<String> SUPPORTED_COUNTRIES = Set.of("INDIA");

    @Override
    public boolean isSatisfiedBy(Address address) {
        return address != null &&
                address.getAddress1() != null && address.getAddress1().length() > ADDRESS_LINE_1_MIN_LENGTH &&
                address.getCity() != null && address.getCity().length() > CITY_MIN_LENGTH &&
                address.getState() != null && STATES.contains(address.getState()) &&
                address.getCountry() != null && SUPPORTED_COUNTRIES.contains(address.getCountry()) &&
                address.getLocation() != null &&
                address.getLocation().getLatitude().abs().compareTo(new BigDecimal(90)) < 0 &&
                address.getLocation().getLongitude().abs().compareTo(new BigDecimal(180)) <= 0;
    }
}
