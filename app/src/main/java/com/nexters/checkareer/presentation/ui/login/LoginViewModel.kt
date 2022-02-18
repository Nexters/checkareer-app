package com.nexters.checkareer.presentation.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexters.checkareer.domain.usecase.SignInUseCase
import com.nexters.checkareer.domain.util.getValue
import com.nexters.checkareer.presentation.ui.login.model.Account
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : ViewModel() {

    private val _isProgressing = MutableLiveData<Boolean>().apply { value = false }
    val isProgressing: LiveData<Boolean> = _isProgressing

    private val _isSucceededLogin = MutableLiveData<Boolean>()
    val isSucceededLogin: LiveData<Boolean> = _isSucceededLogin

    fun signIn(account: Account) {
        viewModelScope.launch {
            try {
                _isProgressing.value = true
                signInUseCase(account.toDomain()).getValue().run {
                    _isSucceededLogin.value = true
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _isSucceededLogin.value = false
            } finally {
                _isProgressing.value = false
            }
        }
    }
}