package com.gf.springcloud.controller;

import com.gf.springcloud.entities.Payment;
import com.gf.springcloud.common.CommonResult;
import com.gf.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Slf4j
@RestController
@RefreshScope //支持Nacos的动态刷新功能。
public class OrderController {
    @Value("${config.info}")
    private String configInfo;

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id)
    {
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/config/info")
    @ResponseBody
    public String getConfigInfo() {
        return configInfo;
    }
}
