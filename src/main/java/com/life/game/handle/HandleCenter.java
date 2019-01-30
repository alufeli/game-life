package com.life.game.handle;

/**
 * 数据处理中心，接口
 *
 * @param <T>
 */
public interface HandleCenter<T> extends Runnable {


    /**
     * 增加一个数据处理器
     *
     * @param dataHandler
     */
    void addHandler(DataHandler<T> dataHandler);
}
