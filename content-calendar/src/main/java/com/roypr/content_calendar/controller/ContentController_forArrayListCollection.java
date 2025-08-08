package com.roypr.content_calendar.controller;

import com.roypr.content_calendar.model.Content;
import com.roypr.content_calendar.repository.ContentCollectionRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

//@CrossOrigin //so that a front-end application on a different domain and/or port is allowed to interact with the springboot backend
//@RestController
//@RequestMapping("/api/content")
public class ContentController_forArrayListCollection {

    private final ContentCollectionRepository repository;

    /*@Autowired annotation is not needed here for dependency injection of repository object into the ContentController constructor,
     since it is the only public constructor. It is implicit.*/
    public ContentController_forArrayListCollection(ContentCollectionRepository repository) {
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
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found"));
    }

    @ResponseStatus(HttpStatus.CREATED) //Response code 201 for successful creation
    @PostMapping("")
    public void create(@Valid @RequestBody Content content){ //validate the content param as per Content definition
        repository.save(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)//No response needed
    @PutMapping("/{id}")
    public void update(@RequestBody Content c,@PathVariable Integer id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found");
        }
        repository.save(c);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)//No response needed
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found");
        }
        repository.deleteById(id);
    }
}