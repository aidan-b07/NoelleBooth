package me.aidanbooth.noellebooth.api;

import jakarta.servlet.Filter;
import jakarta.validation.Valid;
import me.aidanbooth.noellebooth.data.dtos.ContactDTO;
import me.aidanbooth.noellebooth.mail.EmailSender;
import org.apache.catalina.filters.RateLimitFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.regex.Pattern;

@RestController
public class ContactForm {

    @Autowired
    private EmailSender emailSender;

    /*
    @PostMapping("/contact")
    public ModelAndView formSubmitted(ContactDTO dto) {
        emailSender.sendUserDetailsEmail(dto.getName(), dto.getEmail(), dto.getMessage());
        emailSender.sendConfirmationEmail(dto.getEmail(), dto.getMessage());

        return new ModelAndView("redirect:/contact?success");
    }
     */

    private static final Pattern SAFE_TEXT = Pattern.compile("^[a-zA-Z0-9 .,'-]+$");

    @PostMapping("/contact")
    public ModelAndView submitContact(@Valid ContactDTO dto, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("redirect:/contact?fail");
        }

        if (dto.hasHeaderInjection()) {
            return new ModelAndView("redirect:/contact?fail");
        }

        dto.sanitize();

        emailSender.sendUserDetailsEmail(dto.getName(), dto.getEmail(), dto.getMessage());
        emailSender.sendConfirmationEmail(dto.getEmail(), dto.getMessage());

        return new ModelAndView("redirect:/contact?success");
    }

    @Bean
    public FilterRegistrationBean<Filter> rateLimitingFilter() {
        return new FilterRegistrationBean<>(new RateLimitFilter());
    }
}
