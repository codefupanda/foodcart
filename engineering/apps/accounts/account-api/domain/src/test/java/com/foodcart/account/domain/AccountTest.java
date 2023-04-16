package com.foodcart.account.domain;


import com.foodcart.account.domain.specification.exception.SpecificationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountTest {

    @Test
    public void testCreateAccount() {
        Account account = new Account();
        account.setId(Id.newId());
        account.setName("Test");

        Assertions.assertNotNull(account.getId().getId());
        Assertions.assertNotNull(account.getName());
    }

    @Test
    public void testAddPhoneNumberWithOnlyCountryCode() {
        Account account = new Account();
        account.setId(Id.newId());

        Assertions.assertThrows(SpecificationException.class, () -> account.addPhoneNumber(new PhoneNumber("+91", null)));
    }

    @Test
    public void testAddPhoneNumberWithOnlyPhoneNumber() {
        Account account = new Account();
        account.setId(Id.newId());

        Assertions.assertThrows(SpecificationException.class, () -> account.addPhoneNumber(new PhoneNumber("", "9876542135")));
    }

    @Test
    public void testAddPhoneNumberWithInvalidCountryCodeAndNumber() {
        Account account = new Account();
        account.setId(Id.newId());
        Assertions.assertThrows(SpecificationException.class, () -> account.addPhoneNumber(new PhoneNumber("1", "9876542135")));
    }

    @Test
    public void testAddPhoneNumberWithCountryCodeAndNumber() {
        Account account = new Account();
        account.setId(Id.newId());
        account.addPhoneNumber(new PhoneNumber("+91", "9876542135"));
        Assertions.assertEquals(account.getPhoneNumbers().size(), 1);
    }
}
