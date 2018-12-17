package com.zxc.web.reactive.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * TODO...
 *
 * @author Zhou RunMing
 * @date 2018/12/15
 */
@SpringBootApplication
@RestController
public class WebFluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebFluxApplication.class, args);
    }

    @RequestMapping("/mvc")
    public String mvc() {
        println("MVC");
        return "MVC";
    }

    @RequestMapping("/monoMVC")
    public Mono<String> mono(ServerRequest serverRequest) {
        println("mono");
        Mono<String> mono = Mono.just("mono");
        mono.subscribe(supplier -> {
            println("supplier complete");
        }, errorSub -> {

        });
        return Mono.just("mono");
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
//        return RouterFunctions.route(serverRequest -> {
//            URI uri = serverRequest.uri();
//            return "/hello-world".equals(uri.getPath());
//        }, serverRequest -> {
//            Mono mono = ServerResponse.status(HttpStatus.OK)
//                    .body(Mono.just("hello world"), String.class);
//            return mono;
//        });
        return route(GET("/hello-world"), this::helloWrold);
    }

    public Mono<ServerResponse> helloWrold(ServerRequest serverRequest) {
        println("hello world mvc");
        Mono mono = ServerResponse.status(HttpStatus.OK)
                .body(Mono.just("hello world"), String.class);
        return mono;
    }

    private static void println(String message) {
        System.out.println("[" + Thread.currentThread().getName() + "]" + message);
    }

}
