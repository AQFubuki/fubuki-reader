package com.fubuki.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ComputeTask {
    Logger logger = LoggerFactory.getLogger(ComputeTask.class);
    @Scheduled(cron = "0 * * * * ?")
    public void updateScore(){
        logger.info("已更新所有图书评分");
    }
}
