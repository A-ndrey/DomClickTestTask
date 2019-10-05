package com.example.test_task.services

import com.example.test_task.models.UserAccount
import com.example.test_task.repositories.UserAccountRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal
import javax.transaction.Transactional

@Service
class UserAccountService(private val userAccountRepository: UserAccountRepository) {

    @Transactional
    fun depositMoney(id: Long, amount: BigDecimal) {
        checkPositive(amount)
        val userAccount = findByIdOrThrow(id)
        userAccount.amount += amount
    }

    @Transactional
    fun withdrawMoney(id: Long, amount: BigDecimal) {
        checkPositive(amount)
        val userAccount = findByIdOrThrow(id)
        doIfEnoughMoney(userAccount, amount) { userAccount.amount -= amount }
    }

    @Transactional
    fun transferMoney(idFrom: Long, idTo: Long, amount: BigDecimal) {
        checkPositive(amount)
        val userAccountFrom = findByIdOrThrow(idFrom)
        val userAccountTo = findByIdOrThrow(idTo)
        doIfEnoughMoney(userAccountFrom, amount) {
            userAccountFrom.amount -= amount
            userAccountTo.amount += amount
        }
    }

    private fun checkPositive(amount: BigDecimal) {
        if (amount <= BigDecimal.ZERO) throw IllegalArgumentException("Amount must be positive.")
    }

    private fun findByIdOrThrow(id: Long) =
            userAccountRepository.findById(id).orElseThrow { IllegalArgumentException("Unknown id: $id") }

    private inline fun doIfEnoughMoney(userAccount: UserAccount, amount: BigDecimal, block: () -> Unit) {
        if (userAccount.amount >= amount) {
            block()
        } else {
            throw IllegalArgumentException("User with id: ${userAccount.id} haven't enough money.")
        }
    }

}