package com.fubuki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.fubuki.entity.Book;
import com.fubuki.mapper.BookMapper;
import com.fubuki.mapper.EvaluationMapper;
import com.fubuki.mapper.MemberReadStateMapper;
import com.fubuki.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
public class BookServiceImpl implements BookService {

    private final BookMapper bookMapper;
    private final EvaluationMapper evaluationMapper;
    private final MemberReadStateMapper memberReadStateMapper;

    @Autowired
    public BookServiceImpl(BookMapper bookMapper, EvaluationMapper evaluationMapper, MemberReadStateMapper memberReadStateMapper) {
        this.bookMapper = bookMapper;
        this.evaluationMapper = evaluationMapper;
        this.memberReadStateMapper = memberReadStateMapper;
    }

    @Override
    public IPage<Book> selectPage(Long categoryId, String order, Integer page, Integer rows) {
        IPage<Book> p = new Page<>(page, rows);
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        if(categoryId != null && categoryId != -1){
            wrapper.eq("category_id", categoryId);
        }
        if(order != null){
            if(order.equals("quantity")){
                wrapper.orderByDesc("evaluation_quantity");
            }else if(order.equals("score")){
                wrapper.orderByDesc("evaluation_score");
            }
        }else{
            wrapper.orderByDesc("evaluation_quantity");
        }
        p = bookMapper.selectPage(p, wrapper);
        return p;
    }

    @Override
    public Book selectById(long id) {
        return bookMapper.selectById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateScore() {
        bookMapper.updateScore();
    }

    @Override
    public IPage<Map> selectBookMap(Integer page, Integer rows) {
        IPage p = new Page(page, rows);
        return bookMapper.selectBookMap(p);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Book createBook(Book book) {
        bookMapper.insert(book);
        return book;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Book updateBook(Book book) {
        bookMapper.updateById(book);
        return book;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteBook(Long bookId) {
        bookMapper.deleteById(bookId);
        //同时也要删除评论和阅读状态
        QueryWrapper wrapper1 = new QueryWrapper();
        wrapper1.eq("book_id", bookId);
        evaluationMapper.delete(wrapper1);
        QueryWrapper wrapper2 = new QueryWrapper();
        wrapper2.eq("book_id", bookId);
        memberReadStateMapper.delete(wrapper2);
    }
}
