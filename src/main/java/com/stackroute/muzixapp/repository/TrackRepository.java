package com.stackroute.muzixapp.repository;

import com.stackroute.muzixapp.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository extends JpaRepository<Track, Integer> {

    @Query("select t from Track t where t.name = ?1")
    List<Track> findByName(String name);
}
