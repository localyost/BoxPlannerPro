package com.localyost.boxplannerpro

import android.os.Bundle
import androidx.activity.ComponentActivity
import kotlin.math.log

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("test", false).apply();
        val pref = sharedPreferences.getBoolean("test", false);
        println(pref)
    }
}