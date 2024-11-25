package com.stream.app.service;

import com.stream.app.model.Category;

import java.util.List;

public interface CategoryService {
    Category getById(Long id);
    List<Category> getAll();
}
