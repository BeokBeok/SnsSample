package com.beok.detail.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.beok.detail.BR
import com.beok.detail.R
import com.beok.detail.databinding.FragmentCardDetailBinding
import com.beok.detail.domain.model.RecommendCard
import com.beok.shared.base.BaseAdapter
import com.beok.shared.base.BaseFragment
import com.beok.shared.model.ClickAction
import com.beok.shared.navigation.NavigationState
import com.beok.shared.navigation.NavigationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CardDetailFragment : BaseFragment<FragmentCardDetailBinding>(
    layoutResourceID = R.layout.fragment_card_detail
) {
    private val viewModel by viewModels<CardDetailViewModel>()
    private val navViewModel by activityViewModels<NavigationViewModel>()
    private val cardDetailAdapter by lazy {
        BaseAdapter<RecommendCard>(
            layoutResourceID = R.layout.item_card_detail,
            bindingID = BR.item,
            clickAction = ClickAction(bindingID = BR.onClick) {
                navViewModel.navigate(navigationState = NavigationState.CardDetail(it.id))
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        setupListener()
        setupObserve()
        showContent()
    }

    private fun setupListener() {
        binding.srlCardDetail.setOnRefreshListener {
            showContent(isRefresh = true)
        }
    }

    private fun setupUI() {
        binding.rvCardDetailRecommend.adapter = cardDetailAdapter
    }

    private fun setupObserve() {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is CardDetailState.Error -> {
                    hideLoading()
                    Toast.makeText(
                        requireContext(),
                        it.throwable.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is CardDetailState.Loaded -> {
                    hideLoading()
                    binding.item = it.item
                    cardDetailAdapter.replaceItems(it.item.recommendCards)
                }
                CardDetailState.Loading -> {
                    binding.pbCardDetail.isVisible = true
                }
                CardDetailState.Refreshing -> {
                    binding.srlCardDetail.isRefreshing = true
                }
            }
        }
    }

    private fun hideLoading() {
        binding.pbCardDetail.isVisible = false
        binding.srlCardDetail.isRefreshing = false
    }

    private fun showContent(isRefresh: Boolean = false) {
        viewModel.fetchCardDetail(isRefresh = isRefresh)
    }

    companion object {
        private const val BUNDLE_KEY_CARD_ID = "bundle_key_card_id"
        val TAG = CardDetailFragment::class.java.simpleName.toString()

        fun newInstance(id: Int): CardDetailFragment = CardDetailFragment().apply {
            arguments = bundleOf(BUNDLE_KEY_CARD_ID to id)
        }
    }
}
