package com.nexters.checkareer.presentation.ui.editprofile.addskill

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexters.checkareer.domain.usecase.GetAllSkillUseCase
import com.nexters.checkareer.domain.usecase.GetProfileUseCase
import com.nexters.checkareer.domain.util.getValue
import com.nexters.checkareer.domain.vo.Profile
import com.nexters.checkareer.domain.vo.SkillTree
import com.nexters.checkareer.presentation.ui.createprofile.model.CategorySelect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddSkillViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val getAllSkillUseCase: GetAllSkillUseCase
) : ViewModel() {

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _items = MutableLiveData<List<CategorySelect>>().apply { value = emptyList() }
    val items: LiveData<List<CategorySelect>> = _items

    private val _selectedSkills = MutableLiveData<List<CategorySelect>>().apply { value = emptyList() }
    val selectedSkills: LiveData<List<CategorySelect>> = _selectedSkills


    fun toggleSkillItemSelected(skillCategory: CategorySelect) {
        skillCategory.copy(selected = !skillCategory.selected).let { result ->
            if (result.selected) {
                addSelectedSkill(result)
            } else {
                removeSelectedSkill(skillCategory)
            }
            items.value?.let {
                val position = it.indexOf(skillCategory)
                _items.value = it.toMutableList().apply { this[position] = result }
            }
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

    fun loadSkillItems(originSkillTree: List<SkillTree>) {
        try {
            _dataLoading.value = true
            viewModelScope.launch {
                val myParentSkills = originSkillTree.map { it.skill.id to it.skill }.toMap()

                getAllSkillUseCase().getValue().run {
                    _items.value = this.map { skillTree ->
                        if(myParentSkills[skillTree.skill.id] != null)
                            CategorySelect(skillTree.skill.id, skillTree.skill.name, skillTree.skill.parentId, skillTree.skill.layer, true)
                        else
                            CategorySelect(skillTree.skill.id, skillTree.skill.name, skillTree.skill.parentId, skillTree.skill.layer, false)
                    }
                    items.value?.filter { it.selected }?.run { addSelectedSkills(this) }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            _dataLoading.value = false
        }
    }
}