package com.stream.app.service.impl;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.stream.app.model.Video;
import com.stream.app.repository.VideoRepository;
import com.stream.app.service.VideoService;

import jakarta.annotation.PostConstruct;


@Service
public class VideoServiceImpl implements VideoService {

	@Autowired
	VideoRepository videoRepo;

	@Value("${files.video}")
	String DIR;
	@PostConstruct
	public void inti() {
		File file = new File(DIR);
		
		if(!file.exists()) {
			file.mkdir();
			System.out.println("Folder created...");
		}	
		else {
			System.out.println("Folder Already Created...");
		}
		
	}

	@Override
	public Video save(Video video, MultipartFile file) {
		// TODO Auto-generated method stub
		try {
			String fileName = file.getOriginalFilename();
			String contentType = file.getContentType();
			InputStream inputStream = file.getInputStream();

			// file path
			String cleanFileName = StringUtils.cleanPath(fileName);
			
			// folder path 
			String cleanFolder = StringUtils.cleanPath(DIR);
			
			// folder path with file name
			Path path = Paths.get(cleanFolder,cleanFileName);
			
			System.out.println(path);
			

			// copy file to folder
			
			Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);

			// video meta data
			video.setContentType(contentType);
			video.setFilePath(path.toString());
			
			// save meta data
			
			Video save = videoRepo.save(video);
			return save;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public Video get(String id) {
		// TODO Auto-generated method stub
		Video video = videoRepo.findById(id).orElseThrow(()-> new RuntimeException("Video Not Found"));
		return video;
	}

	@Override
	public Video getByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Video> getAll() {
		// TODO Auto-generated method stub
		return videoRepo.findAll();
	}

	@Override
	public List<Video> findAllByCategoryName(String categoryName) {
		return videoRepo.findByCategory_Name(categoryName);
	}

}
