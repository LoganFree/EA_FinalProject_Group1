package com.budgetbuddy.dao;

import com.budgetbuddy.dto.Category;

import java.util.List;

public interface ICategoryDAO {
    List<Category> getCategories();
}