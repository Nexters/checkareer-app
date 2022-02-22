package com.nexters.checkareer.presentation.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexters.checkareer.domain.usecase.GetProfileUseCase
import com.nexters.checkareer.domain.usecase.GetSkillCategoryUseCase
import com.nexters.checkareer.domain.usecase.SaveSkillAllUseCase
import com.nexters.checkareer.domain.util.getValue
import com.nexters.checkareer.domain.vo.Profile
import com.nexters.checkareer.presentation.ui.createprofile.model.CategorySelect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val getSkillCategoryUseCase: GetSkillCategoryUseCase,
    private val saveSkillAllUseCase: SaveSkillAllUseCase
): ViewModel() {

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _profile = MutableLiveData<Profile?>()
    val profile: LiveData<Profile?> = _profile


    init {
        loadSkillCategories(true)
    }

    private fun loadSkillCategories(forceUpdate: Boolean) {
        try {
            _dataLoading.value = true
            viewModelScope.launch {
                getSkillCategoryUseCase(forceUpdate).getValue().run {
                    saveSkillAllUseCase.invoke(this)
                    loadHomes(true)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            _dataLoading.value = false
        }
    }

    private fun loadHomes(forceUpdate: Boolean) {
        try {
            _dataLoading.value = true
            viewModelScope.launch {
                getProfileUseCase(forceUpdate).getValue().run {
                    _profile.value = this
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            _dataLoading.value = false
        }
    }
}