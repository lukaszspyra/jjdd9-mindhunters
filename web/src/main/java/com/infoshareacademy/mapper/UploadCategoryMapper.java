package com.infoshareacademy.mapper;

import com.infoshareacademy.domain.Category;
import com.infoshareacademy.jsonSupport.CategoryJson;
import com.infoshareacademy.service.CategoryService;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class UploadCategoryMapper {

    @EJB
    private CategoryService categoryService;

    public Category toEntity(CategoryJson categoryJson) {
        Category category = categoryService.getOrCreate(categoryJson.getCategoryName());
        return category;
    }
}
