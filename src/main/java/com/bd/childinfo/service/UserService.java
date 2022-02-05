package com.bd.childinfo.service;


import com.bd.childinfo.domain.PasswordResetToken;
import com.bd.childinfo.domain.Profile;
import com.bd.childinfo.domain.User;
import com.bd.childinfo.dto.UserForm;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;
import java.util.Set;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    Page<User> findAll(Pageable pageable);

    Optional<User> findById(Long id);

    User updateUser(UserForm user);

    void remove(Long id);

    Optional<User> findCurrentLoggedInUser();

    boolean isAuthenticated();

    Set<Profile> deleteProfile(String profile, Long profileId);

    boolean isCurrentLoggedInUserIsOrganization();

    void changePassword(String newPassword);

	void lock(Long id);
	
	void unlock(Long id);

    boolean isCurrentUserIsOrganizationUser();

	void createPasswordResetTokenForUser(User user, String token);

	PasswordResetToken getPasswordResetToken(String token);

	User getUserByPasswordResetToken(String token);

	void changeUserPassword(User user, String newPassword);

}
