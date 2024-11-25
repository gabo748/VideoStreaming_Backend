package com.stream.app.controller;

import com.stream.app.model.DTO.AddFavoriteRequest;
import com.stream.app.model.Favorites;
import com.stream.app.service.FavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/favorites")
@CrossOrigin("*")
public class FavoritesController {

    @Autowired
    private FavoritesService favoritesService;

    @PostMapping("/{userId}")
    public ResponseEntity<Favorites> addFavorite(@PathVariable Long userId, @RequestBody AddFavoriteRequest request) {
        Favorites favorite = favoritesService.addFavorite(userId, request.getName());
        return ResponseEntity.ok(favorite);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Favorites>> getFavorites(@PathVariable Long userId) {
        List<Favorites> favorites = favoritesService.getFavoritesByUserId(userId);
        return ResponseEntity.ok(favorites);
    }
}