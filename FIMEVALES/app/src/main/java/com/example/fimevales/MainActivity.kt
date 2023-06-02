package com.example.fimevales

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    initEvents()
    }
    fun initEvents()
    {
        val buttonInitSesion = findViewById<Button>(R.id.INICIAR_SESION);
        buttonInitSesion.setOnClickListener{
            val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        }
        val buttonCreateSesion = findViewById<Button>(R.id.INICIASESION);
        buttonCreateSesion.setOnClickListener{
            val intent = Intent(this, ActivityIniciarSesion::class.java)
            startActivity(intent)
        }
    }
}