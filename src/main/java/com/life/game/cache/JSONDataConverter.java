package com.life.game.cache;

import com.google.common.base.Converter;
import com.google.gson.Gson;
import com.life.game.model.Message;

/**
 * JSON数据转换器
 * @author Alan Chen
 *
 */
public class JSONDataConverter extends Converter<String, Message> {
	private Gson gson = new Gson();

	@Override
	protected String doBackward(Message model) {
		return gson.toJson(model);
	}

	@Override
	protected Message doForward(String json) {
		return gson.fromJson(json, Message.class);
	}

}
