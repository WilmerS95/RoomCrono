package com.wilmer.roomcrono.model.cronos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Entidades = tabla, Atributo = campo
@Entity(tableName = "cronoModel")
data class CronoModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "crono")
    val crono: Long
)
