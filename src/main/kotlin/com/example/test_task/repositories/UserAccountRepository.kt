package com.example.test_task.repositories

import com.example.test_task.models.UserAccount
import org.springframework.data.jpa.repository.JpaRepository

interface UserAccountRepository : JpaRepository<UserAccount, Long> {
    fun findByIdOrThrow(id: Long): UserAccount = findById(id).orElseThrow { IllegalArgumentException("Unknown id: $id") }
}