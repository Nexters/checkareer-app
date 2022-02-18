package com.nexters.checkareer.presentation.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.nexters.checkareer.R
import com.nexters.checkareer.databinding.HomeFragBinding
import com.nexters.checkareer.presentation.ui.home.adapter.HomePageAdapter
import com.nexters.checkareer.presentation.ui.home.listener.HomeListener
import com.nexters.checkareer.presentation.ui.home.model.MyProfile
import com.nexters.checkareer.presentation.ui.home.model.OtherProfile
import com.nexters.checkareer.presentation.ui.settings.SettingActivity
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
        setupSettingButton()
    }

    private fun setupListAdapter() {
//        viewDataBinding.homeRecyclerview.apply {
//            adapter = HomeAdapter(this@HomeFragment)
//        }
    }

    private fun setupSettingButton() {
        viewDataBinding.imageviewMenu.setOnClickListener {
            startActivity(Intent(requireContext(), SettingActivity::class.java))
        }
    }


    private fun setupViewPagerAdapter() {
        viewDataBinding.viewpagerHome.apply {
            homePageAdapter = HomePageAdapter(requireActivity())
            homePageAdapter.addFragment(HomeFragment2())
            homePageAdapter.addFragment(HomeFragment3())
            adapter = homePageAdapter
        }

        viewDataBinding.textviewMyProfile.setOnClickListener {
            viewDataBinding.viewpagerHome.currentItem = 0
        }

        viewDataBinding.textviewFriendProfile.setOnClickListener {
            viewDataBinding.viewpagerHome.currentItem = 1
        }

        viewDataBinding.viewpagerHome.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when(position) {
                    0 -> {
                        viewDataBinding.textviewMyProfile.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                        viewDataBinding.textviewFriendProfile.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray))
                    }
                    1 -> {
                        viewDataBinding.textviewMyProfile.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray))
                        viewDataBinding.textviewFriendProfile.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                    }
                }
            }
        })

    }

//    private fun setupTabLayout() {
//        TabLayoutMediator(viewDataBinding.tabLayoutHome, viewDataBinding.viewpagerHome) { tab, position ->
//            when (position) {
//                0 -> {
//                    tab.text = getString(R.string.my_profile)
//                }
//                1 -> {
//                    tab.text = getString(R.string.friend_profile)
//                }
//            }
//        }.attach()
//    }

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