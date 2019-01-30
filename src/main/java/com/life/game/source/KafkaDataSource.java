package com.life.game.source;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.life.game.cache.DataCache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class KafkaDataSource extends CachedDataSource<String> {

    private Logger logger = LogManager.getLogger(KafkaDataSource.class);

    private Gson gson = new GsonBuilder().create();

    @Autowired
    private DataCache<String> commonDataCache;

    @PostConstruct
    private void init() {
        setCache(commonDataCache);
    }

    @KafkaListener(topics = "${spring.kafka.producer.topic}")
    public void nextData(String content) {
//        Message m = gson.fromJson(content, Message.class);
        logger.info("receive message:{}", content);
        commonDataCache.cacheData(content);
    }
}
