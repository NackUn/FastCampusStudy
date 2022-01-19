package com.example.appstudy.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.appstudy.R
import com.example.appstudy.bmi.BmiActivity
import com.example.appstudy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var dataBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        dataBinding.buttonMainBmi.setOnClickListener {
            startActivity(Intent(this, BmiActivity::class.java))
        }
    }
}
