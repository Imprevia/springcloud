package com.gf.springcloud.routes;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

import static com.gf.springcloud.commons.RedisConstants.GATEWAY_ROUTES_PREFIX;

/**
 * @Author: gyt
 * @Description:
 * @Date: create in 2022/4/12 16:45
 */
@Component
public class RedisRouteDefinitionRepository implements RouteDefinitionRepository {


    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private Set<RouteDefinition> routeDefinitions = new HashSet<>();

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        routeDefinitions.clear();
        BoundHashOperations<String, String, String> boundHashOperations = stringRedisTemplate.boundHashOps(GATEWAY_ROUTES_PREFIX);
        Map<String, String> map = boundHashOperations.entries();
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            routeDefinitions.add(JSON.parseObject(entry.getValue(), RouteDefinition.class));
        }
        return Flux.fromIterable(routeDefinitions);
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return route.flatMap(routeDefinition -> {
            routeDefinitions.add(routeDefinition);
            return Mono.empty();
        });
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return routeId.flatMap(id -> {
            List<RouteDefinition> routeDefinitionList = routeDefinitions.stream().filter(
                    routeDefinition -> StringUtils.equals(routeDefinition.getId(), id)
            ).collect(Collectors.toList());
            routeDefinitions.removeAll(routeDefinitionList);
            return Mono.empty();
        });
    }
}
