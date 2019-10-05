package com.example.test_task.services

import com.example.test_task.models.UserAccount
import com.example.test_task.repositories.UserAccountRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class UserAccountService(private val userAccountRepository: UserAccountRepository) {

    fun depositMoney(id: Long, amount: BigDecimal) {
        val userAccount = userAccountRepository.findByIdOrThrow(id)
        userAccount.amount += amount
    }

    fun withdrawMoney(id: Long, amount: BigDecimal) {
        val userAccount = userAccountRepository.findByIdOrThrow(id)
        doIfEnoughMoney(userAccount, amount) { userAccount.amount -= amount }
    }

    fun transferMoney(idFrom: Long, idTo: Long, amount: BigDecimal) {
        val userAccountFrom = userAccountRepository.findByIdOrThrow(idFrom)
        val userAccountTo = userAccountRepository.findByIdOrThrow(idTo)
        doIfEnoughMoney(userAccountFrom, amount) {
            userAccountFrom.amount -= amount
            userAccountTo.amount += amount
        }
    }

    private inline fun doIfEnoughMoney(userAccount: UserAccount, amount: BigDecimal, block: () -> Unit) {
        if (userAccount.amount >= amount) {
            block()
        } else {
            throw RuntimeException("User with id: ${userAccount.id} haven't enough money.")
        }
    }
}