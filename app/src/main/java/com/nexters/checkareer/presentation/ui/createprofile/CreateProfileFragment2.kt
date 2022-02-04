package com.nexters.checkareer.presentation.ui.createprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.nexters.checkareer.databinding.CreateProfileFrag2Binding
import com.nexters.checkareer.presentation.ui.createprofile.adapter.SkillCategoryAdapter
import com.nexters.checkareer.presentation.ui.createprofile.listener.SkillCategoryListener
import com.nexters.checkareer.presentation.ui.createprofile.model.CategorySelect

class CreateProfileFragment2 : Fragment(), SkillCategoryListener {

    private val viewModel by viewModels<CreateProfile2ViewModel>()
    private val args: CreateProfileFragment2Args by navArgs()

    private lateinit var viewDataBinding: CreateProfileFrag2Binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = CreateProfileFrag2Binding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLifecycleOwner()
        viewModel.selectedSkillCategoryItems.value = args.skillCategories.take(3).toMutableList()
        setupSelectedSkillListAdapter()

        viewDataBinding.imageviewBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }


    private fun setupLifecycleOwner() {
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
    }

    private fun setupSelectedSkillListAdapter() {
        viewDataBinding.recyclerviewSelectedSkillCategory.apply {
            adapter = SkillCategoryAdapter(this@CreateProfileFragment2, "PROFILE_SELECTED_SKILL_LIST")
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = CreateProfileFragment2()
    }

    override fun onSkillCategoryClicked(item: CategorySelect, view: View) {
    }
}

