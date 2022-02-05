package com.bd.childinfo.utils;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * @author Bazlur Rahman Rokon
 * @since 7/13/15.
 */
public class ServletUtils {
    public static String getContextURL(String appendPath) {

        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(appendPath)
                .build()
                .toUriString();
    }

}
