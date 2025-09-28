package me.aidanbooth.noellebooth.mail;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {
    @Autowired
    private JavaMailSender sender;

    public void sendMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("no-reply@noellebooth.co.uk");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        sender.send(message);
    }

    public void sendConfirmationEmail(String to, String msg) {
        String subject = "Noelle Booth Psychotherapy Inquiry";
        String message = "Thank you for inquiring with Noelle Booth Psychotherapy\n" +
                "\n" +
                "We have received your contact information and will be in touch soon\n" +
                "\n" +
                "Your message:\n" +
                msg +
                "\n\n" +
                "Thanks very much\n" +
                "Noelle Booth";

        sendMessage(to, subject, message);
    }

    public void sendUserDetailsEmail(String name, String email, String message) {
        String subject = "New Client Inquiry";
        String text = "Hello,\n" +
                "\n" +
                "A contact form has been filled out by " + name + " (" + email + ") regarding therapy. The message provided is as follows:\n" +
                message + "\n" +
                "\n" +
                "This is a private email shared only with you\n" +
                "\n" +
                "Many Thanks,\n" +
                "The Noelle Booth Psychotherapy Team";

        sendMessage("contact@noellebooth.co.uk", subject, text);
    }
}
