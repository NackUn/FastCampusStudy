package fastcampus.aop.part2.chapter1_bmi

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Vm() : ViewModel(){
    val height = MutableLiveData<String>()
    val weight = MutableLiveData<String>()

    fun btnClick(view: View){
        Log.d("MainActivity", "btn_result 이 클릭되었습니다.")

        if (height.value.equals("")|| weight.value.equals("")) {
            Toast.makeText(view.context, "빈 값이 있습니다.", Toast.LENGTH_SHORT).show()
            return@btnClick
        }
        val input_height: Int = height.value.toString().toInt()
        val input_weight: Int = weight.value.toString().toInt()

        val intent = Intent(view.context, ResultActivity::class.java)
        intent.putExtra("input_height", input_height);
        intent.putExtra("input_weight", input_weight);
        view.context.startActivity(intent)
    }
}