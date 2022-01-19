package com.example.appstudy.bmi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.appstudy.R
import com.example.appstudy.databinding.ActivityBmiBinding

class BmiActivity : AppCompatActivity() {
    private lateinit var dataBinding: ActivityBmiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_bmi)

        dataBinding.buttonBmiResult.setOnClickListener {
            Log.d("MainActivity", "ResultButton 이 클릭되었습니다.")

            if (dataBinding.editBmiHeight.text.isEmpty() || dataBinding.editBmiWeight.text.isEmpty()) {
                Toast.makeText(this, "빈 값이 있습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
//                어디 부분에서 리턴할지 알수 없어서 정해준다.
            }
//            이 아래로는 절대 빈 값이 올 수 없음.

            val height: Int = dataBinding.editBmiHeight.text.toString().toInt()
            val weight: Int = dataBinding.editBmiWeight.text.toString().toInt()

            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("height", height)
            intent.putExtra("weight", weight)

            startActivity(intent)
        }
    }
}