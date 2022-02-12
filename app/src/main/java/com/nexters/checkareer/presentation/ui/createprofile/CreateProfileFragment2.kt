package com.nexters.checkareer.presentation.ui.createprofile

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.nexters.checkareer.data.adapter.db.data.UserProfile
import com.nexters.checkareer.databinding.CreateProfileFrag2Binding
import com.nexters.checkareer.presentation.ui.createprofile.adapter.SkillCategoryAdapter
import com.nexters.checkareer.presentation.ui.createprofile.listener.SkillCategoryListener
import com.nexters.checkareer.presentation.ui.createprofile.model.CategorySelect
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
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
        viewModel.setSelectedSkillItems(args.skillCategories.toList())
        setupSelectedSkillListAdapter()

        viewDataBinding.imageviewBack.setOnClickListener {
            findNavController().popBackStack()
        }

        viewDataBinding.buttonNext.setOnClickListener {
            viewModel.saveUserProfile()
        }
        setupEditTextEvent()
    }

    private fun setupLifecycleOwner() {
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
    }

    private fun setupSelectedSkillListAdapter() {
        viewDataBinding.recyclerviewSelectedSkillCategory.apply {
            adapter = SkillCategoryAdapter(this@CreateProfileFragment2, "PROFILE_SELECTED_SKILL_LIST")
        }
    }

    private fun setupEditTextEvent() {
        viewDataBinding.edittextName.apply {
            addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

                override fun afterTextChanged(s: Editable?) {
                    viewModel.setName(s.toString())
                }
            })
        }
    }

   /*private fun saveUserProfile() = with(viewDataBinding) {
        viewModel.saveUserProfile(
            UserProfile(
                user = edittextName.text.toString(),
                skills = viewModel.selectedSkillItems.value?.toList() ?: listOf()
            )
        )
    }*/

    companion object {
        @JvmStatic
        fun newInstance() = CreateProfileFragment2()
    }

    override fun onSkillCategoryClicked(item: CategorySelect, view: View) {
    }
}

