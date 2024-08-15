package com.example.spikesystem.service.impl;

import com.example.spikesystem.cache.RedisService;
import com.example.spikesystem.dto.InfoResult;
import com.example.spikesystem.dto.InfoResults;
import com.example.spikesystem.service.SpikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @program: spike-system
 * @ClassName: SpikeServiceImpl
 * @description:
 * @author: ligang
 * @create: 2024-08-15 09:37:57
 */
@Slf4j
@Service
public class SpikeServiceImpl implements SpikeService {

    @Autowired
    private RedisService redisService;

    @Override
    public InfoResult spikeTest() {
        List<String> keys = Arrays.asList("123456", "654321","GOODS_STOCK","USER_GOODS_SPIKE");
        return InfoResults.ok(redisService.setLua(keys,"spike.lua"));
    }

    @Override
    public InfoResult goodsTestConfig() {
        try {
            redisService.set("GOODS_STOCK_" + 654321, 1, 50000);
            redisService.sSet("USER_GOODS_SPIKE_" + 654321, 123);
        }catch (Exception e){
            log.error(">>>>>error msg:{}",e);
            return InfoResults.error();
        }
        return InfoResults.success();
    }
}
