package com.life.game.cache;

import com.google.common.base.Converter;
import com.google.common.collect.Maps;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MapDataCache<K, V> implements DataCache<V> {

    private Map<K, V> map = Maps.newHashMap();

    private Converter<V, K> converter;

    public void setConverter(Converter<V, K> converter) {
        this.converter = converter;
    }

    private K getKey(V data) {
        return converter.convert(data);
    }

    private V retrieveData(String key) {
        return map.get(key);
    }

    @Override
    public boolean cacheData(V data) {
        map.put(getKey(data), data);
        return true;
    }

    @Override
    public boolean cacheData(V data, long timeout, TimeUnit unit) {
        return cacheData(data);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void clear() {
        map.clear();
    }
}
