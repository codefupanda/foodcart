package com.foodcart.account.domain.specification;

import com.foodcart.account.domain.specification.exception.SpecificationException;

/**
 * Defines a business rule
 *
 * @param <T> the class the specification applies to
 */
public interface Specification<T> {

    boolean isSatisfiedBy(T t);

    void assertSatisfiedBy(T t) throws SpecificationException;
}
