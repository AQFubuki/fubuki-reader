package com.fubuki.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fubuki.entity.Book;

import java.util.Map;


public interface BookService {
    /**
     * 分页查询图书
     * @param categoryId 分类编号
     * @param order 排序方式
     * @param page 页号
     * @param rows 每页记录数
     * @return 分页对象
     */
    public IPage<Book> selectPage(Long categoryId, String order, Integer page, Integer rows);
    public Book selectById(long id);
    public void updateScore();

    /**
     * 分页查询
     * @param page 页数
     * @param rows 每页行数
     * @return
     */
    public IPage<Map> selectBookMap(Integer page, Integer rows);
    public Book createBook(Book book);
    public Book updateBook(Book book);
    public void deleteBook(Long bookId);
}
