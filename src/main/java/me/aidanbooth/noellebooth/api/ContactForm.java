package me.aidanbooth.noellebooth.api;

import me.aidanbooth.noellebooth.data.dtos.ContactDTO;
import me.aidanbooth.noellebooth.mail.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ContactForm {

    @Autowired
    private EmailSender emailSender;

    @PostMapping("/contact")
    public ModelAndView formSubmitted(ContactDTO dto) {
        emailSender.sendUserDetailsEmail(dto.getName(), dto.getEmail(), dto.getMessage());
        emailSender.sendConfirmationEmail(dto.getEmail(), dto.getMessage());

        return new ModelAndView("redirect:/contact?success");
    }
}
