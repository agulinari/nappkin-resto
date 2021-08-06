package com.nappkin.resto.service

import com.nappkin.resto.persistence.RestaurantRepository
import com.nappkin.resto.persistence.entity.Restaurant
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.BodyInserters.fromValue
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Service
class RestaurantHandler {

    @Autowired
    lateinit var repository: RestaurantRepository

    fun getRestaurant(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toLong()!!
        val notFound = ServerResponse.notFound().build()
        val restaurantMono = this.repository.findById(id)
        return restaurantMono
                .flatMap { restaurant -> ServerResponse.ok()
                        .contentType(APPLICATION_JSON)
                        .body(fromValue(restaurant)) }
                .switchIfEmpty(notFound)
    }

    fun createRestaurant(request: ServerRequest): Mono<ServerResponse> {
        val name = request.pathVariable("name")!!
        val description = request.pathVariable("description")!!
        val address = request.pathVariable("address")!!
        val latitude = request.pathVariable("latitude").toDouble()!!
        val longitude = request.pathVariable("longitude").toDouble()!!

        val restaurant = Restaurant(null, name, description, address, longitude, latitude)
        return ServerResponse.ok()
                    .contentType(APPLICATION_JSON).build(this.repository.save(restaurant).thenEmpty(Mono.empty()))

    }

    fun listRestaurants(request: ServerRequest): Mono<ServerResponse> {
        val restaurants = this.repository.findAll()
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(restaurants, Restaurant::class.java)
    }

}

