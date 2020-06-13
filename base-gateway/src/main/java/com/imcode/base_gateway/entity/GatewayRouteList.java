package com.imcode.base_gateway.entity;

import lombok.Data;
import org.springframework.cloud.gateway.route.RouteDefinition;

import java.util.List;

/**
 * 路由定义模型
 *
 */
@Data
public class GatewayRouteList {
private static final long serialVersionUID = 1L;

	List<RouteDefinition> routes;
  
}
