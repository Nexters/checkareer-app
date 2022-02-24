package com.nexters.checkareer.presentation.ui.createprofile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexters.checkareer.domain.skill.Skill
import com.nexters.checkareer.domain.usecase.SaveProfileUseCase
import com.nexters.checkareer.domain.user.User
import com.nexters.checkareer.domain.util.getValue
import com.nexters.checkareer.domain.vo.Profile
import com.nexters.checkareer.domain.vo.SkillTree
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class CreateProfile2ViewModel @Inject constructor(
    private val saveProfileUseCase: SaveProfileUseCase
) : ViewModel() {

    private val _selectedSkillItems = MutableLiveData<List<Skill>>()
    val selectedSkillItems: LiveData<List<Skill>> = _selectedSkillItems

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _goHome = MutableLiveData<Unit>()
    val goHome: LiveData<Unit> = _goHome

    fun setSelectedSkillItems(categorySelect: List<Skill>) {
        _selectedSkillItems.value = categorySelect
    }

    fun setName(name: String) {
        _name.value = name
    }

    fun saveUserProfile() = viewModelScope.launch {
        try {
            name.value?.let { name ->
                selectedSkillItems.value?.let { skill ->
                    val profile = Profile(
                        User(name = name),
                        skill.map {
                            SkillTree(Skill(it.id, it.name, it.parentId))
                        }
                    )
                    saveProfileUseCase(profile).getValue()
                    _goHome.value = Unit
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}