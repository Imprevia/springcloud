package com.gf.springcloud.dto;

import lombok.*;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 路由过滤器模型
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GatewayFilterDefinition {
    private String name;
    @Builder.Default
    private Map<String, String> args = new LinkedHashMap<>();
}
