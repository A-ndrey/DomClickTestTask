package com.example.test_task.controllers

import com.example.test_task.services.UserAccountService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.math.BigDecimal

@RestController
@RequestMapping("/api")
class MoneyProcessingController(private val userAccountService: UserAccountService) {

    @PostMapping("/deposit")
    fun deposit(@RequestParam id: Long, @RequestParam amount: BigDecimal) {
        try {
            userAccountService.depositMoney(id, amount)
        } catch (ex: IllegalArgumentException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, ex.message)
        }
    }

    @PostMapping("/withdraw")
    fun withdraw(@RequestParam id: Long, @RequestParam amount: BigDecimal) {
        try {
            userAccountService.withdrawMoney(id, amount)
        } catch (ex: IllegalArgumentException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, ex.message)
        }
    }

    @PostMapping("/transfer")
    fun transfer(@RequestParam idFrom: Long, @RequestParam idTo: Long, @RequestParam amount: BigDecimal) {
        try {
            userAccountService.transferMoney(idFrom, idTo, amount)
        } catch (ex: IllegalArgumentException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, ex.message)
        }
    }
}