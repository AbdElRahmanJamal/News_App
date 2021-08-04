package com.app.newsapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.app.newsapp.R
import com.app.newsapp.databinding.ActivityNewsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {

    private lateinit var activityNewsBinding: ActivityNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityNewsBinding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(activityNewsBinding.root)
        val navController =
            (supportFragmentManager.findFragmentById(R.id.news_nav_host_fragment) as NavHostFragment).navController
        activityNewsBinding.newsBottomNavigationView.setupWithNavController(navController)
        activityNewsBinding.newsBottomNavigationView.setOnItemReselectedListener {
        }
    }
}

