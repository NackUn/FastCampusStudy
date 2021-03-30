package fastcampus.aop.part2.chapter1_bmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlin.math.pow

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val input_height  = intent.getIntExtra("input_height", 0)
        val input_weight  = intent.getIntExtra("input_weight", 0)

        //val bmi = weight / Math.pow(height / 100.0, 2.0)
        val bmi = input_weight / (input_height/100.0).pow(2.0)
        val result = when {
            bmi >= 35.0 -> "고도 비만"
            bmi >= 30.0 -> "중정도 비만"
            bmi >= 25.0 -> "경도 비만"
            bmi >= 23.0 -> "과체중"
            bmi >= 18.5 -> "정상체중"
            else -> "저체중"
        }
        val tv_bmi = findViewById<TextView>(R.id.tv_bmi)
        val tv_result = findViewById<TextView>(R.id.tv_result)
//        tv_bmi.text = bmi.toString()
        tv_bmi.text = String.format("%.1f",bmi)
        tv_result.text = result

    }
}