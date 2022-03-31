package com.nexters.checkareer.presentation.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexters.checkareer.domain.usecase.LogoutUseCase
import com.nexters.checkareer.domain.usecase.GetUserUseCase
import com.nexters.checkareer.domain.util.getValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val deleteProfileUseCase: LogoutUseCase
): ViewModel(){

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _isMember = MutableLiveData<Boolean>().apply { value = false }
    val isMember: LiveData<Boolean> = _isMember

    private val _deletedUser = MutableLiveData<Boolean>()
    val deletedUser: LiveData<Boolean> = _deletedUser

    fun checkUser() {
        viewModelScope.launch {
            try {
                _dataLoading.value = true
                getUserUseCase().getValue()?.run {
                    _isMember.value = this.isMember
                }?:run {
                    _isMember.value = false
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _dataLoading.value = false
            }
        }
    }

    fun logout(){
        deleteUserProfile()
    }

    private fun deleteUserProfile() {
        viewModelScope.launch {
            try {
                deleteProfileUseCase().getValue().run {
                    _deletedUser.value = true
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}