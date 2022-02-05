package com.bd.childinfo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bd.childinfo.domain.Organization;

import java.util.Optional;


public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    Optional<Organization> findByName(String name);

    Optional<Organization> findBySlug(String slug);

    Optional<Organization> findOneById(Long id);

    Page<Organization> findAllByDeletedFalseOrderByNameAsc(Pageable pageable);
}
