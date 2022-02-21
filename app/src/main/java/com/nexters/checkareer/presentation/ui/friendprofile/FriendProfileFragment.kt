package com.nexters.checkareer.presentation.ui.friendprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.nexters.checkareer.databinding.FriendProfileFragBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FriendProfileFragment: Fragment() {

    private val viewModel by activityViewModels<FriendProfileViewModel>()
    private lateinit var viewDataBinding: FriendProfileFragBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = FriendProfileFragBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLifecycleOwner()
        setupBackButton()
    }

    private fun setupLifecycleOwner() {
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
    }

    private fun setupBackButton() {
        viewDataBinding.imageviewBack.setOnClickListener {
            requireActivity().finish()
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = FriendProfileFragment()
    }
}