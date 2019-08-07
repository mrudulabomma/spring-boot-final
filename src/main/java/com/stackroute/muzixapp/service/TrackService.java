package com.stackroute.muzixapp.service;


import java.util.List;

import com.stackroute.muzixapp.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixapp.exceptions.TrackNotFoundException;
import com.stackroute.muzixapp.model.Track;

public interface TrackService {

	 Track saveTrack(Track track) throws TrackAlreadyExistsException;

	 boolean deleteTrack(int id) throws TrackNotFoundException;

	 List<Track> getAllTracks();

	 Track getTrackById(int id) throws TrackNotFoundException;

	 boolean updateTrack(Track track) throws TrackNotFoundException;

	 List<Track> findByName(String Name) throws TrackNotFoundException;

}
