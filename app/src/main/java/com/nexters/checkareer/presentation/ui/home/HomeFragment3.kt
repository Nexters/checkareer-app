package com.nexters.checkareer.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nexters.checkareer.databinding.HomeFrag3Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment3 : Fragment() {

    private lateinit var viewDataBinding: HomeFrag3Binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = HomeFrag3Binding.inflate(inflater, container, false).apply {

        } 
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}