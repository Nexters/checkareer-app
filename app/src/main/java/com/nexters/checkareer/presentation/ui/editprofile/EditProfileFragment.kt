package com.nexters.checkareer.presentation.ui.editprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nexters.checkareer.R
import com.nexters.checkareer.databinding.EditProfileFragBinding
import com.nexters.checkareer.domain.skill.Skill
import com.nexters.checkareer.presentation.ui.createprofile.adapter.SkillCategoryAdapter
import com.nexters.checkareer.presentation.ui.createprofile.listener.SkillCategoryListener
import com.nexters.checkareer.presentation.ui.createprofile.model.CategorySelect
import com.nexters.checkareer.presentation.ui.editprofile.adapter.MySkillEditAdapter
import com.nexters.checkareer.presentation.ui.editprofile.addskill.AddSkillBottomSheetDialogFragment
import com.nexters.checkareer.presentation.ui.editprofile.addsubskill.AddSubSkillBottomSheetDialogFragment
import com.nexters.checkareer.presentation.ui.editprofile.listener.SkillEditListener
import com.nexters.checkareer.presentation.ui.home.adapter.MySkillAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditProfileFragment : Fragment(), SkillEditListener {

    private val viewModel by activityViewModels<EditProfileViewModel>()
    private lateinit var viewDataBinding: EditProfileFragBinding

    private lateinit var addSkillBottomSheet: BottomSheetDialogFragment
    private lateinit var addSubSkillBottomSheet: BottomSheetDialogFragment


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = EditProfileFragBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBackButton()
        setupCompleteButton()
        setupAddSkillButton()
        setupLifecycleOwner()
        setupMySkillAdapter()
        setupMySkillTopThreeAdapter()

    }

    private fun setupBackButton() {
        viewDataBinding.imageviewBack.setOnClickListener {
            requireActivity().finish()
        }
    }

    private fun setupCompleteButton() {
        viewDataBinding.buttonSave.setOnClickListener {
            requireActivity().finish()
        }
    }

    private fun setupAddSkillButton() {
        viewDataBinding.imageviewAddSkill.setOnClickListener {
            addSkillBottomSheet = AddSkillBottomSheetDialogFragment()
            addSkillBottomSheet.show(requireActivity().supportFragmentManager, "")
        }
    }

    private fun setupLifecycleOwner() {
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
    }

    private fun setupMySkillAdapter() {
        viewDataBinding.recyclerviewMySkills.apply {
            adapter = MySkillEditAdapter("SKILL_LIST", this@EditProfileFragment)
        }
    }


    private fun setupMySkillTopThreeAdapter() {
        viewDataBinding.recyclerviewMySkillsTopThree.apply {
            adapter = MySkillAdapter("SKILL_TOP_THREE_LIST")
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = EditProfileFragment()
    }

    override fun onSkillDeleteClicked(item: Skill, view: View) {
        viewModel.removeSelectedSkillCategoryItem(item)
    }

    override fun onSubSkillAddClick(item: Skill, view: View) {
        addSubSkillBottomSheet = AddSubSkillBottomSheetDialogFragment()
        addSubSkillBottomSheet.show(requireActivity().supportFragmentManager, "")
    }

}