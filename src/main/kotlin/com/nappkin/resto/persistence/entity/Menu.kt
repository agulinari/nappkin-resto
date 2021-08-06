package com.nappkin.resto.persistence.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table
data class Menu(
        @Id var id: Long?,
        var restaurantId: Long?,
        var description: String
        )