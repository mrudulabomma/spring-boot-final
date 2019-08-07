package com.stackroute.muzixapp.service;


import java.util.List;

import com.stackroute.muzixapp.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixapp.exceptions.TrackNotFoundException;
import com.stackroute.muzixapp.model.Track;

public interface TrackService {

	 Track saveTrack(Track track) throws TrackAlreadyExistsException,Exception;

	 boolean deleteTrack(int id) throws TrackNotFoundException,Exception;

	 List<Track> getAllTracks() throws Exception;

	 Track getTrackById(int id) throws TrackNotFoundException,Exception;

	 boolean updateTrack(Track track) throws TrackNotFoundException,Exception;

	 List<Track> findByName(String Name) throws TrackNotFoundException,Exception;

}
