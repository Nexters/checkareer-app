package com.nexters.checkareer.presentation.ui.createprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.nexters.checkareer.databinding.CreateProfileFrag1Binding
import com.nexters.checkareer.presentation.ui.createprofile.adapter.SkillCategoryAdapter
import com.nexters.checkareer.presentation.ui.createprofile.listener.SkillCategoryListener
import com.nexters.checkareer.presentation.ui.createprofile.model.CategorySelect
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateProfileFragment1 : Fragment(), SkillCategoryListener {

    private val viewModel by viewModels<CreateProfile1ViewModel>()
    private lateinit var viewDataBinding: CreateProfileFrag1Binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = CreateProfileFrag1Binding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupLifecycleOwner()
        setupSkillListAdapter()
        setupSelectedSkillListAdapter()

        viewDataBinding.buttonNext.setOnClickListener {
            val action = CreateProfileFragment1Directions.actionCreateProfileFragmentToCompleteProfileFragment(viewModel.selectedSkillCategoryItems.value!!.toTypedArray())
            Navigation.findNavController(view).navigate(action)
        }

        viewDataBinding.imageviewBack.setOnClickListener {
            requireActivity().finish()
        }

    }

    private fun setupSelectedSkillListAdapter() {
        viewDataBinding.recyclerviewSelectedSkillCategory.apply {
            adapter = SkillCategoryAdapter(this@CreateProfileFragment1, "SELECTED_SKILL_LIST")
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
            adapter = SkillCategoryAdapter(this@CreateProfileFragment1, ".")
        }
    }

    private fun setupLifecycleOwner() {
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
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

    companion object {
        @JvmStatic
        fun newInstance() = CreateProfileFragment1()
    }


}