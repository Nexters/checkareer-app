package com.nexters.checkareer.presentation.ui.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexters.checkareer.domain.usecase.GetUserUseCase
import com.nexters.checkareer.domain.util.getValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
): ViewModel() {

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _isExistUser = MutableLiveData<Boolean>()
    val isExistUser: LiveData<Boolean> = _isExistUser

    fun checkUser() {
        viewModelScope.launch {
            try {
                _dataLoading.value = true
                getUserUseCase().getValue()?.run {
                    _isExistUser.value = false
                } ?: run {
                    _isExistUser.value = true
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _dataLoading.value = false
            }
        }
    }
}