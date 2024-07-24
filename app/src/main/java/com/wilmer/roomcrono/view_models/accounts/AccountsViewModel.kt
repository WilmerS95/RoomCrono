package com.wilmer.roomcrono.view_models.accounts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wilmer.roomcrono.model.accounts.AccountsModel
import com.wilmer.roomcrono.repositories.accounts.AccountsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AccountsViewModel @Inject constructor(private val repository: AccountsRepository): ViewModel() {
    private val _accounts = MutableStateFlow<List<AccountsModel>>(emptyList())
    val accounts = _accounts.asStateFlow()

    private val _accountsById = MutableStateFlow<List<AccountsModel>>(emptyList())
    val accountsById = _accountsById.asStateFlow()

    private val _title = MutableLiveData("--- Crono App ---")
    val title: LiveData<String> get() = _title

    private val _showBackButton = MutableLiveData(false)
    val showBackButton: LiveData<Boolean> get() = _showBackButton

    init {
        fetchAccounts()
    }

    private fun fetchAccounts() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.getAllAccounts()?.let {
                    _accounts.value = it
                }
            }
        }
    }

    fun getAccountById(id: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.getAccountById(id)?.let {
                    _accountsById.value = it
                    withContext(Dispatchers.Main) {
                        it.first().usuario.nombre?.let { it1 -> updateTitleWithUser(it1) }
                    }
                }
            }
        }
    }

    private fun updateTitleWithUser(nombre: String) {
        _title.value = "--- Cuentas de $nombre ---"
    }


    fun updateTitle(newTitle: String) {
        _title.value = newTitle
    }

    fun updateShowBackButton(show: Boolean) {
        _showBackButton.value = show
    }
}
