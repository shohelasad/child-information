package com.bd.childinfo.security;

import com.bd.childinfo.domain.User;
import com.bd.childinfo.service.UserService;
import com.bd.childinfo.utils.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("ChildInfoAuthenticationProvider")
public class ChildInfoAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChildInfoAuthenticationProvider.class);

    @Autowired
    private UserService userService;

    @Autowired
    private MessageDigestPasswordEncoder messageDigestPasswordEncoder;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        LOGGER.info("additionalAuthenticationChecks, userDetails={}", userDetails == null ? "null" : userDetails.toString());
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        LOGGER.info("retrieveUser, for username={}", username);

        if (StringUtils.isEmpty(username)) {
            setHideUserNotFoundExceptions(false);
            throw new UsernameNotFoundException("Enter your email address.");
        }

        User targetUser = userService.findByEmail(username);

        String givenPassword = (String) authentication.getCredentials();

        if (targetUser == null ||
                !messageDigestPasswordEncoder.encodePassword(givenPassword, targetUser.getSalt()).equals(targetUser.getHashedPassword())) {
            throw new BadCredentialsException("Incorrect username or password.");
        }

        return targetUser;
    }
}