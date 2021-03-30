package fastcampus.aop.part2.chapter1_bmi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edt_height: EditText = findViewById(R.id.edt_height);//1
        val edt_weight = findViewById<EditText>(R.id.edt_weight);//2

        val btn_result = findViewById<Button>(R.id.btn_result);

        btn_result.setOnClickListener {
            Log.d("MainActivity", "btn_result 이 클릭되었습니다.")

            if (edt_height.text.isEmpty() || edt_weight.text.isEmpty()) {
                Toast.makeText(this, "빈 값이 있습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val input_height: Int = edt_height.text.toString().toInt()
            val input_weight: Int = edt_weight.text.toString().toInt()
            //Log.d("MainActivity", "height : $input_height weight : $input_weight ")

            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("input_height", input_height);
            intent.putExtra("input_weight", input_weight);
            startActivity(intent)
        }
    }
}