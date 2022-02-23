package com.nexters.checkareer.presentation.ui.createprofile

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.nexters.checkareer.data.adapter.db.data.UserProfile
import com.nexters.checkareer.databinding.CreateProfileFrag2Binding
import com.nexters.checkareer.domain.skill.Skill
import com.nexters.checkareer.presentation.ui.createprofile.adapter.SkillCategoryAdapter
import com.nexters.checkareer.presentation.ui.createprofile.listener.SkillCategoryListener
import com.nexters.checkareer.presentation.ui.createprofile.model.CategorySelect
import com.nexters.checkareer.presentation.ui.home.HomeActivity
import com.nexters.checkareer.presentation.ui.home.adapter.MySkillAdapter
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
        viewModel.setSelectedSkillItems(args.skillCategories.toList().map { Skill(it.id, it.name, it.parentId) })
        setupMySkillTopThreeAdapter()
        setupMySkillAdapter()
        setupEditTextEvent()
        setEvents()

        viewDataBinding.imageviewBack.setOnClickListener {
            findNavController().popBackStack()
        }

        viewDataBinding.buttonNext.setOnClickListener {
            if (viewDataBinding.edittextName.length() > 0) {
                viewModel.saveUserProfile()

            } else {
                Toast.makeText(requireContext(), "이름을 입력해주세요", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun setEvents() {
        viewModel.goHome.observe(this.viewLifecycleOwner) {
            val intent = Intent(requireContext(), HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
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

    private fun setupEditTextEvent() {
        viewDataBinding.edittextName.apply {
            addTextChangedListener(object : TextWatcher {
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

