package me.aidanbooth.noellebooth.data;

import me.aidanbooth.noellebooth.data.pagecontent.PageContentService;
import me.aidanbooth.noellebooth.data.user.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private PageContentService pageContentService;

    @Autowired
    private AppUserService userService;

    @Override
    public void run(String... args) throws Exception {
        pageContentService.createDefaults();
        userService.createDefaults();
    }
}
