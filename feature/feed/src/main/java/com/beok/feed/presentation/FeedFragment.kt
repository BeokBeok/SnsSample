package com.beok.feed.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.beok.feed.BR
import com.beok.feed.R
import com.beok.feed.databinding.FragmentFeedBinding
import com.beok.feed.domain.model.Card
import com.beok.ohousesample.MainFragmentDirections
import com.beok.shared.base.BaseFragment
import com.beok.shared.base.BaseListAdapter
import com.beok.shared.model.ClickAction
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedFragment : BaseFragment<FragmentFeedBinding>(
    layoutResourceID = R.layout.fragment_feed
) {
    private val viewModel by viewModels<FeedViewModel>()
    private val feedAdapter by lazy {
        BaseListAdapter(
            layoutResourceID = R.layout.item_feed,
            bindingID = BR.item,
            clickAction = ClickAction(BR.onClick) {
                viewModel.onClickCardId(it.id)
            },
            diffUtil = object : DiffUtil.ItemCallback<Card>() {
                override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean =
                    oldItem.id == newItem.id

                override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean =
                    oldItem == newItem
            }
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
        binding.rvFeed.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val isLoading = viewModel.state.value is FeedState.Loading
                    if (dy < 0 || isLoading) return
                    val bottomDirection = 1
                    if (!recyclerView.canScrollVertically(bottomDirection)) {
                        viewModel.fetchFeed(isNext = true)
                    }
                }
            }
        )
        binding.srlFeed.setOnRefreshListener {
            showContent(isRefresh = true)
        }
    }

    private fun setupObserver() {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is FeedState.Error -> {
                    hideLoading()
                    Toast.makeText(
                        requireContext(),
                        it.throwable.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                FeedState.Loaded -> {
                    hideLoading()
                }
                FeedState.Loading -> {
                    binding.pbFeed.isVisible = true
                }
                is FeedState.CardClick -> {
                    val action = MainFragmentDirections.actionDetail(it.id)
                    findNavController().navigate(action)
                }
                FeedState.Refreshing -> {
                    binding.srlFeed.isRefreshing = true
                }
            }
        }
        viewModel.feed.observe(viewLifecycleOwner) {
            feedAdapter.submitList(it)
        }
    }

    private fun hideLoading() {
        binding.pbFeed.isVisible = false
        binding.srlFeed.isRefreshing = false
    }

    private fun setupUI() {
        binding.rvFeed.adapter = feedAdapter
        binding.rvFeed.itemAnimator = null
    }

    private fun showContent(isRefresh: Boolean = false) {
        viewModel.fetchFeed(isRefresh = isRefresh)
    }

    companion object {
        fun newInstance(): FeedFragment = FeedFragment()
    }
}
