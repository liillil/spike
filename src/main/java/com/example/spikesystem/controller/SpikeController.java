package com.example.spikesystem.controller;

import com.example.spikesystem.dto.InfoResult;
import com.example.spikesystem.service.SpikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: spike-system
 * @ClassName: SpikeController
 * @description:
 * @author: ligang
 * @create: 2024-08-15 10:02:25
 */
@RestController
@RequestMapping("/spike")
public class SpikeController {

    @Autowired
    private SpikeService spikeService;

    @GetMapping("/goodsTestConfig")
    public InfoResult goodsTestConfig(){
        return spikeService.goodsTestConfig();
    }

    @GetMapping("/spikeTest")
    public InfoResult spikeTest(){
        return spikeService.spikeTest();
    }

}
