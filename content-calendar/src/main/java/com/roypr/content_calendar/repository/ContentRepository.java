package com.roypr.content_calendar.repository;

import com.roypr.content_calendar.model.Content;
import com.roypr.content_calendar.model.Status;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository; //returns a list instead of iterables that plain CrudRepository does
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//Spring will at runtime automatically provide a concrete implementation of ContentRepository seeing it extends a base repo, ListCrudRepository.
//User doesn't have to write any code!!!
//It automatically provides implementations of methods in ListCrudRepository and its parents
//Whoa!!
@Repository
public interface ContentRepository extends ListCrudRepository<Content,Integer> {

    List<Content> findAllByTitleContainingIgnoreCase(String keyword); //query derivation
    //Only providing the signature w/o the body!!! And it works!!!

    //writing custom SQL query (for more control) with named param status to run against findAllByStatus repository method
    @Query("""
            SELECT * FROM CONTENT 
            WHERE STATUS = :status
            """)
    List<Content> findAllByStatus(@Param("status") Status status);//param binding into the SQL above
}
