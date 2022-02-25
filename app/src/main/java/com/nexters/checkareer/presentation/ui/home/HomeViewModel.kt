package com.nexters.checkareer.presentation.ui.home

import androidx.lifecycle.*
import com.nexters.checkareer.domain.usecase.DeleteProfileUseCase
import com.nexters.checkareer.domain.usecase.GetProfileUseCase
import com.nexters.checkareer.domain.util.getValue
import com.nexters.checkareer.domain.vo.Profile
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val deleteProfileUseCase: DeleteProfileUseCase
) : ViewModel() {

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _profile = MutableLiveData<Profile>()
    val profile: LiveData<Profile> = _profile

    private val _friendProfiles = MutableLiveData<List<Profile>>()
    val friendProfiles: LiveData<List<Profile>> = _friendProfiles

    init {
        loadHomes(true)
    }

    fun loadHomes(forceUpdate: Boolean) {
        try {
            _dataLoading.value = true
            viewModelScope.launch {
                getProfileUseCase(forceUpdate).getValue()?.run {
                    _profile.value = this
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            _dataLoading.value = false
        }
    }

    /*fun deleteUserProfile() {
        try {
            viewModelScope.launch {
                profile.value?.let {
                    deleteProfileUseCase(it).getValue().run {
                        _profile.value = null
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }*/
}