package com.stream.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stream.app.model.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, String> {

	List<Video> findByCategory_Name(String categoryName);
	Optional<Video> findByTitle(String title);
}
