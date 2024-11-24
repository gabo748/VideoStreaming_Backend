package com.stream.app.service.impl;

import com.stream.app.model.Category;
import com.stream.app.repository.CategoryRepository;
import com.stream.app.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService  {
    @Autowired
    CategoryRepository categoryRepo;
    @Override
    public List<Category> getAll() {
        return categoryRepo.findAll();
    }
}
