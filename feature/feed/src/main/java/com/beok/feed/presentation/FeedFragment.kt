package com.beok.feed.presentation

import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedFragment : Fragment() {

    companion object {
        fun newInstance(): FeedFragment = FeedFragment()
    }
}
