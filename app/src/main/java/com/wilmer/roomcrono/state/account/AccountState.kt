package com.wilmer.roomcrono.state.account

import com.wilmer.roomcrono.model.accounts.AccountTypeModel
import com.wilmer.roomcrono.model.accounts.UserModel

data class AccountState(
    var nombre : String = "",
    var cuenta : Int = 0,
    var saldo : Double = 0.0,
    var favorita : Boolean = false,
    var usuario : UserModel? = null,
    var tipoCuenta : AccountTypeModel? = null
)
