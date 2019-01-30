package com.life.game.handle;

import com.google.common.collect.Lists;
import com.life.game.cache.CommonDataCache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class DataHandleCenter<T> implements HandleCenter<T> {

    private static Logger logger = LogManager.getLogger(DataHandleCenter.class);

    // 任务处理线程集合
    private List<DataHandler<T>> handlers = Lists.newArrayList();

    private ExecutorService service;

    private CommonDataCache<T> cache;

    // 分批次处理大小
    private int handleBatchSize;

    private int threadSleepTime;

    private long timeout;

    private TimeUnit unit;


    @Override
    public void addHandler(DataHandler<T> dataHandler) {
        handlers.add(dataHandler);
    }

    @Override
    public void run() {

        List<T> commonContexts = Lists.newArrayList();

        // 发送批次大小
        int batchSize;

        try {
            Thread.sleep(threadSleepTime);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }

        if (cache.size() < handleBatchSize) {
            batchSize = cache.size();
        } else {
            batchSize = handleBatchSize;
        }

        if (batchSize > 0) {
            logger.info("deal the batch size of CommonContext is {}", batchSize);
        }

        // 临时变量
        int size = 0;
        // 按照发送批量大小从队列取出相应大小的数据，放入集合中
        while (++size <= batchSize) {
            T commonContext = cache.retrieveData(timeout, unit);
            if (commonContext != null) {
                commonContexts.add(commonContext);
            }
        }

        logger.debug("CommonHandleCenter distribute the commonContexts {} to DataHandleTask", commonContexts);

        if (!commonContexts.isEmpty()) {
            for (DataHandler<T> handler : handlers) {
                service.submit(new DataHandleTask<T>(handler, commonContexts));
            }
        }


    }
}
