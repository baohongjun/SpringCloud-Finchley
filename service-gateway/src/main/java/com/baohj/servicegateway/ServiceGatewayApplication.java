package com.baohj.servicegateway;

import com.baohj.servicegateway.factory.RequestTimeGatewayFilterFactory;
import com.baohj.servicegateway.filter.RequestTimeFilter;
import com.baohj.servicegateway.filter.TokenFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author DELL
 * 1.Spring Cloud Gateway是Spring Cloud官方推出的第二代网关框架，取代Zuul网关
 * 网关常见的功能有路由转发、权限校验、限流控制等作用
 * 2.Hystrix是 spring cloud中一个服务熔断降级的组件
 *  Hystrix是 spring cloud gateway中是以filter的形式使用
 */
@SpringBootApplication
@RestController
@EnableDiscoveryClient  //注册
@EnableEurekaClient
public class ServiceGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceGatewayApplication.class, args);
    }

    /**
     * 1.使用RouteLocator的Bean进行路由转发，将请求进行处理，最后转发到目标的下游服务。
     *  在本案例中，会将请求转发到http://httpbin.org:80这个地址上
     *  predicates  断言，根据具体的请求的规则，由具体的route去处理
     *  filters是各种过滤器，用来对请求做各种判断和修改
     * @param builder
     * @return
     */
//   @Bean
////    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
////        //创建的route可以让请求“/get”请求都转发到“http://httpbin.org/get”。
////        // 在route配置上，添加了一个filter，该filter会将请求添加一个header,key为hello，value为world
////        String httpUri = "http://httpbin.org:80";
////        return builder.routes()
////                .route(p -> p
////                        .path("/get")
////                        .filters(f -> f.addRequestHeader("Hello", "World"))
////                        .uri(httpUri))
////                //使用另外一个router，该router使用host去断言请求是否进入该路由，
////                // 当请求的host有“*.hystrix.com”，都会进入该router
////                //该router中有一个hystrix的filter,该filter可以配置名称、和指向性fallback的逻辑的地址，
////                // 在本案例中重定向到了“/fallback”
////                .route(p -> p
////                        .host("*.hystrix.com")
////                        .filters(f -> f
////                                .hystrix(config -> config
////                                        .setName("mycmd")
////                                        .setFallbackUri("forward:/fallback")))
////                        .uri(httpUri))
////                .build();
////    }

//    @RequestMapping("/fallback")
//    public Mono<String> fallback() {
//        //Mono是一个Reactive stream，对外输出“fallback”字符串
//        return Mono.just("fallback");
//    }

    /**
     * 自定义过滤器时用法
     * @param builder
     * @return
     */
//    @Bean
//    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
//        // @formatter:off
//        //将RequestTimeFilter过滤器注册到router中
//        return builder.routes()
//                .route(r -> r.path("/customer/**")
//                        .filters(f -> f.filter(new RequestTimeFilter())
//                                .addResponseHeader("X-Response-Default-Foo", "Default-Bar"))
//                        .uri("http://httpbin.org:80/get")
//                        .order(0)
//                        .id("customer_filter_router")
//                )
//                .build();
//        // @formatter:on
//    }

    /**
     * 自定义过滤工厂，注入IOC容器
     * @return
     */
//    @Bean
//    public RequestTimeGatewayFilterFactory requestTimeGatewayFilterFactory() {
//        return new RequestTimeGatewayFilterFactory();
//    }

    /**
     * TokenFilter 注入 GlobalFilter
     * @return
     */
    @Bean
    public TokenFilter tokenFilter(){
        return new TokenFilter();
    }

}
