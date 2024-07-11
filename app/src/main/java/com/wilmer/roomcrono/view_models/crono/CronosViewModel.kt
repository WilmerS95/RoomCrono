package com.wilmer.roomcrono.view_models.crono

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wilmer.roomcrono.model.cronos.CronoModel
import com.wilmer.roomcrono.repositories.crono.CronosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CronosViewModel @Inject constructor(private val repository: CronosRepository) : ViewModel() {

    private val _cronosList = MutableStateFlow<List<CronoModel>>(emptyList())
    val cronosList = _cronosList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllCronos().collect { item ->
                if (item.isNullOrEmpty()) {
                    _cronosList.value = emptyList()
                } else {
                    _cronosList.value = item
                }
            }
        }
    }

    fun addCrono(cronoModel: CronoModel) = viewModelScope.launch { repository.addCrono(cronoModel) }
    fun updateCrono(cronoModel: CronoModel) =
        viewModelScope.launch { repository.updateCrono(cronoModel) }

    fun deleteCrono(cronoModel: CronoModel) =
        viewModelScope.launch { repository.deleteCrono(cronoModel) }
}