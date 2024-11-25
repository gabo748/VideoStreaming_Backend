package com.stream.app.service;


import com.stream.app.model.Favorites;

import java.util.List;

public interface FavoritesService {
    public Favorites addFavorite(Long userId, String favoriteName);

    public List<Favorites> getFavoritesByUserId(Long userId);
}
