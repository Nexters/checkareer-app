package com.nexters.checkareer.presentation.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.nexters.checkareer.databinding.HomeFrag3Binding
import com.nexters.checkareer.domain.vo.Profile
import com.nexters.checkareer.presentation.ui.friendprofile.FriendProfileActivity
import com.nexters.checkareer.presentation.ui.home.adapter.MySkillAdapter
import com.nexters.checkareer.presentation.ui.home.adapter.ProfileAdapter
import com.nexters.checkareer.presentation.ui.home.listener.FriendProfileListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment3 : Fragment(), FriendProfileListener {

    private val viewModel by viewModels<HomeViewModel>()

    private lateinit var viewDataBinding: HomeFrag3Binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = HomeFrag3Binding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        } 
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLifecycleOwner()
        setupFriendProfileAdapter()
    }

    private fun setupLifecycleOwner() {
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
    }

    private fun setupFriendProfileAdapter() {
        viewDataBinding.recyclerviewFriendProfile.apply {
            adapter = ProfileAdapter(this@HomeFragment3)
        }
    }

    override fun onProfileClicked(item: Profile, view: View) {
        println("clicked")
        startActivity(Intent(requireContext(), FriendProfileActivity::class.java))
    }


}