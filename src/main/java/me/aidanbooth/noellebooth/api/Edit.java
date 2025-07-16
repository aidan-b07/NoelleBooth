package me.aidanbooth.noellebooth.api;

import me.aidanbooth.noellebooth.data.pagecontent.PageContentService;
import me.aidanbooth.noellebooth.data.pagecontent.PageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class Edit {
    @Autowired
    private PageContentService pageContentService;

    @PostMapping(value = "/edit/update-content", consumes = "application/json")
    public ResponseEntity<String> save(@RequestBody Map<String, String> map) {
        PageType type = PageType.valueOf(map.get("page"));
        pageContentService.saveAll(type, map);
        System.out.println("ALL DONE");
        return ResponseEntity.ok("Saved successfully");
    }
}
