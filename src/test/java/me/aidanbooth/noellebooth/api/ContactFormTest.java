package me.aidanbooth.noellebooth.api;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContactFormTest {

    // Dummy ContactForm for testing
    static class ContactForm {
        String name;
        String email;
        String message;

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
            String lower = value.toLowerCase();
            if (lower.contains("%0a") || lower.contains("%0d")) {
                return true;
            }

            // header keywords at line start after newline
            if (lower.matches("(?s).*\\r?\\n\\s*(to|cc|bcc|subject):.*")) {
                return true;
            }

            return false;
        }
    }

    @Test
    void validInputShouldNotTriggerInjection() {
        ContactForm form = new ContactForm();
        form.name = "Alice Johnson";
        form.email = "alice.johnson@example.com";
        form.message = "Hi, I’d like some info.\nThanks!"; // newline in message is fine
        assertFalse(form.hasHeaderInjection());
    }

    @Test
    void crlfInNameShouldTriggerInjection() {
        ContactForm form = new ContactForm();
        form.name = "EvilUser\r\nCC: victim@example.com";
        form.email = "attacker@example.com";
        form.message = "Hello";
        assertTrue(form.hasHeaderInjection());
    }

    @Test
    void lfInEmailShouldTriggerInjection() {
        ContactForm form = new ContactForm();
        form.name = "Bob";
        form.email = "bob@example.com\nBcc: spam@example.com";
        form.message = "Test";
        assertTrue(form.hasHeaderInjection());
    }

    @Test
    void percentEncodedNewlineShouldTriggerInjection() {
        ContactForm form = new ContactForm();
        form.name = "Mallory";
        form.email = "mallory%0aCC%3Avictim@example.com@example.com";
        form.message = "Hello";
        assertTrue(form.hasHeaderInjection());
    }

    @Test
    void nullByteShouldTriggerInjection() {
        ContactForm form = new ContactForm();
        form.name = "Null\0Byte";
        form.email = "null@example.com";
        form.message = "Test";
        assertTrue(form.hasHeaderInjection());
    }
}
