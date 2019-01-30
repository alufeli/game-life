package com.life.game.handle;

import java.util.List;

/**
 * 数据处理器
 * @author Wagic
 * @param <T>
 */
public interface DataHandler<T> {

    /**
     * 处理数据
     *
     * @param data
     */
    void handle(List<T> data);
}
