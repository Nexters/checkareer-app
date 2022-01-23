package com.nexters.checkareer.presentation.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.nexters.checkareer.databinding.HomeFragBinding
import com.nexters.checkareer.presentation.ui.home.adapter.HomeAdapter
import com.nexters.checkareer.presentation.ui.home.listener.HomeListener
import com.nexters.checkareer.presentation.ui.home.model.MyProfile
import com.nexters.checkareer.presentation.ui.home.model.OtherProfile
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), HomeListener {

    private val viewModel by viewModels<HomeViewModel>()

    private lateinit var viewDataBinding: HomeFragBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = HomeFragBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupLifecycleOwner()
        setupListAdapter()
    }

    private fun setupListAdapter() {
        viewDataBinding.homeRecyclerview.apply {
            adapter = HomeAdapter(this@HomeFragment)
        }
    }

    private fun setupLifecycleOwner() {
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
    }

    override fun onMyProfileClicked(item: MyProfile) {

    }

    override fun onOtherProfileClicked(item: OtherProfile) {

    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}