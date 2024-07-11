package com.wilmer.roomcrono.repositories.crono

import com.wilmer.roomcrono.model.cronos.CronoModel
import com.wilmer.roomcrono.room.CronosDataBaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CronosRepository @Inject constructor(private val cronosDataBaseDao: CronosDataBaseDao) {

    suspend fun addCrono(cronoModel: CronoModel) = cronosDataBaseDao.insert(cronoModel)

    suspend fun updateCrono(cronoModel: CronoModel) = cronosDataBaseDao.update(cronoModel)

    suspend fun deleteCrono(cronoModel: CronoModel) = cronosDataBaseDao.delete(cronoModel)

    fun getAllCronos(): Flow<List<CronoModel>> = cronosDataBaseDao.getCronos().flowOn(Dispatchers.IO).conflate()
    fun getCronoById(id : Long): Flow<CronoModel> = cronosDataBaseDao.getCronosById(id).flowOn(Dispatchers.IO).conflate()

}