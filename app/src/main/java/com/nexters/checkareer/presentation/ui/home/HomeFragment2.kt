package com.nexters.checkareer.presentation.ui.home

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.nexters.checkareer.R
import com.nexters.checkareer.databinding.HomeFrag2Binding
import com.nexters.checkareer.presentation.ui.createprofile.CreateProfileActivity
import com.nexters.checkareer.presentation.ui.editprofile.EditProfileActivity
import com.nexters.checkareer.presentation.ui.home.adapter.MySkillAdapter
import com.nexters.checkareer.presentation.ui.onboarding.OnBoardingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment2 : Fragment() {

    private val viewModel by viewModels<HomeViewModel>()

    private lateinit var viewDataBinding: HomeFrag2Binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = HomeFrag2Binding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupProfileEditButton()
        setupProfileDeleteButton()
        setupProfileCreateButton()
        setupLifecycleOwner()
        setupMySkillAdapter()
        setupMySkillTopThreeAdapter()
    }

    private fun setupProfileEditButton() {
        viewDataBinding.imageviewSetting.setOnClickListener {
            startActivity(Intent(requireContext(), EditProfileActivity::class.java))
        }
    }

    private fun setupProfileDeleteButton() {
        viewDataBinding.imageviewDelete.setOnClickListener {
            val mDialogView = LayoutInflater.from(requireContext()).inflate(R.layout.confirm_dialog, null)
            val mBuilder = AlertDialog.Builder(requireContext()).setView(mDialogView)
            val mAlertDialog = mBuilder.show()

            mAlertDialog.apply {
                window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                findViewById<CardView>(R.id.cardview_confirm).setOnClickListener {
                    viewModel.deleteUserProfile()
                    dismiss()
                }

                findViewById<CardView>(R.id.cardview_cancel).setOnClickListener {
                    dismiss()
                }

            }
        }
    }

    private fun setupProfileCreateButton() {
        viewDataBinding.cardviewCreateProfile.setOnClickListener {
            startActivity(Intent(requireContext(), CreateProfileActivity::class.java))
        }
    }

    private fun setupLifecycleOwner() {
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
    }

    private fun setupMySkillAdapter() {
        viewDataBinding.recyclerviewMySkills.apply {
            adapter = MySkillAdapter("SKILL_LIST")
        }
    }

    private fun setupMySkillTopThreeAdapter() {
        viewDataBinding.recyclerviewMySkillsTopThree.apply {
            adapter = MySkillAdapter("SKILL_TOP_THREE_LIST")
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadHomes(true)
    }

}