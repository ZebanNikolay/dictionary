package com.ncbs.dictionary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.ncbs.dictionary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.toolbar.setOnMenuItemClickListener { item ->
            return@setOnMenuItemClickListener when (item.itemId) {
                R.id.application -> {
                    println("Нажат элемент about")
                    true
                }
                R.id.word -> {
                    println("Нажат элемент word")
                    true
                }
                R.id.language -> {
                    println("Нажат элемент language")
                    true
                }
                else -> false
            }
        }

        setContentView(binding.root)
    }
}
