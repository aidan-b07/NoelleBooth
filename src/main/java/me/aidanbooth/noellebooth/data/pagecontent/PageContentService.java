package me.aidanbooth.noellebooth.data.pagecontent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PageContentService {
    @Autowired
    private PageContentRepo pageContentRepo;

    private void createPageContent(PageType page, String sectionKey, String content) {
        PageContent newContent = new PageContent(page, sectionKey, content);
        pageContentRepo.save(newContent);
    }

    public void createDefaults() {
        createPageContent(PageType.HOME, "title", "Noelle Booth");
        createPageContent(PageType.HOME, "subtitle", "Physcotherapy");
        createPageContent(PageType.HOME, "text", "Lorem ipsum dolor sit amet, consectetur adipisicing elit. A commodi error exercitationem fugit officia provident quaerat qui quidem quod, velit! Ab corporis, delectus dolorem ducimus, error ipsa iste iure labore perferendis quasi ratione ut? Maiores!");

        createPageContent(PageType.ABOUT, "title", "ABOUT ME");
        createPageContent(PageType.ABOUT, "header1", "Qualifications");
        createPageContent(PageType.ABOUT, "text1", "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aperiam consectetur dolor earum et laudantium magnam molestiae nam natus nemo nulla, odio provident quas reiciendis repellat soluta sunt vero voluptate voluptates.");
        createPageContent(PageType.ABOUT, "header2", "Qualifications 2");
        createPageContent(PageType.ABOUT, "text2", "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aperiam consectetur dolor earum et laudantium magnam molestiae nam natus nemo nulla, odio provident quas reiciendis repellat soluta sunt vero voluptate voluptates.");
        createPageContent(PageType.ABOUT, "header3", "Qualifications 3");
        createPageContent(PageType.ABOUT, "text3", "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aperiam consectetur dolor earum et laudantium magnam molestiae nam natus nemo nulla, odio provident quas reiciendis repellat soluta sunt vero voluptate voluptates.");

        createPageContent(PageType.SERVICES, "title", "SERVICES");
        createPageContent(PageType.SERVICES, "text1", "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aperiam consectetur dolor earum et laudantium magnam molestiae nam natus nemo nulla, odio provident quas reiciendis repellat soluta sunt vero voluptate voluptates.");
        createPageContent(PageType.SERVICES, "text2", "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aperiam consectetur dolor earum et laudantium magnam molestiae nam natus nemo nulla, odio provident quas reiciendis repellat soluta sunt vero voluptate voluptates.");
        createPageContent(PageType.SERVICES, "text3", "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aperiam consectetur dolor earum et laudantium magnam molestiae nam natus nemo nulla, odio provident quas reiciendis repellat soluta sunt vero voluptate voluptates.");

        createPageContent(PageType.CONTACT, "title", "CONTACT");
        createPageContent(PageType.CONTACT, "text1", "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aperiam consectetur dolor earum et laudantium magnam molestiae nam natus nemo nulla, odio provident quas reiciendis repellat soluta sunt vero voluptate voluptates.");
        createPageContent(PageType.CONTACT, "text2", "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aperiam consectetur dolor earum et laudantium magnam molestiae nam natus nemo nulla, odio provident quas reiciendis repellat soluta sunt vero voluptate voluptates.");
    }

    public List<PageContent> getContent(PageType type) {
        return pageContentRepo.getPageContentByPageName(type);
    }

    public Map<String, String> getContentAttributes(PageType type) {
        Map<String, String> map = new HashMap<>();

        for(PageContent content : getContent(type)) {
            map.put(content.getSectionKey(), content.getContent());
        }

        return map;
    }

    @Transactional
    public void saveAll(PageType type, Map<String, String> map) {
        List<PageContent> contents = new ArrayList<>();

        for(String s : map.keySet()) {
            if(s.equals("page")) continue;

            PageContent content = new PageContent(type, s, map.get(s));
            contents.add(content);

            pageContentRepo.deleteByPageNameAndSectionKey(type, s);
        }

        pageContentRepo.saveAll(contents);
    }
}
