package com.stackroute.muzixapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.muzixapp.TrackServiceApplication;
import com.stackroute.muzixapp.model.Track;
import com.stackroute.muzixapp.service.TrackService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TrackServiceApplication.class)
public class TrackControllerTest {

    private MockMvc mockMvc;

    private Track track;

    private List<Track> trackList;

    @MockBean
    private TrackService trackService;

    @InjectMocks
    private TrackController trackController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(trackController).build();
        track = new Track(1, "A", "Spring");
        trackList = new ArrayList<>();
        trackList.add(track);
    }

    @Test
    public void addTrack() throws Exception {
        when(trackService.saveTrack(any())).thenReturn(track);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(track)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void indexPage() throws Exception {
        when(trackService.getAllTracks()).thenReturn(trackList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(track)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void findByName() throws Exception {
        when(trackService.findByName(any())).thenReturn(trackList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track/A")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(track)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteTrack() throws Exception {
        when(trackService.deleteTrack(anyInt())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/track/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(track)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateTrack() throws Exception {
        when(trackService.updateTrack(any())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(track)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    private String asJsonString(Track track) {
        try {
            return new ObjectMapper().writeValueAsString(track);
        } catch(Exception e) {
            throw new RuntimeException();
        }
    }

    @After
    public void tearDown() throws Exception {
    }
}