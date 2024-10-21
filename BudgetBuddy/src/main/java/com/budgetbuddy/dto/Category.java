package com.budgetbuddy.dto;

import lombok.Data;

public @Data class Category {
    private String categoryName;

    public Category(String name) {
        this.categoryName = name;
    }
}
