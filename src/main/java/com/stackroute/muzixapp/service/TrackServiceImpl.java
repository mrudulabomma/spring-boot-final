package com.stackroute.muzixapp.service;

import com.stackroute.muzixapp.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixapp.exceptions.TrackNotFoundException;
import com.stackroute.muzixapp.model.Track;
import com.stackroute.muzixapp.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackServiceImpl implements TrackService {

	private TrackRepository trackRepository;

	@Autowired
	public TrackServiceImpl(TrackRepository trackRepository) {
		this.trackRepository = trackRepository;
	}

	@Override
	public boolean saveTrack(Track track) throws TrackAlreadyExistsException {
		boolean result = false;
		if(!trackRepository.findById(track.getId()).isPresent()) {
			Track savedTrack = trackRepository.save(track);
			result = true;
		}
		else {
			throw new TrackAlreadyExistsException("Track Already Exists");
		}
		return result;
	}

	@Override
	public boolean deleteTrack(int id) throws TrackNotFoundException {
		boolean result = false;
		if(!trackRepository.findById(id).isPresent()) {
			result = false;
			throw new TrackNotFoundException("Track Not Found");
		}
		else {
			trackRepository.delete(getTrackById(id));
		}
		return result;
	}

	@Override
	public List<Track> getAllTracks() {
		return trackRepository.findAll();
	}

	@Override
	public List<Track> findByName(String name) throws TrackNotFoundException {
		if(!(trackRepository.findByName(name).size() > 0))
			throw new TrackNotFoundException("Track Not Found");
		return trackRepository.findByName(name);
	}

	@Override
	public Track getTrackById(int id) throws TrackNotFoundException {
		Track savedTrack = trackRepository.getOne(id);
		if(savedTrack == null) {
			throw new TrackNotFoundException("Track Not Found");
		}
		return savedTrack;
	}

	@Override
	public boolean updateTrack(Track track) throws TrackNotFoundException {
		boolean result = false;
		Track savedTrack = trackRepository.getOne(track.getId());
		savedTrack.setName(track.getName());
		savedTrack.setComment(track.getComment());
		trackRepository.save(savedTrack);
		if(savedTrack != null) {
			result = true;
		}
		else {
			throw new TrackNotFoundException("Track Not Found");
		}
		return result;
	}

}
