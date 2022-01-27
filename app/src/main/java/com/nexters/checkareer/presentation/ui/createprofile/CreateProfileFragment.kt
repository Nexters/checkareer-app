package com.nexters.checkareer.presentation.ui.createprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.nexters.checkareer.R
import com.nexters.checkareer.databinding.CreateProfileFragBinding
import com.nexters.checkareer.domain.category.Category
import com.nexters.checkareer.presentation.ui.createprofile.adapter.SkillCategoryAdapter
import com.nexters.checkareer.presentation.ui.createprofile.listener.SkillCategoryListener
import com.nexters.checkareer.presentation.ui.createprofile.model.CategorySelect
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateProfileFragment : Fragment(), SkillCategoryListener {

    private val viewModel by viewModels<CreateProfileViewModel>()

    private lateinit var viewDataBinding: CreateProfileFragBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = CreateProfileFragBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupLifecycleOwner()
        setupListAdapter()
    }

    private fun setupListAdapter() {
        viewDataBinding.recyclerviewSkillCategory.apply {
            val layoutManager = FlexboxLayoutManager(requireContext())
            layoutManager.apply {
                flexDirection = FlexDirection.ROW
                justifyContent = JustifyContent.FLEX_START
            }
            setLayoutManager(layoutManager)
            adapter = SkillCategoryAdapter(this@CreateProfileFragment)
        }
    }

    private fun setupLifecycleOwner() {
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
    }


    companion object {
        @JvmStatic
        fun newInstance() = CreateProfileFragment()
    }

    override fun onSkillCategoryClicked(item: CategorySelect, view: View) {
        println(item.selected)
        if(!item.selected) {
            view.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.black))
            item.selected = true
        } else {
            view.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.white))
            item.selected = false
        }

    }


}