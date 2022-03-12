package com.beok.auth.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.beok.auth.R
import com.beok.auth.databinding.FragmentAuthBinding
import com.beok.auth.presentation.model.AuthState
import com.beok.shared.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthFragment : BaseFragment<FragmentAuthBinding>(
    layoutResourceID = R.layout.fragment_auth
) {
    private val viewModel by activityViewModels<AuthViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBinding()
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                AuthState.EmptyNickname -> {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.input_nickname),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                AuthState.EmptyPassword -> {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.input_password),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is AuthState.Error -> {
                    Toast.makeText(
                        requireContext(),
                        it.throwable.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                AuthState.LogIn,
                AuthState.NotLogIn -> Unit
            }
        }
    }

    private fun setupBinding() {
        binding.viewModel = viewModel
    }

    companion object {
        val TAG = AuthFragment::class.java.simpleName.toString()

        fun newInstance(): AuthFragment = AuthFragment()
    }
}
