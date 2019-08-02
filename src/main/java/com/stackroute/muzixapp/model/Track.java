package com.stackroute.muzixapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;

@Document(collection = "track")
@Data
@AllArgsConstructor
@Builder
public class Track {

	@Id
	private int id;
	private String name;
	private String comment;

	@PersistenceConstructor
	public Track() {
	}
}