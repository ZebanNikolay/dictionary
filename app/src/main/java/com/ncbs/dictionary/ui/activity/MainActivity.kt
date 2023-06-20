package com.ncbs.dictionary.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.annotation.MenuRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.ncbs.dictionary.R
import com.ncbs.dictionary.databinding.ActivityMainBinding
import com.ncbs.dictionary.domain.Language
import java.util.Locale

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
                    val intent = Intent(this, AboutActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.word -> {
                    println("Нажат элемент word")
                    true
                }

                R.id.language -> {
                    showMenu(
                        binding.toolbar.findViewById(R.id.language),
                        R.menu.popup_menu
                    )
                    true
                }

                else -> false
            }
        }
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)
    }

    private fun showMenu(v: View, @MenuRes menuRes: Int) {
        val popup = PopupMenu(this, v)
        popup.menuInflater.inflate(menuRes, popup.menu)

        popup.setOnMenuItemClickListener { menuItem: MenuItem ->
            return@setOnMenuItemClickListener when (menuItem.itemId) {
                R.id.ru_language -> {
                    Language("Russian", Locale("ru"))
                    true
                }

                R.id.nv_language -> {
                    Language("Nivh", Locale("nv"))
                    true
                }

                R.id.en_language -> {
                    Language("English", Locale("en"))
                    true
                }

                else -> false
            }
        }
        popup.show()
    }
}