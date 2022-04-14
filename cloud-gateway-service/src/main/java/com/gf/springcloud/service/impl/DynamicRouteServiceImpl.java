package com.gf.springcloud.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.gf.springcloud.common.CommonResult;
import com.gf.springcloud.dto.GatewayRouteDefinition;
import com.gf.springcloud.service.DynamicRouteService;
import com.gf.springcloud.vo.GatewayRoutesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

import static com.gf.springcloud.commons.RedisConstants.GATEWAY_ROUTES_PREFIX;

@Service
public class DynamicRouteServiceImpl implements DynamicRouteService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RouteDefinitionWriter routeDefinitionWriter;

    @Override
    public String add(GatewayRouteDefinition gatewayRouteDefinition) {
        stringRedisTemplate.boundHashOps(GATEWAY_ROUTES_PREFIX)
                .put(gatewayRouteDefinition.getId(), JSONObject.toJSONString(gatewayRouteDefinition));
        return gatewayRouteDefinition.getId();
    }

    @Override
    public String update(GatewayRouteDefinition gatewayRouteDefinition) {
        delete(gatewayRouteDefinition.getId());
        add(gatewayRouteDefinition);
        return gatewayRouteDefinition.getId();
    }

    @Override
    public String delete(String id) {
        stringRedisTemplate.boundHashOps(GATEWAY_ROUTES_PREFIX).delete(id);
        return id;
    }

    @Override
    public CommonResult<GatewayRoutesVO> findAll(Map<String, Object> params) {
        return null;
    }

    @Override
    public String synchronization() {
        return null;
    }

    @Override
    public String updateFlag(Map<String, Object> params) {
        return null;
    }
}
