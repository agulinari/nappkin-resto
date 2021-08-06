package com.nappkin.resto.persistence

import com.nappkin.resto.persistence.entity.Menu
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface MenuRepository: ReactiveCrudRepository<Menu, Long> {
    @Query("select m.* from menu m where m.restaurant_id = :restaurantId ")
    fun findByRestaurantId(restaurantId: Long): Flux<Menu>
}