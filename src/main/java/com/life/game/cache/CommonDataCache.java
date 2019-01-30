package com.life.game.cache;

import java.util.concurrent.TimeUnit;

public abstract class CommonDataCache<T> implements DataCache<T> {

	/**
	 * 获取数据
	 * @return
	 */
	public abstract T retrieveData();

	/**
	 * 在超时时间内获取数据
	 * @param timeout 超时时间
	 * @param unit 时间单位
	 * @return
	 */
	public abstract T retrieveData(long timeout, TimeUnit unit);

}
