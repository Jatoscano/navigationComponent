package com.torres.myapplication.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.torres.myapplication.databinding.ActivityBiometricBinding


class BiometricActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBiometricBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splash = installSplashScreen()

        binding = ActivityBiometricBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Thread.sleep(1000)
        splash.setKeepOnScreenCondition { false }

    }

}