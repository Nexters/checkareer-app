package com.nexters.checkareer.presentation.ui.settings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.nexters.checkareer.databinding.SettingFragBinding
import com.nexters.checkareer.presentation.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : Fragment() {

    private val viewModel by viewModels<SettingViewModel>()

    private lateinit var viewDataBinding: SettingFragBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = SettingFragBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupCloseButton()
        setupLoginButton()
    }

    private fun setupCloseButton() {
        viewDataBinding.imageviewClose.setOnClickListener {
            requireActivity().finish()
        }
    }

    private fun setupLoginButton() {
        viewDataBinding.textviewLogin.setOnClickListener {
            startActivity(Intent(requireContext(), LoginActivity::class.java))
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = SettingFragment()
    }

}