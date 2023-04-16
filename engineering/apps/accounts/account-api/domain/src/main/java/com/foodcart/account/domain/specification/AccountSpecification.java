package com.foodcart.account.domain.specification;

import com.foodcart.account.domain.Account;
import com.foodcart.account.domain.Address;
import com.foodcart.account.domain.PhoneNumber;

import java.util.List;

public class AccountSpecification extends AbstractSpecification<Account> {

    @Override
    public boolean isSatisfiedBy(Account account) {
        return account.getId() != null && account.getName() != null &&
                atLeastOnePhoneNumber(account.getPhoneNumbers()) && allValidPhoneNumbers(account.getPhoneNumbers()) &&
                allValidAddresses(account.getAddresses());
    }

    // address can be null or empty
    private boolean allValidAddresses(List<Address> addresses) {
        boolean valid = true;
        if (addresses != null && addresses.size() > 0) {
            valid = addresses.stream().allMatch(Address::isValid);
        }
        return valid;
    }

    // we will have at-least one phone number
    private boolean allValidPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        return phoneNumbers.stream().allMatch(PhoneNumber::isValid);
    }

    private boolean atLeastOnePhoneNumber(List<PhoneNumber> phoneNumbers) {
        return phoneNumbers != null && phoneNumbers.size() >= 1;
    }
}
