package com.stackroute.muzixapp.repository;

import com.stackroute.muzixapp.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository extends MongoRepository<Track, Integer> {

    @Query(value = "{ 'name' : ?0 }")
    List<Track> findByName(String name);
}
