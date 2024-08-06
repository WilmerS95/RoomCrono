package com.wilmer.roomcrono.view_models.forms

import androidx.lifecycle.*
import com.thejuki.kformmaster.form_api.model.FormData
import com.wilmer.roomcrono.repositories.form.FormRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormViewModel @Inject constructor(private val formRepository: FormRepository) : ViewModel() {

    private val _formData = MutableLiveData<List<FormData>?>()
    val formData: LiveData<List<FormData>?> get() = _formData

    private val _error = MutableLiveData<Throwable?>()
    val error: LiveData<Throwable?> get() = _error

    init {
        fetchFormData()
    }

    private fun fetchFormData() {
        viewModelScope.launch {
            formRepository.getFormData { data, error ->
                if (error != null) {
                    _error.value = error
                } else {
                    _formData.value = data
                }
            }
        }
    }
}