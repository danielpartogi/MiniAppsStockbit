package com.apelgigit.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.apelgigit.commons.base.BaseFragment
import com.apelgigit.home.databinding.FragmentHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

class HomeFragment : BaseFragment<HomeViewModel>(HomeViewModel::class),
    ViewPager.OnPageChangeListener,
    BottomNavigationView.OnNavigationItemSelectedListener {

    private val backStack = Stack<Int>()

    private val fragments = listOf(
        WatchListFragment(),
        WatchListVolumeFragment()
    )

    private lateinit var binding: FragmentHomeBinding

    private val indexToPage = mapOf(
        0 to R.id.home_main,
        1 to R.id.home_watch_list_volume)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreated(view: View) {
        binding.nsvpContent.addOnPageChangeListener(this)
        binding.nsvpContent.adapter = ViewPagerAdapter()
        binding.nsvpContent.offscreenPageLimit = fragments.size
        binding.navigation.setOnNavigationItemSelectedListener(this)

        if (backStack.empty()) backStack.push(0)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val position = indexToPage.values.indexOf(item.itemId)
        if (binding.nsvpContent.currentItem != position) setItem(position)
        return true
    }

    private fun setItem(position: Int) {
        binding.nsvpContent.currentItem = position
        backStack.remove(position)
        backStack.push(position)
    }

    override fun onPageScrollStateChanged(state: Int) {}

    override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {}

    override fun onPageSelected(page: Int) {
        val itemId = indexToPage[page] ?: R.id.home
        if (binding.navigation.selectedItemId != itemId) {
            binding.navigation.selectedItemId = itemId

        }
    }


    inner class ViewPagerAdapter :
        FragmentPagerAdapter(childFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getItem(position: Int): Fragment = fragments[position]

        override fun getCount(): Int = fragments.size

    }
}