package me.aidanbooth.noellebooth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Index {

    @GetMapping(value = "")
    public String getIndex() {
        return "index";
    }

    @GetMapping(value = "/contact")
    public String getContact() {
        return "contact";
    }

    @GetMapping(value = "/services")
    public String getServices() {
        return "services";
    }
}
