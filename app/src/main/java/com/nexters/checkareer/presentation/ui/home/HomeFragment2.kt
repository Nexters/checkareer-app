package com.nexters.checkareer.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.nexters.checkareer.R
import com.nexters.checkareer.databinding.HomeFrag2Binding
import com.nexters.checkareer.presentation.ui.home.adapter.MySkillAdapter
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

        setupProfileDeleteButton()
        setupLifecycleOwner()
        setupMySkillAdapter()
        setupMySkillTopThreeAdapter()
    }

    private fun setupProfileDeleteButton() {
        viewDataBinding.imageviewDelete.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(getString(R.string.delete_profle))
                .setNegativeButton("취소") { _, _ -> }
                .setPositiveButton("확인") { dialog, _ ->
                    viewModel.deleteUserProfile()
                }
                .show()
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

}