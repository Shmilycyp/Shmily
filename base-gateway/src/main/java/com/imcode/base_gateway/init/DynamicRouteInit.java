package com.imcode.base_gateway.init;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.imcode.base_gateway.config.CustomProperties;
import com.imcode.base_gateway.entity.GatewayRouteList;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.annotation.Configuration;
import org.yaml.snakeyaml.Yaml;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executor;

@Slf4j
@AllArgsConstructor
@Configuration
public class DynamicRouteInit {

    private RouteDefinitionWriter routeDefinitionWriter;

    private static final String DATA_ID = "dynamic_routes";

    private CustomProperties properties;


    @PostConstruct
    public void initRoute(){

        try {
            log.info("初始化网关路由开始!");
            ConfigService configService = NacosFactory.createConfigService(properties.getNacosAddr());
            String content = configService.getConfig(DATA_ID, "DEFAULT_GROUP", 5000);
            this.updateRoute(content);
            log.info("初始化网关路由结束!");
            // 开启监听
            configService.addListener(DATA_ID, "DEFAULT_GROUP", new Listener() {

                @Override
                public Executor getExecutor() {

                    return null;
                }
                @Override
                public void receiveConfigInfo(String configInfo) {
                    log.info("更新网关路由开始");
                    updateRoute(configInfo);
                    log.info("更新网关路由完成");
                }
            });
        }catch (Exception e){

            log.error("路由初始化失败!" , e);
        }
    }


    public void updateRoute(String content){

        Yaml yaml = new Yaml();
        GatewayRouteList gatewayRouteList = yaml.loadAs(content, GatewayRouteList.class);

        gatewayRouteList.getRoutes().forEach(routeDefinition -> {

            log.info("加载路由:{}{}" , routeDefinition.getId() , routeDefinition);
            routeDefinitionWriter.save(Mono.just(routeDefinition)).subscribe();
        });
    }


}























