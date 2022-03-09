package com.beok.detail.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.beok.detail.BR
import com.beok.detail.R
import com.beok.detail.databinding.FragmentCardDetailBinding
import com.beok.detail.domain.model.RecommendCard
import com.beok.ohousesample.MainFragmentDirections
import com.beok.shared.base.BaseAdapter
import com.beok.shared.base.BaseFragment
import com.beok.shared.model.ClickAction
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CardDetailFragment : BaseFragment<FragmentCardDetailBinding>(
    layoutResourceID = R.layout.fragment_card_detail
) {
    private val viewModel by viewModels<CardDetailViewModel>()
    private val args: CardDetailFragmentArgs by navArgs()
    private val cardDetailAdapter by lazy {
        BaseAdapter<RecommendCard>(
            layoutResourceID = R.layout.item_card_detail,
            bindingID = BR.item,
            clickAction = ClickAction(bindingID = BR.onClick) {
                viewModel.onClickCardId(it.id)
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        setupObserve()
        showContent()
    }

    private fun setupUI() {
        binding.rvCardDetailRecommend.adapter = cardDetailAdapter
    }

    private fun setupObserve() {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is CardDetailState.Error -> {
                    Toast.makeText(
                        requireContext(),
                        it.throwable.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is CardDetailState.Loaded -> {
                    binding.pbCardDetail.isVisible = false
                    binding.item = it.item
                    cardDetailAdapter.replaceItems(it.item.recommendCards)
                }
                CardDetailState.Loading -> {
                    binding.pbCardDetail.isVisible = true
                }
                is CardDetailState.CardClick -> {
                    val action = MainFragmentDirections.actionDetail(it.id)
                    findNavController().navigate(action)
                }
            }
        }
    }

    private fun showContent() {
        viewModel.fetchCardDetail(args.id)
    }
}
