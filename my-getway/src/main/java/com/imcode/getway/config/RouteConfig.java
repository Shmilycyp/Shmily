package com.imcode.getway.config;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.listener.AbstractListener;
import com.alibaba.nacos.api.exception.NacosException;
import com.imcode.getway.entity.GatewayRouteList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.annotation.Configuration;
import org.yaml.snakeyaml.Yaml;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;


/**
 * @author shmilyah@163.com
 * @date 2022-08-29
 */
@Configuration
@Slf4j
public class RouteConfig {


    private static final String DATA_ID = "dynamic_routes";
    private static final String GROUP = "gateway";
    @Resource
    private RouteDefinitionWriter routeDefinitionWriter;
    @Resource
    private NacosConfigManager nacosConfigManager;

    @PostConstruct
    public void init() throws NacosException {
        String config = nacosConfigManager.getConfigService().getConfig(DATA_ID, GROUP, 5000);
        this.updateRoute(config);
        nacosConfigManager.getConfigService().addListener(DATA_ID, GROUP, new AbstractListener() {
            @Override
            public void receiveConfigInfo(String configInfo) {
                log.info("get change info {}", configInfo);
                updateRoute(configInfo);
            }
        });
    }

    private void updateRoute(String configInfo) {
        GatewayRouteList gatewayRouteList = new Yaml().loadAs(configInfo, GatewayRouteList.class);
        gatewayRouteList.getRoutes().forEach(reoute -> {
            log.info("load config {}", reoute);
            routeDefinitionWriter.save(Mono.just(reoute)).subscribe();
        });
    }


}
