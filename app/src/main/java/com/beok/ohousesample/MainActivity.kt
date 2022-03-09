package com.beok.ohousesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.beok.auth.presentation.AuthViewModel
import com.beok.auth.presentation.model.AuthState
import com.beok.ohousesample.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private val viewModel by viewModels<AuthViewModel>()
    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.fcv_main_nav_host) as NavHostFragment)
            .navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        setupListener()
        setupObserver()
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
                    navController.popBackStack()
                    binding?.btnMainLogin?.run {
                        text = getString(R.string.logout)
                    }
                }
            }
        }
    }

    private fun setupListener() {
        binding?.btnMainLogin?.run {
            setOnClickListener {
                if (text == getString(R.string.logout)) {
                    text = getString(R.string.login)
                    return@setOnClickListener
                }
                if (navController.currentDestination?.label == getString(R.string.label_auth)) {
                    return@setOnClickListener
                }
                navController.navigate(MainFragmentDirections.actionAuth())
            }
        }
    }
}
