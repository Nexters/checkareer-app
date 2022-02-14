package com.nexters.checkareer.presentation.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.nexters.checkareer.R
import com.nexters.checkareer.databinding.HomeFragBinding
import com.nexters.checkareer.presentation.ui.home.adapter.HomePageAdapter
import com.nexters.checkareer.presentation.ui.home.listener.HomeListener
import com.nexters.checkareer.presentation.ui.home.model.MyProfile
import com.nexters.checkareer.presentation.ui.home.model.OtherProfile
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), HomeListener {

    private val viewModel by viewModels<HomeViewModel>()

    private lateinit var viewDataBinding: HomeFragBinding

    private lateinit var homePageAdapter: HomePageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = HomeFragBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupLifecycleOwner()
        setupListAdapter()
        setupViewPagerAdapter()
        setupTabLayout()
    }

    private fun setupListAdapter() {
//        viewDataBinding.homeRecyclerview.apply {
//            adapter = HomeAdapter(this@HomeFragment)
//        }
    }


    private fun setupViewPagerAdapter() {
        viewDataBinding.viewpagerHome.apply {
            homePageAdapter = HomePageAdapter(requireActivity())
            homePageAdapter.addFragment(HomeFragment2())
            homePageAdapter.addFragment(HomeFragment3())
            adapter = homePageAdapter
        }
    }

    private fun setupTabLayout() {
        TabLayoutMediator(viewDataBinding.tabLayoutHome, viewDataBinding.viewpagerHome) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = getString(R.string.my_profile)
                }
                1 -> {
                    tab.text = getString(R.string.friend_profile)
                }
            }
        }.attach()
    }

    private fun setupLifecycleOwner() {
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
    }

    override fun onMyProfileClicked(item: MyProfile) {

    }

    override fun onOtherProfileClicked(item: OtherProfile) {

    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
        const val ARG_OBJECT = "object"
    }
}