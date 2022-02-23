package com.nexters.checkareer.presentation.ui.createprofile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexters.checkareer.domain.usecase.GetAllSkillUseCase
import com.nexters.checkareer.domain.usecase.GetSkillCategoryUseCase
import com.nexters.checkareer.domain.util.getValue
import com.nexters.checkareer.presentation.ui.createprofile.model.CategorySelect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateProfile1ViewModel @Inject constructor(
    val getAllSkillUseCase: GetAllSkillUseCase
) : ViewModel() {


    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _items = MutableLiveData<List<CategorySelect>>()
    val items: LiveData<List<CategorySelect>> = _items

    private val _selectedSkillCategoryItems = MutableLiveData<MutableList<CategorySelect>>()
    val selectedSkillCategoryItems = _selectedSkillCategoryItems


    init {
        loadSkillCategories(true)
        _selectedSkillCategoryItems.value = mutableListOf()
    }

    fun addSelectedSkillCategoryItem(skillCategory: CategorySelect) {
        _selectedSkillCategoryItems.value?.add(skillCategory)
        _selectedSkillCategoryItems.notifyObserver()
        _items.notifyObserver()
    }


    fun removeSelectedSkillCategoryItem(skillCategory: CategorySelect) {
        _selectedSkillCategoryItems.value?.remove(skillCategory)
        _selectedSkillCategoryItems.notifyObserver()
        _items.notifyObserver()
    }

    private fun loadSkillCategories(forceUpdate: Boolean) {
        try {
            _dataLoading.value = true
            viewModelScope.launch {
                getAllSkillUseCase(forceUpdate).getValue().run {
                    _items.value = this.map { CategorySelect(it.id, it.name, it.parentId) }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            _dataLoading.value = false
        }
    }


    fun <T> MutableLiveData<T>.notifyObserver() {
        this.value = this.value
    }
}