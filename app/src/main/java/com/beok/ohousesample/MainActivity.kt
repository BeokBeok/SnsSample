package com.beok.ohousesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.beok.ohousesample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        setupListener()
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    private fun setupListener() {
        binding?.run {
            btnMainLogin.setOnClickListener {
                val navHostFragment = supportFragmentManager
                    .findFragmentById(R.id.fcv_main_nav_host) as NavHostFragment
                val navController = navHostFragment.navController
                if (navController.currentDestination?.label == getString(R.string.label_auth)) {
                    return@setOnClickListener
                }
                navController.navigate(MainFragmentDirections.actionAuth())
            }
        }
    }
}
