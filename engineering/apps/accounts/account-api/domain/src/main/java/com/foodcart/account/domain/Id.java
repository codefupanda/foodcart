package com.foodcart.account.domain;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Id {
    private UUID id;

    private Id(UUID id) {
        this.id = id;
    }

    public static Id fromId(UUID id) {
        return new Id(id);
    }

    public static Id newId() {
        return new Id(UUID.randomUUID());
    }
}
