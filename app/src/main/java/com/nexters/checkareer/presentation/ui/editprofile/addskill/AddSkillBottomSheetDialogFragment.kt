package com.nexters.checkareer.presentation.ui.editprofile.addskill

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.google.android.flexbox.*
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
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

    @SuppressLint("ClickableViewAccessibility")
    private fun setupSkillListAdapter() {
        viewModel.loadSkillCategories(true, editProfileViewModel.profile.value?.skills ?: listOf())

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