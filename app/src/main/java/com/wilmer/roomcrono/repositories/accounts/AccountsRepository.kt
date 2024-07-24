package com.wilmer.roomcrono.repositories.accounts

import com.wilmer.roomcrono.model.accounts.AccountsModel
import com.wilmer.roomcrono.services.account.AccountApiService
import javax.inject.Inject


class AccountsRepository @Inject constructor(private val accountApiService: AccountApiService) {

    suspend fun getAllAccounts(): List<AccountsModel>? {
        val response = accountApiService.getAllAccounts()
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }

    suspend fun getAccountById(id: Int) : List<AccountsModel>? {
        val response = accountApiService.getAccountById(id)
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}
