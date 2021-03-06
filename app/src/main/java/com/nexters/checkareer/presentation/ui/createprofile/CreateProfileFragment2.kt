package com.nexters.checkareer.presentation.ui.createprofile

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.nexters.checkareer.R
import com.nexters.checkareer.data.adapter.db.data.UserProfile
import com.nexters.checkareer.databinding.CreateProfileFrag2Binding
import com.nexters.checkareer.domain.skill.Skill
import com.nexters.checkareer.presentation.ui.createprofile.adapter.SkillCategoryAdapter
import com.nexters.checkareer.presentation.ui.createprofile.listener.SkillCategoryListener
import com.nexters.checkareer.presentation.ui.createprofile.model.CategorySelect
import com.nexters.checkareer.presentation.ui.home.HomeActivity
import com.nexters.checkareer.presentation.ui.home.adapter.MySkillAdapter
import com.skydoves.balloon.*
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class CreateProfileFragment2 : Fragment() {

    private val viewModel by viewModels<CreateProfile2ViewModel>()
    private val args: CreateProfileFragment2Args by navArgs()
    private lateinit var profileImageDescriptionBalloon: Balloon

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
        setProfileInformation()
        setProfileDescriptionBalloon()

        viewDataBinding.imageviewBack.setOnClickListener {
            findNavController().popBackStack()
        }

        viewDataBinding.buttonNext.setOnClickListener {
            if (viewDataBinding.edittextName.length() > 0) {
                viewModel.saveUserProfile()

            } else {
                Toast.makeText(requireContext(), "????????? ??????????????????", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun setProfileInformation() {
        viewModel.selectedSkillItems.observe(viewLifecycleOwner) {
            if(it.size in 0..3) {
                viewDataBinding.profileImage.setImageResource(R.drawable.image_tree_1)
                viewDataBinding.textviewProfileDescription.text = getString(R.string.skill_level_1)
            } else if(it.size in 4..7) {
                viewDataBinding.profileImage.setImageResource(R.drawable.image_tree_2)
                viewDataBinding.textviewProfileDescription.text = getString(R.string.skill_level_2)
            } else {
                viewDataBinding.profileImage.setImageResource(R.drawable.image_tree_3)
                viewDataBinding.textviewProfileDescription.text = getString(R.string.skill_level_3)
            }
        }
    }

    private fun setProfileDescriptionBalloon() {
        profileImageDescriptionBalloon = Balloon.Builder(requireContext())
            .setLayout(R.layout.profile_description_item)
            .setArrowOrientation(ArrowOrientation.TOP)
            .setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
            .setWidthRatio(1f)
            .setMarginLeft(20)
            .setMarginRight(20)
            .setCornerRadius(10f)
            .setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            .setBalloonAnimation(BalloonAnimation.OVERSHOOT)
            .setLifecycleOwner(viewLifecycleOwner)
            .build()



        viewDataBinding.imageviewProfileDescription.setOnClickListener {
            viewDataBinding.imageviewProfileDescription.showAlignBottom(profileImageDescriptionBalloon)
        }
    }

    private fun setEvents() {
        viewModel.goHome.observe(this.viewLifecycleOwner) {
            val intent = Intent(requireContext(), HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }

        viewDataBinding.edittextName.setOnKeyListener{ _, keyCode, keyEvent ->
            if (keyEvent.action == KeyEvent.ACTION_DOWN && keyCode == KEYCODE_ENTER) {
                if (viewDataBinding.edittextName.length() > 0) {
                    viewModel.saveUserProfile()

                } else {
                    Toast.makeText(requireContext(), "????????? ??????????????????", Toast.LENGTH_SHORT).show()
                }
            }
            true
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
}

