package com.nexters.checkareer.presentation.ui.splash

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.nexters.checkareer.databinding.SplashFragBinding
import com.nexters.checkareer.presentation.ui.home.HomeActivity
import com.nexters.checkareer.presentation.ui.onboarding.OnBoardingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private val viewModel by viewModels<SplashViewModel>()

    private lateinit var viewDataBinding: SplashFragBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = SplashFragBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEvents()
    }

    private fun setupEvents() {
        viewModel.isFirst.observe(viewLifecycleOwner) {
            if (it) {
                startActivity(Intent(requireContext(), OnBoardingActivity::class.java))
            } else {
                startActivity(Intent(requireContext(), HomeActivity::class.java))
            }
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = SplashFragment()
    }

}