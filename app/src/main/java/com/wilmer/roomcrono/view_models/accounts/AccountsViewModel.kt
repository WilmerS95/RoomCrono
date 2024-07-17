package com.wilmer.roomcrono.view_models.accounts

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wilmer.roomcrono.model.accounts.AccountsModel
import com.wilmer.roomcrono.repositories.accounts.AccountsRepository
import com.wilmer.roomcrono.state.account.AccountState
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

    init {
        fetchAccounts()
    }

    fun fetchAccounts() {
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
                }
            }
        }
    }
}