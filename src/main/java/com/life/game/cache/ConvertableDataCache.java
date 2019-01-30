package com.life.game.cache;

import com.google.common.base.Converter;

import java.util.concurrent.TimeUnit;

public abstract class ConvertableDataCache<A, B> implements DataCache<A> {

    protected Converter<? super A, ? extends B> converter;

    protected B convert(A a) {
        return converter.convert(a);
    }


    /**
     * 获取数据
     * @return
     */
    public abstract B retrieveData();

    /**
     * 在超时时间内获取数据
     * @param timeout 超时时间
     * @param unit 时间单位
     * @return
     */
    public abstract B retrieveData(long timeout, TimeUnit unit);

    public void setConverter(Converter<? super A, ? extends B> converter) {
        this.converter = converter;
    }
}
