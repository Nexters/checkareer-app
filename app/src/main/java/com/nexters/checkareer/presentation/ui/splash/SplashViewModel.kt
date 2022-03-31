package com.nexters.checkareer.presentation.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexters.checkareer.domain.usecase.GetUserUseCase
import com.nexters.checkareer.domain.usecase.SyncSkillsUseCase
import com.nexters.checkareer.domain.util.getValue
import com.nexters.checkareer.domain.vo.Profile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val syncSkillsUseCase: SyncSkillsUseCase,
): ViewModel() {

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _profile = MutableLiveData<Profile?>()
    val profile: LiveData<Profile?> = _profile

    private val _isFirst = MutableLiveData<Boolean>()
    val isFirst: LiveData<Boolean> = _isFirst

    init {
        sync()
    }

    private fun sync() {
        viewModelScope.launch {
            try {
                _dataLoading.value = true
                syncSkillsUseCase().getValue()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _dataLoading.value = false
                checkUser()
            }
        }
    }

    private fun checkUser() {
        viewModelScope.launch {
            try {
                _dataLoading.value = true
                getUserUseCase().getValue()?.run {
                    _isFirst.value = false
                }?:run {
                    _isFirst.value = true
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _dataLoading.value = false
            }
        }
    }
}