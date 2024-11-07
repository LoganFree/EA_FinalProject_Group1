package com.budgetbuddy.dao.CategoryDAO;

import com.budgetbuddy.dto.Category;

import java.io.IOException;
import java.util.List;

public interface ICategoryDAO {
    List<Category> getCategories() throws IOException;

}
