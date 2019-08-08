package com.stackroute.muzixapp.controller;

import com.stackroute.muzixapp.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixapp.exceptions.TrackNotFoundException;
import com.stackroute.muzixapp.model.Track;
import com.stackroute.muzixapp.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1")
public class TrackController {

	@Autowired
	private TrackService trackService;

	

	//update all the methods with code
	@PostMapping("track")
	public ResponseEntity<?> addTrack(@RequestBody Track track) throws TrackAlreadyExistsException,Exception {
		ResponseEntity responseEntity;
		trackService.saveTrack(track);
		responseEntity = new ResponseEntity<Track>(track, HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping("track")
	public ResponseEntity<?> indexPage(ModelMap model) throws exception {
		ResponseEntity responseEntity;
		List<Track> trackList = trackService.getAllTracks();
		model.addAttribute("trackList", trackList);
		responseEntity = new ResponseEntity<List<Track>>(trackList, HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping("track/{name}")
	public ResponseEntity<?> findByName(@PathVariable(value = "name") String name, ModelMap model) {
		ResponseEntity responseEntity;
		List<Track> trackList = trackService.findByName(name);
		model.addAttribute("trackList", trackList);
		responseEntity = new ResponseEntity<List<Track>>(trackList, HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping("track/id/{id}")
	public ResponseEntity<?> getTrackById(@PathVariable(value = "id") Integer id, ModelMap model) throws TrackNotFoundException,Exception {
		ResponseEntity responseEntity;
		Track track = trackService.getTrackById(id);
		model.addAttribute("track", track);
		responseEntity = new ResponseEntity<Track>(track, HttpStatus.OK);
		return responseEntity;
	}

	@DeleteMapping("track/{id}")
	public ResponseEntity<?> deleteTrack(@PathVariable(name = "id") int id) throws TrackNotFoundException,Exception {
		ResponseEntity responseEntity;
		trackService.deleteTrack(id);
		responseEntity = new ResponseEntity<Track>(track, HttpStatus.OK);
		return responseEntity;
	}

	@PutMapping("track/{id}")
	public ResponseEntity<?> updateTrack(@PathVariable(name = "id") int id, @RequestBody Track track) throws TrackNotFoundException,Exception {
		ResponseEntity responseEntity;
		trackService.updateTrack(track);
		responseEntity = new ResponseEntity<Track>(track, HttpStatus.OK);
		return responseEntity;
	}

}
