package fastcampus.aop.part2.chapter1_bmi


import android.os.Bundle
import fastcampus.aop.part2.chapter1_bmi.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            vm = Vm()
        }
    }
}