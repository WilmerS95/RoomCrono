package com.wilmer.roomcrono.state.crono

data class CronoState(
    val cronometroActivo: Boolean = false,
    val showSaveButton: Boolean = false,
    val showTextField: Boolean = false,
    val title: String =""
)
