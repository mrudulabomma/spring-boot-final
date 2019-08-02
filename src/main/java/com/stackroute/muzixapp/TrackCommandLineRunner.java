package com.stackroute.muzixapp;

import com.stackroute.muzixapp.model.Track;
import com.stackroute.muzixapp.service.TrackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TrackCommandLineRunner implements CommandLineRunner {

    @Autowired
    TrackService trackService;
    @Override
    public void run(String... args) throws Exception {
        Logger logger = LoggerFactory.getLogger(TrackCommandLineRunner.class);
        try {
            Track track = new Track(2, "B", "Spring");
            trackService.saveTrack(track);
            for(Track allTracks : trackService.getAllTracks()) {
                logger.info(allTracks.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
