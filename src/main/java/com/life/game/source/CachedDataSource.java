package com.life.game.source;


import com.life.game.cache.DataCache;

public abstract class CachedDataSource<T> {

    private DataCache<T> cache;

    public void setCache(DataCache<T> cache) {
        this.cache = cache;
    }

    protected void cacheData(T data) {
        cache.cacheData(data);
    }
}
