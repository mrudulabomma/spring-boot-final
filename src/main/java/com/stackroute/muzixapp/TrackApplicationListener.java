package com.stackroute.muzixapp;

import com.stackroute.muzixapp.model.Track;
import com.stackroute.muzixapp.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class TrackApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    TrackService trackService;

    public TrackApplicationListener(TrackService trackService) {
        this.trackService = trackService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Track track = new Track(1, "A", "Spring");
        try {
            trackService.saveTrack(track);
            for(Track allTracks : trackService.getAllTracks()) {
                System.out.println(allTracks.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
