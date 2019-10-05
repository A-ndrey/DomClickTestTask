package com.example.test_task.models

import java.math.BigDecimal
import javax.persistence.*

@Entity
data class UserAccount(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,

        @Column(nullable = false)
        val name: String,

        @Column(nullable = false)
        var amount: BigDecimal = BigDecimal.ZERO
)