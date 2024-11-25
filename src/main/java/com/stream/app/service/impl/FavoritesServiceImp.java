package com.stream.app.service.impl;

import com.stream.app.model.Favorites;
import com.stream.app.model.Login;
import com.stream.app.repository.FavoritesRepository;
import com.stream.app.repository.LoginRepository;
import com.stream.app.service.FavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoritesServiceImp implements FavoritesService {
    @Autowired
    private FavoritesRepository favoritesRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public Favorites addFavorite(Long userId, String favoriteName) {
        // Retrieve the user
        Login user = loginRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if the favorite already exists for this user
        Optional<Favorites> existingFavorite = favoritesRepository.findByUserAndName(user, favoriteName);
        if (existingFavorite.isPresent()) {
            throw new RuntimeException("Favorite already exists for this user");
        }

        // If not, create and save the new favorite
        Favorites favorite = new Favorites();
        favorite.setName(favoriteName);
        favorite.setUser(user);

        return favoritesRepository.save(favorite);
    }

    @Override
    public List<Favorites> getFavoritesByUserId(Long userId) {
        Login user = loginRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getFavorites();
    }
    @Override
    public void removeFavoriteByUserAndId(Long userId, Long favoriteId) {
        // Verificar si el usuario existe
        Login user = loginRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        // Buscar el favorito por ID y verificar que pertenece al usuario
        Favorites favorite = favoritesRepository.findById(favoriteId)
                .orElseThrow(() -> new RuntimeException("Favorite not found with ID: " + favoriteId));

        if (!favorite.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Favorite does not belong to the user with ID: " + userId);
        }

        // Eliminar el favorito
        favoritesRepository.deleteById(favoriteId);
    }
}
