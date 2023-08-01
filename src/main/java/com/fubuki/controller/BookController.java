package com.fubuki.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fubuki.entity.Book;
import com.fubuki.service.BookService;
import com.fubuki.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/list")
    public ResponseUtils list(Long categoryId, String order, Integer page) {
        IPage<Book> iPage = bookService
                .selectPage(categoryId, order, page, 10);
        ResponseUtils resp = null;
        try {
            resp = new ResponseUtils().put("page", iPage);
        } catch (Exception e) {
            resp = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
            throw new RuntimeException(e);
        }
        return resp;
    }

    @GetMapping("/id/{id}")
    public ResponseUtils selectById(@PathVariable("id") Long id){
        ResponseUtils resp = null;
        try {
            Book book = bookService.selectById(id);
            resp = new ResponseUtils().put("book",book);
        }catch (Exception e){
            e.printStackTrace();
            resp = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
        return resp;
    }

}
