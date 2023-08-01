package com.fubuki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fubuki.entity.Category;
import com.fubuki.entity.Test;
import com.fubuki.mapper.CategoryMapper;
import com.fubuki.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<Category> selectAll() {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.orderByAsc("category_id");
        List<Category> categories=categoryMapper.selectList(queryWrapper);
        return categories;
    }
}
