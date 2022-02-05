package com.bd.childinfo.controller;


import com.bd.childinfo.domain.Registration;
import com.bd.childinfo.service.RegistrationService;
import com.bd.childinfo.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;



@Controller
public class RegistrationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/childatrisk", method = RequestMethod.GET)
    public String addChildAtRisk(Model uiModel) {

        return "childAtRisk/create";
    }
    
    @RequestMapping(value = "/childatrisk", method = RequestMethod.POST)
    public String saveChildAtRisk(Model uiModel) {

        return "redirect:/childatrisk";
    }
    
    @RequestMapping(value = "/childatrisk/list", method = RequestMethod.GET)
    public String showChildAtRisk(Model uiModel) {

        return "childAtRisk/list";
    }
    
   
}
