package com.nappkin.resto.routes

import com.nappkin.resto.service.RestaurantHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.RequestPredicates.GET
import org.springframework.web.reactive.function.server.RequestPredicates.accept
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions.route


@Configuration
class Routes {

    @Autowired
    lateinit var restaurantHandler: RestaurantHandler

    @Bean
    fun routerFunction(): RouterFunction<*> {
        return route(GET("/api/restaurant").and(accept(APPLICATION_JSON)),
                restaurantHandler::listRestaurants )

                .and(route(GET("/api/restaurant/{id}").and(accept(APPLICATION_JSON)),
                        restaurantHandler::getRestaurant ))
    }

}