package com.nexters.checkareer.presentation.ui.editprofile.skillorder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.nexters.checkareer.databinding.ChangeSkillOderFragBinding
import com.nexters.checkareer.domain.vo.SkillTree
import com.nexters.checkareer.presentation.ui.editprofile.EditProfileViewModel
import com.nexters.checkareer.presentation.ui.editprofile.adapter.MySkillEditOrderAdapter
import com.nexters.checkareer.presentation.ui.editprofile.listener.ItemDragListener
import com.nexters.checkareer.presentation.ui.editprofile.util.ItemTouchHelperCallback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangeSkillOrderFragment : Fragment(), ItemDragListener {

    private val viewModel by viewModels<ChangeSkillOrderViewModel>()
    private lateinit var viewDataBinding: ChangeSkillOderFragBinding

    private lateinit var itemTouchHelper: ItemTouchHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = ChangeSkillOderFragBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBackButton()
        setupMySkillAdapter()

    }

    private fun setupBackButton() {
        viewDataBinding.imageviewBack.setOnClickListener {
            requireActivity().finish()
        }
    }

    private fun setupMySkillAdapter() {
        viewDataBinding.recyclerviewMySkills.apply {
            val mySkillEditOrderAdapter = MySkillEditOrderAdapter("SKILL_LIST", this@ChangeSkillOrderFragment) {}
            adapter = mySkillEditOrderAdapter
            itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(mySkillEditOrderAdapter))
            itemTouchHelper.attachToRecyclerView(this)

            val list = activity?.intent?.getParcelableArrayListExtra<SkillTree>("skillList")?.toList()
            viewModel.setSkillListOrder(list ?: listOf())
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = ChangeSkillOrderFragment()
    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        itemTouchHelper.startDrag(viewHolder)
    }

    override fun onEndDrag(viewHolder: RecyclerView.ViewHolder) {

    }

}