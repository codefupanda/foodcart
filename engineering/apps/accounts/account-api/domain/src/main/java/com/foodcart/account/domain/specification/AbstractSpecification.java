package com.foodcart.account.domain.specification;

import com.foodcart.account.domain.specification.exception.SpecificationException;

public abstract class AbstractSpecification<T> implements Specification<T> {


    @Override
    public abstract boolean isSatisfiedBy(T t);

    @Override
    public void assertSatisfiedBy(T t) throws SpecificationException {
        if(!isSatisfiedBy(t)) {
            throw new SpecificationException("Invalid data");
        }
    }
}
