package com.bd.childinfo.utils;

import com.bd.childinfo.exceptions.ResourceNotFoundException;

/**
 * @uathor Bazlur Rahman Rokon
 * @since 5/12/15.
 */
public class RestPreconditions {
    public static <T> T checkFound(final T resource) {

        if (resource == null) {
            throw new ResourceNotFoundException();
        }

        return resource;
    }
}
