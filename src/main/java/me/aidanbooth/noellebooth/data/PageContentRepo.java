package me.aidanbooth.noellebooth.data;


import org.hibernate.query.Page;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PageContentRepo extends CrudRepository<PageContent, Long> {
    List<PageContent> getPageContentByPageName(PageType name);
}
