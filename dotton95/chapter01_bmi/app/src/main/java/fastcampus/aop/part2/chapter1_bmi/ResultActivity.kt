package fastcampus.aop.part2.chapter1_bmi

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import fastcampus.aop.part2.chapter1_bmi.databinding.ActivityMainBinding
import fastcampus.aop.part2.chapter1_bmi.databinding.ActivityResultBinding
import kotlin.math.pow

class ResultActivity : BaseActivity<ActivityResultBinding>(R.layout.activity_result) {
    var bmi:String? = null
    var text:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.result = this

        val input_height  = intent.getIntExtra("input_height", 0)
        val input_weight  = intent.getIntExtra("input_weight", 0)

        //val bmi = weight / Math.pow(height / 100.0, 2.0)
        val bmi1 = input_weight / (input_height/100.0).pow(2.0)
        val result1 = when {
            bmi1 >= 35.0 -> "고도 비만"
            bmi1 >= 30.0 -> "중정도 비만"
            bmi1 >= 25.0 -> "경도 비만"
            bmi1 >= 23.0 -> "과체중"
            bmi1 >= 18.5 -> "정상체중"
            else -> "저체중"
        }
        bmi = String.format("%.1f",bmi1)
        text = result1


    }
}