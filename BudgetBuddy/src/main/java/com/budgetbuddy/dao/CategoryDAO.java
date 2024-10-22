package com.budgetbuddy.dao;

import com.budgetbuddy.dto.Category;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class CategoryDAO implements ICategoryDAO{
    //Create a list of category

    List<Category> categoryList = new ArrayList<>(Arrays.asList(
            new Category("Food"),
            new Category("Groceries"),
            new Category("Utilities"),
            new Category("Entertainment"),
            new Category("Health"),
            new Category("Transportation"),
            new Category("Other")
    ));

    @Override
    public List<Category> getCategories() {
        return categoryList;
    }
}
