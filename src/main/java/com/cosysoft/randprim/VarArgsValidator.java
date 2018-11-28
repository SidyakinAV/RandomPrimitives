package com.cosysoft.randprim;

import java.util.Collection;

/**
 * Validates var arg params.
 */
final class VarArgsValidator {
    private VarArgsValidator() {
    }

    static void validateVarArgNotEmpty(final Object[] params) throws IllegalArgumentException {
        if (params.length == 0) {
            VarArgsValidator.throwNotEmptyException();
        }
    }

    static void validateVarArgNotEmpty(final Collection params) throws IllegalArgumentException {
        if (params.isEmpty()) {
            VarArgsValidator.throwNotEmptyException();
        }
    }

    static void throwNotEmptyException() {
        throw new IllegalArgumentException("you must provide not empty collection of parameters");
    }
}
