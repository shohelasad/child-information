package com.bd.childinfo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.context.request.RequestContextHolder;

import com.bd.childinfo.domain.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class SecurityAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {

        LOGGER.trace("onAuthenticationSuccess() login successful with login={}, sid={}", authentication.getName(), RequestContextHolder.currentRequestAttributes().getSessionId());

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains(Role.ROLE_APPLICATION.name())) {
            LOGGER.debug("ROLE_APPLICATION found, redirecting to application page");
            // here we will redirect user to respective page
            httpServletResponse.sendRedirect("/admin/organization/list");
        } else if (roles.contains(Role.ROLE_ADMIN.name())) {
            LOGGER.debug("ROLE_ADMIN found, redirecting to organization user page");
            // here we will redirect user to respective page
            httpServletResponse.sendRedirect("/program/list");
        } else if (roles.contains(Role.ROLE_USER.name())) {
            LOGGER.debug("ROLE_USER found, redirecting to user page");
            // here we will redirect user to respective page
            httpServletResponse.sendRedirect("/");
        }
    }
}
