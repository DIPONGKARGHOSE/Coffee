package com.example.coffee.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.coffee.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {
    lateinit var binding: ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityIntroBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.Startbutton.setOnClickListener{
            startActivity(Intent(this@IntroActivity, MainpartActivity::class.java))
       }



    }
}