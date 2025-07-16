package me.aidanbooth.noellebooth.data.pagecontent;


import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PageContentRepo extends CrudRepository<PageContent, Long> {
    List<PageContent> getPageContentByPageName(PageType name);
    void deleteByPageNameAndSectionKey(PageType name, String key);
}
