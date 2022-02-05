package com.bd.childinfo.service;

import com.bd.childinfo.domain.Organization;
import com.bd.childinfo.domain.User;
import com.bd.childinfo.dto.SignupForm;

import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @uathor Bazlur Rahman Rokon
 * @since 5/4/15.
 */

@Component
public interface SignupService {
    User createNewUser(SignupForm signupForm);

    User findByEmail(String email);

    Optional<Organization> findOrganizationByName(String organizationName);
}
