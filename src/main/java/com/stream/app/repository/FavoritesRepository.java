package com.stream.app.repository;

import com.stream.app.model.Favorites;
import com.stream.app.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavoritesRepository extends JpaRepository<Favorites, Long> {

    Optional<Favorites> findByUserAndName(Login user, String favoriteName);
}