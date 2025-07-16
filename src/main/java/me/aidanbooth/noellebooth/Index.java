package me.aidanbooth.noellebooth;

import me.aidanbooth.noellebooth.data.pagecontent.PageContentService;
import me.aidanbooth.noellebooth.data.pagecontent.PageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Index {
    @Autowired
    private PageContentService pageContentService;

    @GetMapping(value = "/login")
    public String getLogin() {
        return "login";
    }



    @GetMapping(value = "")
    public String getIndex(Model model) {
        model.addAllAttributes(pageContentService.getContentAttributes(PageType.HOME));

        return "index";
    }

    @GetMapping(value = "/edit/home")
    public String getEditHome(Model model) {
        model.addAllAttributes(pageContentService.getContentAttributes(PageType.HOME));

        return "edit/index";
    }

    @GetMapping(value = "/contact")
    public String getContact(Model model) {
        model.addAllAttributes(pageContentService.getContentAttributes(PageType.CONTACT));

        return "contact";
    }

    @GetMapping(value = "/services")
    public String getServices(Model model) {
        model.addAllAttributes(pageContentService.getContentAttributes(PageType.SERVICES));

        return "services";
    }

    @GetMapping(value = "/about")
    public String getAbout(Model model) {
        model.addAllAttributes(pageContentService.getContentAttributes(PageType.ABOUT));

        return "about";
    }
}
