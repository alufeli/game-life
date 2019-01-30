package com.life.game.cache;

import com.google.common.collect.Queues;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

@Component
public class CommonDataCacheImpl<T> extends CommonDataCache<T> {

    private BlockingQueue<T> queue;

    @Override
    public T retrieveData() {
        return queue.poll();
    }

    @Override
    public T retrieveData(long timeout, TimeUnit unit) {
        try {
            return queue.poll(timeout, unit);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean cacheData(T data) {
        return queue.offer(data);
    }

    @Override
    public boolean cacheData(T data, long timeout, TimeUnit unit) {
        try {
            return queue.offer(data, timeout, unit);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public void clear() {
        queue.clear();
    }

    public void setQueue(int capacity) {
        this.queue = Queues.newArrayBlockingQueue(capacity);
    }
}
