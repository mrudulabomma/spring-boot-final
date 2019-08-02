package com.stackroute.muzixapp.service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.stackroute.muzixapp.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixapp.exceptions.TrackNotFoundException;
import com.stackroute.muzixapp.model.Track;
import com.stackroute.muzixapp.repository.TrackRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class TrackServiceImpl implements TrackService {

	private TrackRepository trackRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	public TrackServiceImpl(TrackRepository trackRepository) {
		this.trackRepository = trackRepository;
	}

	@Override
	public Track saveTrack(Track track) throws TrackAlreadyExistsException {
		Track savedTrack = null;
		if(!trackRepository.findById(track.getId()).isPresent()) {
			savedTrack = trackRepository.save(track);
		}
		else {
			throw new TrackAlreadyExistsException();
		}
		return savedTrack;
	}

	@Override
	public boolean deleteTrack(int id) throws TrackNotFoundException {
		boolean result = false;
		if(!trackRepository.findById(id).isPresent()) {
			throw new TrackNotFoundException();
		}
		else {
			result = true;
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
			throw new TrackNotFoundException();
		return trackRepository.findByName(name);
	}

	@Override
	public Track getTrackById(int id) throws TrackNotFoundException {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		Track savedTrack = mongoTemplate.findOne(query, Track.class);
		if(savedTrack == null) {
			throw new TrackNotFoundException();
		}
		return savedTrack;
	}

	@Override
	public boolean updateTrack(Track track) throws TrackNotFoundException {
		boolean result = false;
		if(trackRepository.findById(track.getId()).isPresent()) {
			trackRepository.save(track);
			result = true;
		}
		else {
			throw new TrackNotFoundException();
		}
		return result;
	}

}
