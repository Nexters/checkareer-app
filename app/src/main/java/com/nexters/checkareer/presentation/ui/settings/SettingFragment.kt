package com.nexters.checkareer.presentation.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.nexters.checkareer.R
import com.nexters.checkareer.databinding.SettingFragBinding
import com.nexters.checkareer.presentation.ui.login.LoginBottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : Fragment() {

    private val viewModel by viewModels<SettingViewModel>()

    private lateinit var viewDataBinding: SettingFragBinding

    private lateinit var loginBottomSheet: BottomSheetDialogFragment
    private lateinit var auth: FirebaseAuth
    private var currentUser: FirebaseUser? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = SettingFragBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth
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
            when (auth.currentUser) {
                null -> {
                    loginBottomSheet = LoginBottomSheetDialogFragment()
                    loginBottomSheet.show(requireActivity().supportFragmentManager, "")
                    //startActivity(Intent(requireContext(), LoginActivity::class.java))
                    //requireActivity().finish()
                }
                else -> {
                    auth.signOut()
                    currentUser = auth.currentUser
                    viewDataBinding.textviewLogin.text = getString(R.string.login)
                }
            }

        }
    }

    private fun updateUser() {
        currentUser = auth.currentUser

        if (currentUser != null) {
            viewDataBinding.textviewLogin.text = getString(R.string.logout)
        } else {
            viewDataBinding.textviewLogin.text = getString(R.string.login)
        }
    }

    override fun onStart() {
        super.onStart()
        updateUser()
    }

    override fun onResume() {
        super.onResume()
        updateUser()
    }

    companion object {
        @JvmStatic
        fun newInstance() = SettingFragment()
    }

}