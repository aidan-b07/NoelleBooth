package me.aidanbooth.noellebooth.data.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactDTO {
    private String name;
    private String email;
    private String message;
    private boolean sendCopy;

    @Override
    public String toString() {
        return String.format("Name: %s\nEmail: %s\nMessage: %s\nSend Copy: %s", name, email, message, sendCopy);
    }
}
