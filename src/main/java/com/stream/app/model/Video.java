package com.stream.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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

	@OneToOne
	@JoinColumn(name = "category_id") // Define la columna de unión
	private Category category;
}