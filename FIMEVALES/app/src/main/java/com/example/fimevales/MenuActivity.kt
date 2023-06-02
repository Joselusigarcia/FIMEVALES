package com.example.fimevales

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnf1: Button = findViewById<Button>(R.id.btndesayuno)
        btnf1.setOnClickListener({
            val intent: Intent = Intent(this, DesayunoActivity::class.java)
            startActivity(intent)
        })

        val btnf2: Button = findViewById<Button>(R.id.btncomida)
        btnf2.setOnClickListener({
            val intent: Intent = Intent(this, ComidaActivity::class.java)
            startActivity(intent)
        })

        val btnf3: Button = findViewById<Button>(R.id.btncena)
        btnf3.setOnClickListener({
            val intent: Intent = Intent(this, CenaActivity::class.java)
            startActivity(intent)
        })

    }
}