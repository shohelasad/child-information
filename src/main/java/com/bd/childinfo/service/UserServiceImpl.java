package com.bd.childinfo.service;

import com.bd.childinfo.domain.PasswordResetToken;
import com.bd.childinfo.domain.Profile;
import com.bd.childinfo.domain.Role;
import com.bd.childinfo.domain.User;
import com.bd.childinfo.dto.UserForm;
import com.bd.childinfo.exceptions.ResourceNotFoundException;
import com.bd.childinfo.exceptions.UserNotFoundException;
import com.bd.childinfo.repository.PasswordResetTokenRepository;
import com.bd.childinfo.repository.ProfileRepository;
import com.bd.childinfo.repository.UserRepository;
import com.bd.childinfo.utils.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private MessageDigestPasswordEncoder messageDigestPasswordEncoder;

    @PersistenceContext
    private EntityManager em;

    @Override
    public User findByEmail(String email) {

        return userRepository.findByEmail(email);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {

        return userRepository.findAll(pageable);
    }

    

    @Override
    public Optional<User> findById(Long id) {

        return userRepository.findOneByIdAndEnabledTrue(id);
    }

    @Override
    public User updateUser(UserForm userForm) {

        return findById(userForm.getId())
                .map(user -> {
                    user.setFirstName(userForm.getFirstName());
                    user.setLastName(userForm.getLastName());
                    return userRepository.save(user);
                }).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void remove(Long id) {
        userRepository.delete(id);
    }

    @Override
    public Optional<User> findCurrentLoggedInUser() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LOGGER.info("findCurrentLoggedInUser() -> principal: {}", principal);

        return userRepository.findOneByIdAndEnabledTrueAndLockedFalse(principal.getId());
    }

    @Override
    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return !(authentication == null || authentication instanceof AnonymousAuthenticationToken);
    }

    @Override
    public Set<Profile> deleteProfile(String profile, Long profileId) {
        profileRepository.delete(profileId);

        return findCurrentLoggedInUser()
                .map(loggedInUser -> profileRepository.findAllByUserAndProfileDataType(loggedInUser, profile))
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public boolean isCurrentLoggedInUserIsOrganization() {

        return findCurrentLoggedInUser()
                .map(loggedInUser -> loggedInUser.getUserRoles()
                        .stream()
                        .allMatch(userRole -> userRole.getRole().equals(Role.ROLE_ADMIN)))
                .orElse(Boolean.FALSE);
    }

    @Override
    public void changePassword(String newPassword) {
        findCurrentLoggedInUser().ifPresent(user -> {
            user.setSalt(StringUtils.generateRandomString(16));
            user.setHashedPassword(messageDigestPasswordEncoder.encodePassword(newPassword, user.getSalt()));
            userRepository.save(user);
        });
    }
    
    @Override
    public void changeUserPassword(User user, String newPassword) {
        user.setSalt(StringUtils.generateRandomString(16));
        user.setHashedPassword(messageDigestPasswordEncoder.encodePassword(newPassword, user.getSalt()));
        userRepository.save(user);
    }


    @Override
    public void lock(Long id) {
        findById(id)
                .map(user -> {
                    user.setLocked(true);

                    return userRepository.save(user);
                }).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void unlock(Long id) {
        findById(id)
                .map(user -> {
                    user.setLocked(false);

                    return userRepository.save(user);
                }).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public boolean isCurrentUserIsOrganizationUser() {

        return findCurrentLoggedInUser().map(currentLoggedInUser -> currentLoggedInUser.getUserRoles()
                .stream()
                .anyMatch(userRole -> (userRole.getRole() == Role.ROLE_ADMIN)
                        || (userRole.getRole() == Role.ROLE_APPLICATION))).orElse(false);
    }

    @Override
    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken passwordResetToken = new PasswordResetToken(token, user);
        passwordResetTokenRepository.save(passwordResetToken);
    }
    
    @Override
    public PasswordResetToken getPasswordResetToken(final String token) {
        return passwordResetTokenRepository.findByToken(token);
    }

    @Override
    public User getUserByPasswordResetToken(String token) {
        return passwordResetTokenRepository.findByToken(token).getUser();
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found by " + username);
        }

        return user;
	}

}
