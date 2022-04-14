package com.gf.springcloud.controller;

import com.gf.springcloud.common.CommonResult;
import com.gf.springcloud.dto.GatewayRouteDefinition;
import com.gf.springcloud.service.DynamicRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/route")
public class RouteController {

    @Autowired
    private DynamicRouteService dynamicRouteService;

    //增加路由
    @PostMapping("/add")
    public Mono<CommonResult> add(@RequestBody GatewayRouteDefinition gatewayRouteDefinition) {
        return Mono.just(CommonResult.succeed(dynamicRouteService.add(gatewayRouteDefinition)));
    }

    //更新路由
    @PostMapping("/update")
    public Mono<CommonResult> update(@RequestBody GatewayRouteDefinition gatewayRouteDefinition) {
        return Mono.just(CommonResult.succeed(dynamicRouteService.update(gatewayRouteDefinition)));
    }

    //删除路由
    @DeleteMapping("/{id}")
    public Mono<CommonResult> delete(@PathVariable String id) {
        return Mono.just(CommonResult.succeed(dynamicRouteService.delete(id)));
    }
}
