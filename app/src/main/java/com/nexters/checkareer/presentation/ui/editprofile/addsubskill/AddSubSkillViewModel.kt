package com.nexters.checkareer.presentation.ui.editprofile.addsubskill

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexters.checkareer.domain.skill.Skill
import com.nexters.checkareer.domain.usecase.GetAllSkillUseCase
import com.nexters.checkareer.domain.usecase.GetProfileUseCase
import com.nexters.checkareer.domain.util.getValue
import com.nexters.checkareer.domain.vo.SkillTree
import com.nexters.checkareer.presentation.ui.createprofile.model.CategorySelect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AddSubSkillViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val getAllSkillUseCase: GetAllSkillUseCase
) : ViewModel() {

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _items = MutableLiveData<List<CategorySelect>>().apply { value = emptyList() }
    val items: LiveData<List<CategorySelect>> = _items

    private val _selectedSkills = MutableLiveData<List<CategorySelect>>().apply { value = emptyList() }
    val selectedSkills: LiveData<List<CategorySelect>> = _selectedSkills

    var parentSkillId: Int? = null

    fun toggleSkillItemSelected(skillCategory: CategorySelect) {
        try {
            skillCategory.copy(selected = !skillCategory.selected).let { result ->
                if (result.selected) {
                    addSelectedSkill(result)
                } else {
                    removeSelectedSkill(skillCategory)
                }
                items.value?.let {
                    val position = it.indexOf(skillCategory)
                    Timber.i("position $position")
                    _items.value = it.toMutableList().apply { this[position] = result }
                }
            }
        } catch (e:Exception) {
            e.printStackTrace()
        }
    }

    private fun addSelectedSkill(skillCategory: CategorySelect) {
        selectedSkills.value?.let {
            _selectedSkills.value = it.toMutableList().apply { add(skillCategory) }
        }
    }

    private fun addSelectedSkills(skills: List<CategorySelect>) {
        selectedSkills.value?.let {
            _selectedSkills.value = it.toMutableList().apply { addAll(skills) }
        }
    }

    private fun removeSelectedSkill(skillCategory: CategorySelect) {
        selectedSkills.value?.let {
            _selectedSkills.value = it.toMutableList().apply { remove(skillCategory) }
        }
    }

    fun removeSkillItemSelected(skillCategory: CategorySelect) {
        skillCategory.copy(selected = false).let { result ->
            removeSelectedSkill(skillCategory)
            items.value?.let {
                val position = it.indexOf(skillCategory)
                _items.value = it.toMutableList().apply { this[position] = result }
            }
        }
    }

    fun loadSkillCategories(originSkillTree: List<SkillTree>, parentSkillId: Int) {
        try {
            _dataLoading.value = true
            this.parentSkillId = parentSkillId
            viewModelScope.launch {

                getProfileUseCase().getValue()?.let { profile ->
                    profile.skills.find { it.skill.id == parentSkillId.toString() }?.let {
                        val mySkills = it.childSkills.map { it.id to it }.toMap()

                        getAllSkillUseCase().getValue().find { it.skill.id == parentSkillId.toString() }?.run {
                            _items.value = this.childSkills.map { skill ->
                                if(mySkills[skill.id] != null)
                                    CategorySelect(skill.id, skill.name, skill.parentId, skill.layer, true)
                                else
                                    CategorySelect(skill.id, skill.name, skill.parentId, skill.layer, false)
                            }
                            items.value?.filter { it.selected }?.run { addSelectedSkills(this) }
                        }
                    }
                }

            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            _dataLoading.value = false
        }
    }
}