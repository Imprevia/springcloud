package com.gf.springcloud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建路由模型
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GatewayRouteDefinition {
    //路由的Id
    private String id;
    //路由断言集合配置
    @Builder.Default
    private List<GatewayPredicateDefinition> predicates = new ArrayList<>();
    //路由过滤器集合配置
    @Builder.Default
    private List<GatewayFilterDefinition> filters = new ArrayList<>();
    //路由规则转发的目标uri
    private String uri;
    //路由执行的顺序
    @Builder.Default
    private int order = 0;
    //路由描述
    private String description;
}