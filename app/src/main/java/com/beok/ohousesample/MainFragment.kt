package com.beok.ohousesample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.beok.feed.presentation.FeedFragment
import com.beok.home.presentation.HomeFragment
import com.beok.ohousesample.databinding.FragmentMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainFragment : Fragment() {

    private var binding: FragmentMainBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    private fun setupUI() {
        binding?.run {
            vpMain.adapter = MainAdapter(
                fragmentActivity = this@MainFragment.requireActivity(),
                fragments = listOf(
                    HomeFragment.newInstance(),
                    FeedFragment.newInstance()
                )
            )
            val tabTitleGroup = listOf(
                getString(R.string.tab_home),
                getString(R.string.tab_feed)
            )
            TabLayoutMediator(tlMain, vpMain) { tab, position ->
                tab.text = tabTitleGroup[position]
            }.attach()
        }
    }
}
