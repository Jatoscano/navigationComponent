package com.torres.myapplication.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.widget.addTextChangedListener
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.snackbar.Snackbar
import com.torres.myapplication.R
import com.torres.myapplication.databinding.ActivityConstrainBinding
import com.torres.myapplication.ui.fragments.FavoritesFragment
import com.torres.myapplication.ui.fragments.ListarNews

class ConstrainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConstrainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConstrainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListeners()

    }


    private fun initListeners() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.listarItem -> {

                    true
                }

                R.id.FavItem -> {

                    true
                }

                R.id.NoFavItem -> {

                    true
                }

                else -> false
            }
        }
    }


}