package com.life.game.job;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 调度任务示例类
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: NewLand Computer</p>
 *
 * @author wagic
 * @version 1.0 wagic 2018-02-07 created
 */
@Configuration
public class ScheduledJob {

    private static final Logger logger = LogManager.getLogger(ScheduledJob.class);


    @Scheduled(cron = "${quartz.cron}")
    public void work() {
        logger.info("this is a example quartz job!!!!");
    }
}
