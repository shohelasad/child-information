package com.bd.childinfo.controller;

import com.bd.childinfo.domain.User;
import com.bd.childinfo.dto.SignupForm;
import com.bd.childinfo.service.SignupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.bd.childinfo.utils.StringUtils.isEmpty;
import static com.bd.childinfo.utils.StringUtils.isNotEmpty;
import static com.bd.childinfo.utils.ValidationUtils.addFieldError;

import javax.validation.Valid;

/**
 * @uathor Bazlur Rahman Rokon
 * @since 5/4/15.
 */

@Controller
@RequestMapping(value = "/signup")
public class SignupController {

    protected static final String MODEL_NAME_SIGNUP_DTO = "signupForm";
    protected static final String ERROR_CODE_EMAIL_EXIST = "NotExist.user.email";
    protected static final String ERROR_CODE_ORGANIZATION_NAME_EXIST = "NotExist.organizationForm.name";
    protected static final String ERROR_CODE_ORGANIZATION_NAME_NOT_EMPTY = "NotEmpty.signupForm.organizationName";
    protected static final String ERROR_CODE_PHONE_NUMBER_NOT_EMPTY = "NotEmpty.signupForm.phoneNumber";

    @Autowired
    private SignupService signupService;

    @RequestMapping(method = RequestMethod.GET)
    public String showSignUpForm(SignupForm signupForm) {

        return "signup";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submitSignUpForm(@Valid SignupForm signupForm, BindingResult bindingResult) {

        validate(signupForm, bindingResult);

        if (bindingResult.hasErrors()) {

            return "signup";
        }

        signupService.createNewUser(signupForm);

        return "redirect:/login";
    }

    private void validate(SignupForm signupForm, BindingResult result) {

        if (isNotEmpty(signupForm.getEmail())) {
            User user = signupService.findByEmail(signupForm.getEmail());

            if (user != null) {
                addFieldError(MODEL_NAME_SIGNUP_DTO, SignupForm.FIELD_NAME_EMAIL, signupForm.getEmail(),
                        ERROR_CODE_EMAIL_EXIST, result);
            }
        }

        if (signupForm.getIsOrganization()) {

            if (isEmpty(signupForm.getOrganizationName())) {
                addFieldError(MODEL_NAME_SIGNUP_DTO, SignupForm.FIELD_NAME_ORGANIZATION_NAME,
                        signupForm.getOrganizationName(), ERROR_CODE_ORGANIZATION_NAME_NOT_EMPTY, result);
            }

            if (isNotEmpty(signupForm.getOrganizationName())) {
                signupService.findOrganizationByName(signupForm.getOrganizationName()).ifPresent(organization -> {
                    addFieldError(MODEL_NAME_SIGNUP_DTO, SignupForm.FIELD_NAME_ORGANIZATION_NAME,
                            signupForm.getOrganizationName(), ERROR_CODE_ORGANIZATION_NAME_EXIST, result);
                });
            }

            if (isEmpty(signupForm.getPhoneNumber())) {
                addFieldError(MODEL_NAME_SIGNUP_DTO, SignupForm.FIELD_NAME_PHONE_NUMBER,
                        signupForm.getPhoneNumber(), ERROR_CODE_PHONE_NUMBER_NOT_EMPTY, result);
            }
        }
    }
}
