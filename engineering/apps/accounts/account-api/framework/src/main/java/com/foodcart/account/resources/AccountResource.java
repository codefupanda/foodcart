package com.foodcart.account.resources;

import com.foodcart.account.application.usecase.AccountService;
import com.foodcart.account.domain.Id;
import com.foodcart.account.vo.Account;
import com.foodcart.account.vo.Address;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.UUID;

@Path("/account")
public class AccountResource {

    private final AccountService accountService;

    @Inject
    public AccountResource(AccountService accountService) {
        this.accountService = accountService;
    }

    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }

    @GET
    @Path("/{accountId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Account get(@PathParam("accountId") String accountId) {
        Optional<com.foodcart.account.domain.Account> account = accountService.getAccount(Id.fromId(UUID.fromString(accountId)));
        if (account.isEmpty()) {
            throw new NotFoundException("Account not found");
        }
        return Account.fromDomain(account.get());
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response post(Account account) throws URISyntaxException {
        try {
            com.foodcart.account.domain.Account savedAccount = accountService.save(account.toDomain());
            return Response.created(new URI("/" + savedAccount.getId().getId().toString())).build();
        } catch (com.foodcart.account.domain.specification.exception.SpecificationException specificationException) {
            throw new BadRequestException("Invalid account data");
        }
    }

    @GET
    @Path("/{accountId}/addresses/{addressId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Address get(@PathParam("accountId") String accountId, @PathParam("addressId") String addressId) {
        Optional<com.foodcart.account.domain.Address> address =
                accountService.getAddress(Id.fromId(UUID.fromString(accountId)), Id.fromId(UUID.fromString(addressId)));
        if (address.isEmpty()) {
            throw new NotFoundException("Account not found");
        }
        return Address.fromDomain(address.get());
    }

    @POST
    @Path("/{accountId}/addresses")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postAddress(@PathParam("accountId") String accountId, Address address) throws URISyntaxException {
        accountService.saveAddress(Id.fromId(UUID.fromString(accountId)), address.toDomain());
        return Response.created(new URI("/" + address.id())).build();
    }
}