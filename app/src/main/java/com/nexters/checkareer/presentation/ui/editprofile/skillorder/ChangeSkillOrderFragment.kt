package com.nexters.checkareer.presentation.ui.editprofile.skillorder

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.nexters.checkareer.databinding.ChangeSkillOderFragBinding
import com.nexters.checkareer.domain.vo.SkillTree
import com.nexters.checkareer.presentation.ui.editprofile.EditProfileActivity
import com.nexters.checkareer.presentation.ui.editprofile.adapter.MySkillEditOrderAdapter
import com.nexters.checkareer.presentation.ui.editprofile.listener.ItemDragListener
import com.nexters.checkareer.presentation.ui.editprofile.util.ItemTouchHelperCallback
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ChangeSkillOrderFragment : Fragment(), ItemDragListener {

    private val viewModel by viewModels<ChangeSkillOrderViewModel>()
    private lateinit var viewDataBinding: ChangeSkillOderFragBinding

    private lateinit var mySkillEditOrderAdapter: MySkillEditOrderAdapter
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

        activity?.onBackPressedDispatcher?.addCallback(requireActivity(), object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                setChangedSkillListOrder()
            }
        })

    }

    private fun setupBackButton() {
        viewDataBinding.imageviewBack.setOnClickListener {
            setChangedSkillListOrder()
        }
    }

    private fun setChangedSkillListOrder() {
        val data = Intent(requireContext(), EditProfileActivity::class.java)
        val arrayList = ArrayList(mySkillEditOrderAdapter.items)
        data.putParcelableArrayListExtra("skillList", arrayList)
        requireActivity().setResult(RESULT_OK, data)
        requireActivity().finish()
    }

    private fun setupMySkillAdapter() {
        val list = activity?.intent?.getParcelableArrayListExtra<SkillTree>("skillList")?.toList()
        viewModel.setSkillListOrder(list ?: listOf())

        viewDataBinding.recyclerviewMySkills.apply {
            println(list.toString())
            mySkillEditOrderAdapter = MySkillEditOrderAdapter(list?.toMutableList() ?: mutableListOf(), this@ChangeSkillOrderFragment) {}
            adapter = mySkillEditOrderAdapter
            itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(mySkillEditOrderAdapter))
            itemTouchHelper.attachToRecyclerView(this)
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = ChangeSkillOrderFragment()
    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        itemTouchHelper.startDrag(viewHolder)
    }
}