package com.nappkin.resto.persistence

import com.nappkin.resto.persistence.entity.Restaurant
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RestaurantRepository: ReactiveCrudRepository<Restaurant, Long> {

}