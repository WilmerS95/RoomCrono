package com.wilmer.roomcrono.model.accounts

data class AccountsModel(
    var id : Int,
    var nombre : String,
    var cuenta : Int,
    var saldo : Double,
    var favorita : Boolean,
    var usuario : UserModel,
    var tipoCuenta : AccountTypeModel
)
