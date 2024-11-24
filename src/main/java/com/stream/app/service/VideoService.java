package com.stream.app.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.stream.app.model.Video;

public interface VideoService {

	//save Video
	Video save(Video video, MultipartFile file);
	
	// get Video by Id
	Video get(String id);
	
	//get video by title
	Video getByTitle(String title);
	
	// get all video
	
	List<Video> getAll();
}
