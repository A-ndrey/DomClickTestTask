package com.example.test_task.controllers

import com.example.test_task.services.UserAccountService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
@RequestMapping("/api")
class MoneyProcessingController(private val userAccountService: UserAccountService) {

    @PostMapping("/deposit")
    fun deposit(@RequestParam id: Long, @RequestParam amount: BigDecimal) =
            userAccountService.depositMoney(id, amount)

    @PostMapping("/withdraw")
    fun withdraw(@RequestParam id: Long, @RequestParam amount: BigDecimal) =
            userAccountService.withdrawMoney(id, amount)

    @PostMapping("/transfer")
    fun transfer(@RequestParam idFrom: Long, @RequestParam idTo: Long, @RequestParam amount: BigDecimal) =
            userAccountService.transferMoney(idFrom, idTo, amount)
}