package com.bd.childinfo.config;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.Optional;

/**
 * @author Bazlur Rahman Rokon
 * @since 6/18/15.
 */
public interface NonDeletableRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
    void delete(ID id);

    Optional<T> findOneById(ID id);
}
