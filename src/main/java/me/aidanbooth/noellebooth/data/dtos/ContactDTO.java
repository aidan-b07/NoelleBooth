package me.aidanbooth.noellebooth.data.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Locale;
import java.util.regex.Pattern;

@Getter
@Setter
public class ContactDTO {
    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Email
    @Size(max = 254)
    private String email;

    @NotBlank
    @Size(max = 1500)
    private String message;
    private boolean sendCopy;

    @Override
    public String toString() {
        return String.format("Name: %s\nEmail: %s\nMessage: %s\nSend Copy: %s", name, email, message, sendCopy);
    }

    public boolean hasHeaderInjection() {
        return containsInjection(name) || containsInjection(email);
    }

    private boolean containsInjection(String value) {
        if (value == null) return false;

        // raw CR, LF, or NUL
        if (value.contains("\r") || value.contains("\n") || value.contains("\0")) {
            return true;
        }

        // percent-encoded CR/LF (case-insensitive)
        String lower = value.toLowerCase(Locale.ROOT);
        if (lower.contains("%0a") || lower.contains("%0d")) {
            return true;
        }

        // header keywords at line start after newline
        if (lower.matches("(?s).*\\r?\\n\\s*(to|cc|bcc|subject):.*")) {
            return true;
        }

        return false;
    }


    private static final Pattern SAFE_TEXT = Pattern.compile("^[a-zA-Z0-9 .,'-]+$");

    private String sanitize(String input) {
        if (input == null || !SAFE_TEXT.matcher(input).matches()) {
            throw new IllegalArgumentException("Invalid characters in input");
        }
        return input;
    }
    public void sanitize() {
        this.name = sanitize(name);
        this.email = sanitize(email);
        this.message = sanitize(message);
    }
}
