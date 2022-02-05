package com.bd.childinfo.controller.api;

import com.bd.childinfo.domain.Profile;
import com.bd.childinfo.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Set;


@RestController
@RequestMapping("api/v1/user")
public class UserApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserApiController.class);

    @Autowired
    private UserService userService;

   
    @RequestMapping(value = "/{profile}/{profileId}/delete", method = RequestMethod.DELETE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Set<Profile> deleteProfile(@PathVariable String profile, @PathVariable Long profileId) {

        return userService.deleteProfile(profile, profileId);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/is-organization", method = RequestMethod.GET)
    public boolean isLoggedInUserIsOrganization() {

        return userService.isCurrentLoggedInUserIsOrganization();
    }
}
