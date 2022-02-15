package com.nexters.checkareer.presentation.ui.home

import androidx.lifecycle.*
import com.nexters.checkareer.data.adapter.db.data.SkillData
import com.nexters.checkareer.domain.skill.Skill
import com.nexters.checkareer.domain.usecase.GetProfileUseCase
import com.nexters.checkareer.domain.user.UserRepository
import com.nexters.checkareer.domain.util.getValue
import com.nexters.checkareer.presentation.ui.home.mapper.toHomes
import com.nexters.checkareer.presentation.ui.home.model.Home
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase
) : ViewModel() {

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _items = MutableLiveData<List<Home>>()
    val items: LiveData<List<Home>> = _items

    private val _skills = MutableLiveData<List<Skill>>()
    val skills: LiveData<List<Skill>> = _skills

    init {
        _skills.value = listOf(Skill("1", "Android"))
        loadHomes(true)
    }

    private fun loadHomes(forceUpdate: Boolean) {
        try {
            _dataLoading.value = true
            viewModelScope.launch {
                getProfileUseCase(forceUpdate).getValue().run {
                    _skills.value = this.skills
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            _dataLoading.value = false
        }
    }
}