package com.nexters.checkareer.presentation.ui.editprofile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nexters.checkareer.databinding.EditProfileFragBinding
import com.nexters.checkareer.domain.vo.SkillTree
import com.nexters.checkareer.presentation.ui.editprofile.adapter.MySkillEditAdapter
import com.nexters.checkareer.presentation.ui.editprofile.addskill.AddSkillBottomSheetDialogFragment
import com.nexters.checkareer.presentation.ui.editprofile.addsubskill.AddSubSkillBottomSheetDialogFragment
import com.nexters.checkareer.presentation.ui.editprofile.listener.SkillEditListener
import com.nexters.checkareer.presentation.ui.editprofile.listener.ItemDragListener
import com.nexters.checkareer.presentation.ui.editprofile.skillorder.ChangeSkillOrderActivity
import com.nexters.checkareer.presentation.ui.editprofile.util.ItemTouchHelperCallback
import com.nexters.checkareer.presentation.ui.home.adapter.MySkillAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EditProfileFragment : Fragment(), SkillEditListener {

    private val viewModel by activityViewModels<EditProfileViewModel>()
    private lateinit var viewDataBinding: EditProfileFragBinding

    private lateinit var startActivityForResult: ActivityResultLauncher<Intent>
    private lateinit var editSkillAdapter: MySkillEditAdapter
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
        setupChangeSkillOrderButton()
        setupLifecycleOwner()
        setupMySkillAdapter()
        setupMySkillTopThreeAdapter()
        setEvents()

    }

    private fun setEvents() {
        viewDataBinding.edittextName.setOnKeyListener { _, keyCode, keyEvent ->
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                if (viewDataBinding.edittextName.length() > 0) {
                    viewModel.editUserProfile(viewDataBinding.edittextName.text.toString())
                    viewModel.editProfileLoading.observe(viewLifecycleOwner) {
                        if (!it) requireActivity().finish()
                    }

                } else {
                    Toast.makeText(requireContext(), "이름을 입력해주세요", Toast.LENGTH_SHORT).show()
                }
            }
            false
        }
    }

    private fun setupBackButton() {
        viewDataBinding.imageviewBack.setOnClickListener {
            requireActivity().finish()
        }
    }

    private fun setupCompleteButton() {
        viewDataBinding.buttonSave.setOnClickListener {
            if (viewDataBinding.edittextName.length() > 0) {
                viewModel.editUserProfile(viewDataBinding.edittextName.text.toString())
                viewModel.editProfileLoading.observe(viewLifecycleOwner) {
                    if (!it) requireActivity().finish()
                }

            } else {
                Toast.makeText(requireContext(), "이름을 입력해주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupAddSkillButton() {
        viewDataBinding.imageviewAddSkill.setOnClickListener {
            addSkillBottomSheet = AddSkillBottomSheetDialogFragment()
            addSkillBottomSheet.show(requireActivity().supportFragmentManager, "")

        }
    }

    private fun setupChangeSkillOrderButton() {
        startActivityForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if(result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                intent?.getParcelableArrayListExtra<SkillTree>("skillList")?.toList()?.let { viewModel.changeSkillListOrder(it) }
            }
        }


        viewDataBinding.imageviewChangeOrder.setOnClickListener {
            val intent = Intent(requireContext(), ChangeSkillOrderActivity::class.java)
            val arrayList = ArrayList(viewModel.profile.value?.skills)
            intent.putParcelableArrayListExtra("skillList", arrayList)
            startActivityForResult.launch(intent)
        }
    }

    private fun setupLifecycleOwner() {
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
    }

    private fun setupMySkillAdapter() {
        viewDataBinding.recyclerviewMySkills.apply {
            editSkillAdapter = MySkillEditAdapter("SKILL_LIST", this@EditProfileFragment)
            adapter = editSkillAdapter
        }
    }


    private fun setupMySkillTopThreeAdapter() {
        viewDataBinding.recyclerviewMySkillsTopThree.apply {
            adapter = MySkillAdapter("SKILL_TOP_THREE_LIST")
        }
    }

    override fun onSkillDeleteClicked(item: SkillTree, view: View) {
        viewModel.removeSelectedSkillCategoryItem(item)
    }

    override fun onSubSkillAddClick(item: SkillTree, view: View) {
        viewModel.setClickedSkill(item.skill)
        addSubSkillBottomSheet = AddSubSkillBottomSheetDialogFragment()
        addSubSkillBottomSheet.show(requireActivity().supportFragmentManager, "")
    }

    companion object {
        @JvmStatic
        fun newInstance() = EditProfileFragment()
    }


}