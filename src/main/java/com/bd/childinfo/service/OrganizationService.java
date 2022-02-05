package com.bd.childinfo.service;

import com.bd.childinfo.domain.*;
import com.bd.childinfo.dto.OrganizationForm;
import com.bd.childinfo.dto.SignupForm;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.Set;


public interface OrganizationService {

    Optional<Organization> findBySlug(String slug);

    Optional<Organization> findByName(String name);

    boolean isNameExist(String name, Organization organization);

    void remove(long id);

    Organization saveOrganization(OrganizationForm organization);

    Page<Organization> findAll(Pageable pageable, String q);

    void addNewUserToOrganization(SignupForm signupForm);

    Set<User> findAllUsers();

	Page<Organization> findAll(Pageable pageable);

	void delete(Long id);

	Set<User> findAllUsers(String slug);

	void addNewUserToOrganizationBySlug(SignupForm signupForm);

    void indexOrganization();
}
