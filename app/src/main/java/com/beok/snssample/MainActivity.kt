package com.beok.snssample

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.beok.auth.presentation.AuthFragment
import com.beok.auth.presentation.AuthViewModel
import com.beok.auth.presentation.model.AuthState
import com.beok.detail.presentation.CardDetailFragment
import com.beok.navigation.NavigationState
import com.beok.navigation.NavigationViewModel
import com.beok.snssample.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private val authViewModel by viewModels<AuthViewModel>()
    private val navViewModel by viewModels<NavigationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()
        setupListener()
        setupObserver()
        showContent()
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    private fun setupListener() {
        binding?.btnMainLogin?.run {
            setOnClickListener {
                if (text == getString(R.string.login)) {
                    navViewModel.navigate(navigationState = NavigationState.Auth)
                } else {
                    authViewModel.signOut()
                }
            }
        }
    }

    private fun showContent() {
        authViewModel.isSignIn()
    }

    private fun setupBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
    }

    private fun setupObserver() {
        authViewModel.state.observe(this) {
            val authState = it.getContentIfNotHandled() ?: return@observe
            when (authState) {
                AuthState.EmptyNickname,
                AuthState.EmptyPassword,
                is AuthState.Error -> Unit
                AuthState.LogIn -> {
                    binding?.btnMainLogin?.text = getString(R.string.logout)
                    supportFragmentManager.popBackStack()
                }
                AuthState.NotLogIn -> {
                    binding?.btnMainLogin?.text = getString(R.string.login)
                }
            }
        }
        navViewModel.state.observe(this) {
            val navigation = it.getContentIfNotHandled() ?: return@observe
            when (navigation) {
                is NavigationState.CardDetail -> {
                    addFragment(
                        fragment = CardDetailFragment.newInstance(id = navigation.id),
                        tag = CardDetailFragment.TAG
                    )
                }
                NavigationState.Auth -> {
                    if (supportFragmentManager.findFragmentByTag(AuthFragment.TAG) == null) {
                        addFragment(
                            fragment = AuthFragment.newInstance(),
                            tag = AuthFragment.TAG
                        )
                    }
                }
            }
        }
    }

    private fun addFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .add(R.id.fcv_main_nav_host, fragment, tag)
            .addToBackStack(null)
            .commit()
    }
}
