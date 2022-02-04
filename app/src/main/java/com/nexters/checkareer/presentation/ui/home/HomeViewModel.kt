package com.nexters.checkareer.presentation.ui.home

import androidx.lifecycle.*
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
    val getProfileUseCase: GetProfileUseCase
): ViewModel() {

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _items = MutableLiveData<List<Home>>()
    val items: LiveData<List<Home>> = _items

    init {
        loadHomes(true)
    }

    fun loadHomes(forceUpdate: Boolean) {
        try {
            _dataLoading.value = true
            viewModelScope.launch {
                getProfileUseCase(forceUpdate).getValue().run {
                    _items.value = this.toHomes()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            _dataLoading.value = false
        }
    }
}