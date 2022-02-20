package com.nexters.checkareer.presentation.ui.editprofile.addskill

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nexters.checkareer.databinding.AddSkillFragmentBottomsheetBinding
import com.nexters.checkareer.domain.skill.Skill
import com.nexters.checkareer.presentation.ui.createprofile.adapter.SkillCategoryAdapter
import com.nexters.checkareer.presentation.ui.createprofile.listener.SkillCategoryListener
import com.nexters.checkareer.presentation.ui.createprofile.model.CategorySelect
import com.nexters.checkareer.presentation.ui.editprofile.EditProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddSkillBottomSheetDialogFragment : BottomSheetDialogFragment(), SkillCategoryListener {

    private val viewModel by viewModels<AddSkillViewModel>()
    private val editProfileViewModel by activityViewModels<EditProfileViewModel>()
    private lateinit var viewDataBinding: AddSkillFragmentBottomsheetBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = AddSkillFragmentBottomsheetBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupLifecycleOwner()
        setupSelectedSkillListAdapter()
        setupSkillListAdapter()
        setupAddCompleteButton()

    }

    private fun setupLifecycleOwner() {
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
    }

    private fun setupAddCompleteButton() {
        viewDataBinding.buttonComplete.setOnClickListener {
            editProfileViewModel.addSelectedSkillCategoryItem(viewModel.selectedSkillCategoryItems.value?.toList()?.map { Skill(it.id, it.name) } ?: listOf())
            dismiss()
        }
    }

    private fun setupSelectedSkillListAdapter() {
        viewDataBinding.recyclerviewSelectedSkillCategory.apply {
            adapter = SkillCategoryAdapter(this@AddSkillBottomSheetDialogFragment, "SELECTED_SKILL_LIST")
        }
    }

    private fun setupSkillListAdapter() {
        viewDataBinding.recyclerviewSkillCategory.apply {
            val layoutManager = FlexboxLayoutManager(requireContext())
            layoutManager.apply {
                flexDirection = FlexDirection.ROW
                justifyContent = JustifyContent.FLEX_START
            }
            setLayoutManager(layoutManager)
            adapter = SkillCategoryAdapter(this@AddSkillBottomSheetDialogFragment, ".")
        }
    }

    override fun onSkillCategoryClicked(item: CategorySelect, view: View) {
        if (!item.selected) {
            item.selected = true
            viewModel.addSelectedSkillCategoryItem(item)
        } else {
            item.selected = false
            viewModel.removeSelectedSkillCategoryItem(item)
        }
    }


}