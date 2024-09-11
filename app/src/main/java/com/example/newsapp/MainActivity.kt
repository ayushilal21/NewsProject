package com.example.newsapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.newsapp.ui.theme.NewsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                // Splash Screen
                SplashScreen {
                    // Navigate to news screen after delay
                    startActivity(Intent(this, NewsScreenActivity::class.java))
                    finish()
                }
            }
        }
    }
}