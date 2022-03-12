package com.beok.home.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.beok.home.BR
import com.beok.home.R
import com.beok.home.databinding.FragmentHomeBinding
import com.beok.home.domain.model.PopularCard
import com.beok.home.domain.model.PopularUser
import com.beok.home.presentation.model.HomeState
import com.beok.shared.base.BaseAdapter
import com.beok.shared.base.BaseFragment
import com.beok.shared.model.ClickAction
import com.beok.shared.navigation.NavigationState
import com.beok.shared.navigation.NavigationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(
    layoutResourceID = R.layout.fragment_home
) {
    private val viewModel by viewModels<HomeViewModel>()
    private val navViewModel by activityViewModels<NavigationViewModel>()
    private val cardAdapter by lazy {
        BaseAdapter<PopularCard>(
            layoutResourceID = R.layout.item_card,
            bindingID = BR.item,
            clickAction = ClickAction(bindingID = BR.onClick) {
                navViewModel.navigate(NavigationState.CardDetail(it.id))
            }
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
        setupListener()
        setupObserver()
        showContent()
    }

    private fun setupListener() {
        binding.srlHome.setOnRefreshListener {
            showContent(isRefresh = true)
        }
    }

    private fun setupUI() {
        binding.rvHomeCard.adapter = cardAdapter
        binding.rvHomeUser.adapter = userAdapter
    }

    private fun setupObserver() {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is HomeState.Error -> {
                    hideLoading()
                    Toast.makeText(
                        requireContext(),
                        it.throwable.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is HomeState.Loaded -> {
                    hideLoading()
                    cardAdapter.replaceItems(it.items.popularCards)
                    userAdapter.replaceItems(it.items.popularUsers)
                }
                HomeState.Loading -> {
                    binding.pbHome.isVisible = true
                }
                HomeState.Refreshing -> {
                    binding.srlHome.isRefreshing = true
                }
            }
        }
    }

    private fun hideLoading() {
        binding.pbHome.isVisible = false
        binding.srlHome.isRefreshing = false
    }

    private fun showContent(isRefresh: Boolean = false) {
        viewModel.fetchHome(isRefresh)
    }

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }
}
