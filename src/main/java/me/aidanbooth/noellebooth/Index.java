package me.aidanbooth.noellebooth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Index {

    @GetMapping(value = "")
    public String getIndex() {
        return "index";
    }
}
