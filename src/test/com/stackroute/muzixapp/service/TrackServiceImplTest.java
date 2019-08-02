package com.stackroute.muzixapp.service;

import com.stackroute.muzixapp.TrackServiceApplication;
import com.stackroute.muzixapp.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixapp.exceptions.TrackNotFoundException;
import com.stackroute.muzixapp.model.Track;
import com.stackroute.muzixapp.repository.TrackRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TrackServiceApplication.class)
public class TrackServiceImplTest {

    private Track track;

    private List<Track> trackList;

    @Mock
    TrackRepository trackRepository;

    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private TrackServiceImpl trackService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        track = new Track(1, "A", "Spring");
        trackList = new ArrayList<>();
        trackList.add(track);
    }

    @Test
    public void saveTrackTestSuccess() {
        when(trackRepository.save(any())).thenReturn(track);
        Track savedTrack = trackService.saveTrack(track);
        Assert.assertEquals(track, savedTrack);
        //verify here verifies that trackRepository save method is only called once
        verify(trackRepository,times(1)).save(track);
    }

    @Test(expected = TrackAlreadyExistsException.class)
    public void saveTrackTestFailure() {
        when(trackRepository.save((Track) any())).thenThrow(TrackAlreadyExistsException.class);
        trackService.saveTrack(track);
    }

    @Test
    public void deleteTrackTestSuccess() {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(1));
        when(trackRepository.findById(anyInt())).thenReturn(Optional.ofNullable(track));
        when(mongoTemplate.findOne(query, Track.class)).thenReturn(track);
        doNothing().when(trackRepository).delete(any());
        boolean result = trackService.deleteTrack(1);
        Assert.assertEquals(true, result);
        //verify here verifies that trackRepository save method is only called once
        verify(trackRepository,times(1)).delete(track);
    }

    @Test(expected = TrackNotFoundException.class)
    public void deleteTrackTestFailure() {
        when(trackRepository.findById(anyInt())).thenReturn(Optional.ofNullable(track));
        doNothing().when(trackRepository).delete(any());
        boolean result = trackService.deleteTrack(2);
        Assert.assertEquals(true, result);
        //verify here verifies that trackRepository save method is only called once
        verify(trackRepository,times(1)).delete(track);
    }

    @Test
    public void getAllTracksTestSuccess() {
        when(trackRepository.findAll()).thenReturn(trackList);
        List<Track> list = trackService.getAllTracks();
        Assert.assertEquals(trackList, list);
        //verify here verifies that trackRepository save method is only called once
        verify(trackRepository,times(1)).findAll();
    }

    @Test
    public void findByNameTestSuccess() {
        when(trackRepository.findByName(anyString())).thenReturn(trackList);
        List<Track> list = trackService.findByName("A");
        Assert.assertEquals(trackList, list);
        //verify here verifies that trackRepository save method is only called once
        verify(trackRepository,times(2)).findByName(anyString());
    }

    @Test(expected = TrackNotFoundException.class)
    public void findByNameTestFailure() {
        when(trackRepository.findByName(anyString())).thenReturn(new ArrayList<>());
        trackService.findByName("B");
        //verify here verifies that trackRepository save method is only called once
        verify(trackRepository,times(2)).findByName(anyString());
    }

    @Test
    public void getTrackByIdTestSuccess() {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(1));
        when(mongoTemplate.findOne(query, Track.class)).thenReturn(track);
        Track foundTrack = trackService.getTrackById(1);
        Assert.assertEquals(track, foundTrack);
        //verify here verifies that trackRepository save method is only called once
        verify(mongoTemplate,times(1)).findOne(query, Track.class);
    }

    @Test(expected = TrackNotFoundException.class)
    public void getTrackByIdTestFailure() {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(1));
        when(mongoTemplate.findOne(query, Track.class)).thenReturn(null);
        trackService.getTrackById(1);

        verify(mongoTemplate, times(1)).findOne(query, Track.class);
    }

    @Test
    public void updateTrackTestSuccess() {
        when(trackRepository.findById(anyInt())).thenReturn(Optional.ofNullable(track));
        when(trackRepository.save(any())).thenReturn(track);
        boolean result = trackService.updateTrack(track);
        Assert.assertEquals(true, result);
        //verify here verifies that trackRepository save method is only called once
        verify(trackRepository,times(1)).save(track);
    }

    @Test(expected = TrackNotFoundException.class)
    public void updateTrackTestFailure() {
        when(trackRepository.findById(anyInt())).thenReturn(Optional.of(track));
        when(trackRepository.save(any())).thenThrow(TrackNotFoundException.class);
        trackService.updateTrack(track);

        verify(trackRepository, times(1)).save(track);
    }

    @After
    public void tearDown() throws Exception {
    }
}