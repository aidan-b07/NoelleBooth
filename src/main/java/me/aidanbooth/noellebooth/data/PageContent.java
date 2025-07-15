package me.aidanbooth.noellebooth.data;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class PageContent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private PageType pageName;

    private String sectionKey;

    @Lob
    private String content;

    public PageContent(PageType pageName, String sectionKey, String content) {
        this.pageName = pageName;
        this.sectionKey = sectionKey;
        this.content = content;
    }

    public PageContent() {}
}
