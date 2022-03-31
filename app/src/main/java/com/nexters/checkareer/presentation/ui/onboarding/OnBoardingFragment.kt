package com.nexters.checkareer.presentation.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nexters.checkareer.databinding.OnboardingFragBinding
import com.nexters.checkareer.presentation.ui.createprofile.CreateProfileActivity
import com.nexters.checkareer.presentation.ui.home.HomeActivity
import com.nexters.checkareer.presentation.ui.login.LoginBottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingFragment : Fragment() {

    private val viewModel by viewModels<OnBoardingViewModel>()

    private lateinit var loginBottomSheet: BottomSheetDialogFragment
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
        setupEvents()
    }

    private fun setupEvents() {
        viewModel.isExistUser.observe(this.viewLifecycleOwner, Observer {
            if(it) {
                startActivity(Intent(requireContext(), HomeActivity::class.java))
            }
        })
    }

    private fun setupCreateProfileButton() {
        viewDataBinding.buttonCreateProfile.setOnClickListener {
            startActivity(Intent(requireContext(), CreateProfileActivity::class.java))
        }
    }
    private fun setupLoginButton() {
        viewDataBinding.loginButton.setOnClickListener {
            startActivity(Intent(requireContext(), HomeActivity::class.java))
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = OnBoardingFragment()
    }


}