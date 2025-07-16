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
    public ResponseEntity<Map<String, String>> save(@RequestBody Map<String, String> map) {
        try {
            PageType type = PageType.valueOf(map.get("page").toUpperCase());
            pageContentService.saveAll(type, map);

            return ResponseEntity.ok(Map.of(
                    "status", "success",
                    "message", "Saved successfully"
            ));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "error",
                    "message", e.getMessage() != null ? e.getMessage() : "An unexpected error occurred"
            ));
        }
    }

}
