package com.nexters.checkareer.presentation.ui.editprofile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexters.checkareer.domain.skill.Skill
import com.nexters.checkareer.domain.usecase.*
import com.nexters.checkareer.domain.util.getValue
import com.nexters.checkareer.domain.util.succeeded
import com.nexters.checkareer.domain.vo.Profile
import com.nexters.checkareer.presentation.ui.createprofile.model.CategorySelect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val editProfileUseCase: EditProfileUseCase
) : ViewModel() {

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _editProfileLoading = MutableLiveData<Boolean>()
    val editProfileLoading: LiveData<Boolean> = _editProfileLoading


    private val _profile = MutableLiveData<Profile?>()
    val profile: LiveData<Profile?> = _profile


    init {

    }


    fun removeSelectedSkillCategoryItem(skill: Skill) {
        val skillList = _profile.value?.skills?.toMutableList()
        skillList?.remove(skill)
        _profile.value?.skills = skillList?.toList()!!
        _profile.notifyObserver()
    }

    fun addSelectedSkillCategoryItem(skillList: List<Skill>) {
        val prevSkillList = _profile.value?.skills?.toMutableList()
        prevSkillList?.addAll(skillList)
        if (prevSkillList != null) {
            _profile.value?.skills = prevSkillList.toList()
        }
        _profile.notifyObserver()
    }

    fun loadHomes(forceUpdate: Boolean) {
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

    fun editUserProfile(userName: String) {
        try {
            _editProfileLoading.value = true
            profile.value?.user?.name = userName
            viewModelScope.launch {
                if (editProfileUseCase.invoke(_profile.value!!).succeeded) {
                    _editProfileLoading.value = false
                } else {
                    _editProfileLoading.value = false
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun <T> MutableLiveData<T>.notifyObserver() {
        this.value = this.value
    }

}