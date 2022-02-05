package com.bd.childinfo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bd.childinfo.service.UserService;

import javax.servlet.http.HttpServletRequest;


@Controller
public class LoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String signin(Model model, HttpServletRequest request) {

        if (userService.isAuthenticated()) {
            return "redirect:/";
        }

        return "login";
    }

    @RequestMapping(value = "/login-error/{errorType}", method = RequestMethod.GET)
    public String signinAuthenticationFailure(@PathVariable String errorType, Model model,
                                              HttpServletRequest request, RedirectAttributes redirectAttributes) {
        LOGGER.info("signinAuthenticationFailure, errorType={}", errorType);

        redirectAttributes.addFlashAttribute("errorType", errorType);
        redirectAttributes.addFlashAttribute("userEmail", request.getParameter("userEmail"));

        return "redirect:/login";
    }
}
