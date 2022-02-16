package com.nackun.study.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.nackun.bmi.BmiActivity
import com.nackun.study.R
import com.nackun.study.databinding.ActivityMainBinding
import com.nackun.todo.presentation.list.ListActivity

class MainActivity : AppCompatActivity() {
    private lateinit var dataBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        dataBinding.run {
            buttonMainBmi.setOnClickListener {
                startActivity(Intent(this@MainActivity, BmiActivity::class.java))
            }
            buttonMainTodo.setOnClickListener {
                startActivity(Intent(this@MainActivity, ListActivity::class.java))
            }
        }
    }
}
