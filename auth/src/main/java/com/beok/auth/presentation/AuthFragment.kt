package com.beok.auth.presentation

import com.beok.auth.R
import com.beok.auth.databinding.FragmentAuthBinding
import com.beok.shared.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthFragment : BaseFragment<FragmentAuthBinding>(
    layoutResourceID = R.layout.fragment_auth
)
