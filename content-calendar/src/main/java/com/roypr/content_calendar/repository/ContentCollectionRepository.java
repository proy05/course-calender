package com.roypr.content_calendar.repository;

import com.roypr.content_calendar.model.Content;
import com.roypr.content_calendar.model.Status;
import com.roypr.content_calendar.model.Type;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class ContentCollectionRepository {
    private final List<Content> contentList = new ArrayList<>();

    public ContentCollectionRepository() {
    }

    public List<Content> findAll(){
        return contentList;
    }

    public Optional<Content> findById(Integer id){
        return contentList.stream().filter(c -> c.id().equals(id)).findFirst();
    }


    public void save(Content content) {
        contentList.removeIf(c -> c.id().equals(content.id()));
        contentList.add(content);
    }

//    public void update(Content content) {
//
//        if (contentList.removeIf(c -> c.id().equals(content.id()))){
//            System.out.println("Removed:" + content.id());
//        }
//        contentList.add(content);
//    }

    public boolean existsById(Integer id) {
        return contentList.stream().filter(c -> c.id().equals(id)).count()==1;
    }

    @PostConstruct
    public void init(){
        Content c  = new Content(1,
                "My first blogpost",
                "Something",
                Status.IDEA,
                Type.ARTICLE,
                LocalDateTime.now(),
                null,
                ""
        );
        contentList.add(c);
    }

    public void deleteById(Integer id) {
        contentList.removeIf(c -> c.id().equals(id));
    }
}
