package com.stream.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Video {

	@Id
	private String videoId;
	private String title;
	private String description;
	private String contentType;
	private String filePath;

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false) // Define la columna de unión
	@JsonIgnore
	private Category category;
}