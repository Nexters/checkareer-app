package com.nexters.checkareer.presentation.ui.createprofile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexters.checkareer.R
import com.nexters.checkareer.domain.category.Category
import com.nexters.checkareer.domain.usecase.GetSkillCategoryUseCase
import com.nexters.checkareer.domain.util.getValue
import com.nexters.checkareer.presentation.ui.createprofile.model.CategorySelect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateProfile2ViewModel @Inject constructor(
) : ViewModel() {

    private val _selectedSkillCategoryItems = MutableLiveData<MutableList<CategorySelect>>()
    val selectedSkillCategoryItems = _selectedSkillCategoryItems

}