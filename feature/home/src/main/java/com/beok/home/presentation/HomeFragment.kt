package com.beok.home.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.beok.home.BR
import com.beok.home.R
import com.beok.home.databinding.FragmentHomeBinding
import com.beok.home.domain.model.PopularCard
import com.beok.home.domain.model.PopularUser
import com.beok.shared.base.BaseAdapter
import com.beok.shared.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(
    layoutResourceID = R.layout.fragment_home
) {
    private val viewModel by viewModels<HomeViewModel>()
    private val cardAdapter by lazy {
        BaseAdapter<PopularCard>(
            layoutResourceID = R.layout.item_card,
            bindingID = BR.item,
        )
    }
    private val userAdapter by lazy {
        BaseAdapter<PopularUser>(
            layoutResourceID = R.layout.item_user,
            bindingID = BR.item,
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        setupObserver()
        showContent()
    }

    private fun setupUI() {
        binding.rvHomeCard.adapter = cardAdapter
        binding.rvHomeUser.adapter = userAdapter
    }

    private fun setupObserver() {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is HomeState.Error -> {
                    Toast.makeText(
                        requireContext(),
                        it.throwable.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is HomeState.Loaded -> {
                    binding.pbHome.isVisible = false
                    cardAdapter.replaceItems(it.items.popularCards)
                    userAdapter.replaceItems(it.items.popularUsers)
                }
                HomeState.Loading -> {
                    binding.pbHome.isVisible = true
                }
            }
        }
    }

    private fun showContent() {
        viewModel.fetchHome()
    }

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }
}
