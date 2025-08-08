package com.roypr.content_calendar.controller;

import com.roypr.content_calendar.model.Content;
import com.roypr.content_calendar.repository.ContentJdbcTemplateRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

//Commented annotations to successfully build & run project as there wud hv bn multiple RestControllers
//@CrossOrigin //so that a front-end application on a different domain and/or port is allowed to interact with the springboot backend
//@RestController
//@RequestMapping("/api/content")
public class ContentController_forSpringJDBCTemplate {

    private final ContentJdbcTemplateRepository repository;

    /*@Autowired annotation is not needed here for dependency injection of repository object into the ContentController constructor,
     since it is the only public constructor. It is implicit.*/
    public ContentController_forSpringJDBCTemplate(ContentJdbcTemplateRepository repository) {
        /* repository = new ContentCollectionRepository();
        Object creation with new keyword should be avoided as it is now done through the ApplicationContext of Spring fw
         */
        this.repository = repository;

    }

    @GetMapping("")
    public List<Content> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Content findByID(@PathVariable Integer id) {
        return repository.getContentByID(id);

    }

    @ResponseStatus(HttpStatus.CREATED) //Response code 201 for successful creation
    @PostMapping("")
    public void create(@Valid @RequestBody Content content){ //validate the content param as per Content definition
        repository.createContent(content.title(),
                content.desc(),
                content.status(),
                content.contentType(),
                content.url()
        );
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)//No response needed
    @PutMapping("/{id}")
    public void update(@RequestBody Content content,@PathVariable Integer id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found");
        }
        repository.updateContent(id,
                content.title(),
                content.desc(),
                content.status(),
                content.contentType(),
                content.url());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)//No response needed
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found");
        }
        repository.deleteContent(id);
    }
}