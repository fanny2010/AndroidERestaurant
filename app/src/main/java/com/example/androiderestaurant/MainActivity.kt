package com.example.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.androiderestaurant.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttonsListener()

        Log.d("lifeCycle", "MainActivity onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d("lifeCycle", "MainActivity onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("lifeCycle", "MainActivity onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("lifeCycle", "MainActivity onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("lifeCycle", "MainActivity onDestroy")
    }

    private fun buttonsListener() {
        binding.starterButton.setOnClickListener {
          showCategory(Category.STARTER)
        }
        binding.mainButton.setOnClickListener {
            showCategory(Category.MAIN)
        }
        binding.finishButton.setOnClickListener {
            showCategory(Category.DESSERT)
        }
    }
    private fun showCategory(category: Category) {
        val intent = Intent(this, MenuListActivity::class.java)
        intent.putExtra(MenuListActivity.extraKey, category)
        startActivity(intent)
    }
}


