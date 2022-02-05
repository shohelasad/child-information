package com.bd.childinfo.utils;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.bd.childinfo.domain.BaseEntity;

/**
 * @uathor Bazlur Rahman Rokon
 * @since 5/5/15.
 */
public class CollectionUtils {
    public static Set<? extends BaseEntity> sortById(Set<? extends BaseEntity> items) {

        return items.stream().sorted(BaseEntity::compareTo).collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
