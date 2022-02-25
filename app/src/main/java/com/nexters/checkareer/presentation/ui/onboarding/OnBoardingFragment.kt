package com.nexters.checkareer.presentation.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.nexters.checkareer.databinding.OnboardingFragBinding
import com.nexters.checkareer.presentation.ui.createprofile.CreateProfileActivity
import com.nexters.checkareer.presentation.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingFragment : Fragment() {

    private val viewModel by viewModels<OnBoardingViewModel>()

    private lateinit var viewDataBinding: OnboardingFragBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = OnboardingFragBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupCreateProfileButton()
        setupLoginButton()
    }

    private fun setupCreateProfileButton() {
        viewDataBinding.buttonCreateProfile.setOnClickListener {
            startActivity(Intent(requireContext(), CreateProfileActivity::class.java))
        }
    }
    private fun setupLoginButton() {
        viewDataBinding.loginButton.setOnClickListener {
            startActivity(Intent(requireContext(), LoginActivity::class.java))
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = OnBoardingFragment()
    }


}