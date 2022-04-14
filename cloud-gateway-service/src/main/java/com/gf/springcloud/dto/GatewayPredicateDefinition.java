package com.gf.springcloud.dto;

import lombok.*;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 路由断言模型
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GatewayPredicateDefinition {
    private String name;
    @Builder.Default
    private Map<String, String> args = new LinkedHashMap<>();
}
