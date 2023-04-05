package com.example.million

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class WinnerActivity : AppCompatActivity() {
    private lateinit var btnClose: Button
    private lateinit var tvWinValue: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_winner)

        btnClose = findViewById(R.id.btnClose)
        tvWinValue = findViewById(R.id.tvWinValue)


        btnClose.setOnClickListener{
            finish()
        }

        val cash = intent.getIntExtra("cash", 0)
        tvWinValue.text = cash.toString()
    }
}