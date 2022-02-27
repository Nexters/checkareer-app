package com.nexters.checkareer.presentation.ui.editprofile.skillorder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nexters.checkareer.domain.vo.Profile
import com.nexters.checkareer.domain.vo.SkillTree
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChangeSkillOrderViewModel @Inject constructor(

): ViewModel(){

    private val _skillList = MutableLiveData<List<SkillTree>>()
    val skillList: LiveData<List<SkillTree>> = _skillList

    private val _newSkillList = MutableLiveData<List<SkillTree>>()
    val newSkillList: LiveData<List<SkillTree>> = _newSkillList

    fun setSkillListOrder(skillList: List<SkillTree>) {
        _skillList.value = skillList
        _skillList.notifyObserver()
    }

    fun setNewSkillListOrder(skillList: List<SkillTree>) {
        _newSkillList.value = skillList
        _newSkillList.notifyObserver()
    }

    fun <T> MutableLiveData<T>.notifyObserver() {
        this.value = this.value
    }
}