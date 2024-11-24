package com.stream.app.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.stream.app.model.Video;
import com.stream.app.payload.CustomMessage;
import com.stream.app.service.VideoService;

@RestController
@RequestMapping("/api/v1/videos")
@CrossOrigin("*")
public class VideoController {
	
	private VideoService videoService;
	
	
	
	public VideoController(VideoService videoService) {
		this.videoService = videoService;
	}


// ####################  Create Video  ##########################
	@PostMapping
	public ResponseEntity<?> create(
				@RequestParam("file") MultipartFile file,
				@RequestParam("title") String title,
				@RequestParam("description") String description
			){
		
		Video video = new Video();
		
		video.setTitle(title);
		video.setDescription(description);
		video.setVideoId(UUID.randomUUID().toString());
		
		Video savedVideo = videoService.save(video, file);
		
		if(savedVideo != null) {
			return ResponseEntity
				.status(HttpStatus.OK)
				.body(savedVideo);
		}
		else {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(CustomMessage.builder()
							.message("Video not uploaded")
							.success(false)
							.build()
						);
		}
		
		
	}

// ####################  Stream Video  ##########################
	
	@GetMapping("/stream/{videoId}")
	public ResponseEntity<Resource> stream(
			@PathVariable("videoId") String videoId
			){
		
		Video video = videoService.get(videoId);
		
		String contentType = video.getContentType();
		String filePath = video.getFilePath();
		
		Resource resource = new FileSystemResource(filePath);
		
		if(contentType == null) {
			contentType = "application/octet-stream";
		}
		
		return ResponseEntity
				.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.body(resource);
	}
	
// ####################  Get ALL Video  ##########################
	@GetMapping
	public List<Video> getAllVideo(){
		return videoService.getAll();
	}
}
