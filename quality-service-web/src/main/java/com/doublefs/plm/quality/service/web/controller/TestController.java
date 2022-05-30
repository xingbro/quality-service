package com.doublefs.plm.quality.service.web.controller;

import com.doublefs.plm.quality.service.data.common.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuzhaoya
 * @date 2022/4/24
 */
@RestController
@RequestMapping("/mes-test")
@Slf4j
public class TestController {

    /**
     *
     */
    @GetMapping("testSentry")
    public ResponseData<String> testSentry() {
        log.info("testSentry");
        log.error("testSentry");
//        throw new SysException("testSentry");
//        int i = 1 / 0;
        return ResponseData.success();
    }

}
