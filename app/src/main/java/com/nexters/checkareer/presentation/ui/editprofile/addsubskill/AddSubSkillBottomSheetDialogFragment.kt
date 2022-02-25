package com.nexters.checkareer.presentation.ui.editprofile.addsubskill

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nexters.checkareer.databinding.AddSubSkillFragmentBottomsheetBinding
import com.nexters.checkareer.domain.skill.Skill
import com.nexters.checkareer.presentation.ui.createprofile.adapter.SkillCategoryAdapter
import com.nexters.checkareer.presentation.ui.createprofile.listener.SkillCategoryListener
import com.nexters.checkareer.presentation.ui.createprofile.model.CategorySelect
import com.nexters.checkareer.presentation.ui.editprofile.EditProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddSubSkillBottomSheetDialogFragment : BottomSheetDialogFragment(), SkillCategoryListener {

    private val viewModel by viewModels<AddSubSkillViewModel>()
    private val editProfileViewModel by activityViewModels<EditProfileViewModel>()
    private lateinit var viewDataBinding: AddSubSkillFragmentBottomsheetBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = AddSubSkillFragmentBottomsheetBinding.inflate(inflater, container, false).apply {
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
            viewModel.parentSkillId?.let { editProfileViewModel.saveSubSkills(it, viewModel.selectedSkills.value?.toList()?.map { Skill(it.id, it.name, it.parentId) } ?: listOf()) }
            dismiss()
        }
    }

    private fun setupSelectedSkillListAdapter() {
        viewDataBinding.recyclerviewSelectedSkillCategory.apply {
            adapter = SkillCategoryAdapter(this@AddSubSkillBottomSheetDialogFragment, "SELECTED_SKILL_LIST")
        }
    }

    private fun setupSkillListAdapter() {
        editProfileViewModel.clickedSkill.value?.let {
            it.id.run { viewModel.loadSkillCategories(editProfileViewModel.profile.value?.skills ?: listOf(), this.toInt()) }
        }?: run { dismiss() }

        viewDataBinding.recyclerviewSkillCategory.apply {
            val layoutManager = FlexboxLayoutManager(requireContext())
            layoutManager.apply {
                flexDirection = FlexDirection.ROW
                justifyContent = JustifyContent.FLEX_START
            }
            setLayoutManager(layoutManager)
            adapter = SkillCategoryAdapter(this@AddSubSkillBottomSheetDialogFragment, ".")
        }
    }

    override fun onSkillCategoryClicked(item: CategorySelect, view: View) {
        viewModel.toggleSkillItemSelected(item)
    }

    override fun onSelectedSkillClicked(item: CategorySelect) {
        viewModel.removeSkillItemSelected(item)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        bottomSheetDialog.setOnShowListener {
            val bottomSheet = bottomSheetDialog
                .findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)

            if (bottomSheet != null) {
                val behavior: BottomSheetBehavior<*> = BottomSheetBehavior.from(bottomSheet)
                behavior.isDraggable = false
            }
        }
        return bottomSheetDialog
    }


}