package com.stackroute.muzixapp;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.stackroute.muzixapp.model.Track;
import com.stackroute.muzixapp.service.TrackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class TrackCommandLineRunner implements CommandLineRunner {

    @Autowired
    TrackService trackService;

    @Autowired
    private Environment environment;

    @Override
    public void run(String... args) throws Exception {
        Logger logger = LoggerFactory.getLogger(TrackCommandLineRunner.class);
        try {
            Track track = new Track(2, "B", "Spring");
            trackService.saveTrack(track);
            System.out.println(environment.getProperty("server.port"));
            for(Track allTracks : trackService.getAllTracks()) {
                logger.info(allTracks.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
