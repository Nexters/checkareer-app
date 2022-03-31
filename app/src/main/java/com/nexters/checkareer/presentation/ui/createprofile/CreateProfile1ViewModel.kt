package com.nexters.checkareer.presentation.ui.createprofile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexters.checkareer.domain.usecase.GetSkillsByLayerUseCase
import com.nexters.checkareer.domain.util.getValue
import com.nexters.checkareer.domain.vo.SkillLayer
import com.nexters.checkareer.presentation.ui.createprofile.model.CategorySelect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateProfile1ViewModel @Inject constructor(
    val getSkillsByLayerUseCase: GetSkillsByLayerUseCase
) : ViewModel() {


    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _items = MutableLiveData<List<CategorySelect>>().apply { value = emptyList() }
    val items: LiveData<List<CategorySelect>> = _items

    private val _selectedSkills = MutableLiveData<List<CategorySelect>>().apply { value = emptyList() }
    val selectedSkills: LiveData<List<CategorySelect>> = _selectedSkills


    init {
        loadSkillCategories()
    }

    fun toggleSkillItemSelected(skillCategory: CategorySelect) {
        try {
            skillCategory.copy(selected = !skillCategory.selected).let { result ->
                if (changeItem(skillCategory, result)) {
                    if (result.selected) {
                        addSelectedSkill(result)
                    } else {
                        removeSelectedSkill(skillCategory)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun changeItem(skillCategory: CategorySelect, result: CategorySelect): Boolean {
        items.value?.let {
            val position = it.indexOf(skillCategory)
            val isExist = position != -1
            if (isExist) {
                _items.value = it.toMutableList().apply { this[position] = result }
            }
            return isExist
        }
        return false
    }

    private fun addSelectedSkill(skillCategory: CategorySelect) {
        selectedSkills.value?.let {
            _selectedSkills.value = it.toMutableList().apply { add(skillCategory) }
        }
    }

    private fun removeSelectedSkill(skillCategory: CategorySelect) {
        selectedSkills.value?.let {
            _selectedSkills.value = it.toMutableList().apply { remove(skillCategory) }
        }
    }

    fun removeSkillItemSelected(skillCategory: CategorySelect) {
        skillCategory.copy(selected = false).let { result ->
            if (changeItem(skillCategory, result)) {
                removeSelectedSkill(skillCategory)
            }
        }
    }

    private fun loadSkillCategories() {
        viewModelScope.launch {
            try {
                _dataLoading.value = true
                    getSkillsByLayerUseCase(SkillLayer.PARENT).getValue().run {
                        _items.value = this.map { CategorySelect(it.id, it.name, it.parentId, it.layer) }
                    }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _dataLoading.value = false
            }
        }
    }


    fun <T> MutableLiveData<T>.notifyObserver() {
        this.value = this.value
    }
}