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
import com.nexters.checkareer.domain.vo.SkillTree
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

    private val _clickedSkill = MutableLiveData<Skill>()
    val clickedSkill: LiveData<Skill> = _clickedSkill

    fun setClickedSkill(skill: Skill) {
        _clickedSkill.value = skill
    }

    fun removeSelectedSkillCategoryItem(skill: SkillTree) {
        profile.value?.skills?.toMutableList()?.let { originParentSkills ->
            originParentSkills.remove(skill)
            _profile.value?.skills = originParentSkills.toList()
            _profile.notifyObserver()
        }
    }

    fun applyParentSkills(skillList: List<Skill>) {
        profile.value?.skills?.let { parentSkills ->
            val originParentSkills = parentSkills.map { it.skill.id to it }.toMap()
            _profile.value?.skills = skillList.map {
                SkillTree(it, originParentSkills[it.id]?.childSkills?: emptyList())
            }
            _profile.notifyObserver()
        }
    }

    fun saveSubSkills(parentSkillId: Int, skillList: List<Skill>) {
        // 세부 스킬 갈아끼기
        profile.value?.let {
            it.skills.find { it.skill.id == parentSkillId.toString() }?.run {
                val position = it.skills.indexOf(this)
                val skillTrees = it.skills.toMutableList()
                skillTrees[position] = SkillTree(this.skill, skillList)
                skillTrees
            }?.run {
                _profile.value = Profile(it.user, this)
            }
        }
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
                if (editProfileUseCase.invoke(profile.value!!).succeeded) {
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