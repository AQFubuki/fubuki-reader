package com.fubuki.task;

import com.fubuki.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ComputeTask {
    private final BookService bookService;
    Logger logger = LoggerFactory.getLogger(ComputeTask.class);

    @Autowired
    public ComputeTask(BookService bookService) {
        this.bookService = bookService;
    }

    @Scheduled(cron = "0 * * * * ?")
    public void updateScore() {
        bookService.updateScore();
        logger.info("已更新所有图书评分");
    }
}
