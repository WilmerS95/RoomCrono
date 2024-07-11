package com.wilmer.roomcrono.services.account

import com.wilmer.roomcrono.model.accounts.AccountsModel
import com.wilmer.roomcrono.util.Constants.Companion.ENDPOINT_ACCOUNTS
import com.wilmer.roomcrono.util.Constants.Companion.POR_USUARIO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface AccountApiService {

    @GET(ENDPOINT_ACCOUNTS)
    suspend fun getAllAccounts() : Response<List<AccountsModel>>

    @GET("$ENDPOINT_ACCOUNTS$POR_USUARIO{id}")
    suspend fun getAccountById(@Path("id") id: Int) : Response<List<AccountsModel>>

    @PUT("$ENDPOINT_ACCOUNTS/{id}")
    suspend fun updateCuentaFavorita(@Path("id") id: Int, @Body cuentasPropiasDTO: AccountsModel) : Response<AccountsModel>
}