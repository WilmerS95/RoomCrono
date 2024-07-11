package com.wilmer.roomcrono.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.wilmer.roomcrono.model.cronos.CronoModel
import kotlinx.coroutines.flow.Flow

//Interface donde definimos metodos -> Repositorio (tomamos metodos de interface) -> View Model -> View

@Dao //Data Access Observer
interface CronosDataBaseDao {

    //CRUD (CREATE, READ, UPDATE, DELETE0
    @Query("SELECT * FROM cronoModel")
    fun getCronos(): Flow<List<CronoModel>>

    @Query("SELECT * FROM cronoModel WHERE id = :id")
    fun getCronosById(id: Long) : Flow<CronoModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cronoModel: CronoModel)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(cronoModel: CronoModel)

    @Delete
    suspend fun delete(cronoModel: CronoModel)
}