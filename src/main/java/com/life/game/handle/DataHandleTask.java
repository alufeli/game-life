package com.life.game.handle;

import java.util.List;

/**
 * 数据处理任务，调用数据处理器处理数据
 *
 * @param <T>
 */
public class DataHandleTask<T> implements Runnable {

    private DataHandler<T> handler;
    private List<T> data;

    /**
     * 创建一个数据处理任务
     *
     * @param handler 数据处理器
     * @param data    数据
     */
    public DataHandleTask(DataHandler<T> handler, List<T> data) {
        this.handler = handler;
        this.data = data;
    }


    @Override
    public void run() {
        handler.handle(data);
    }

}
