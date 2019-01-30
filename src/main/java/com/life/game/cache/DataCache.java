package com.life.game.cache;

import java.util.concurrent.TimeUnit;

/**
 * 数据缓存接口
 *
 * @param <T>
 */
public interface DataCache<T> {

    /**
     * 缓存数据
     * @param data 数据
     * @return 缓存是否成功
     */
    boolean cacheData(T data);

//	boolean cacheData(List<T> data);

    /**
     * 在超时时间内缓存数据
     * @param data 数据
     * @param timeout 超时时间
     * @param unit 时间单位
     * @return 缓存是否成功
     */
    boolean cacheData(T data, long timeout, TimeUnit unit);

    /**
     * 获取缓存数据大小
     */
    int  size();

    /**
     * 清空缓存
     */
    void clear();

}
