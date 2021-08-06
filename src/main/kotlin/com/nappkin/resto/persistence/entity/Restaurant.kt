package com.nappkin.resto.persistence.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table
data class Restaurant(
        @Id var id: Long?,
        var name: String,
        var description: String,
        var address: String,
        var longitude: Double,
        var latitude: Double)


