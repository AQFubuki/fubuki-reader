package com.fubuki.controller;

import com.fubuki.entity.Category;
import com.fubuki.service.CategoryService;
import com.fubuki.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public Object list() {
        List<Category> categories = null;
        ResponseUtils resp = null;
        try {
            categories = categoryService.selectAll();
            resp = new ResponseUtils().put("list", categories);
        } catch (Exception e) {
            resp = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
            throw new RuntimeException(e);
        }

        return resp;
    }
}
