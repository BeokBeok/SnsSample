package com.beok.ohousesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.beok.auth.presentation.AuthViewModel
import com.beok.auth.presentation.model.AuthState
import com.beok.ohousesample.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private val viewModel by viewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()

        setupObserver()
    }

    private fun setupBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    private fun setupObserver() {
        viewModel.state.observe(this) {
            when (it) {
                AuthState.EmptyNickname,
                AuthState.EmptyPassword,
                is AuthState.Error -> Unit
                AuthState.LogIn -> {
                }
            }
        }
    }
}
