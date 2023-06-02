package com.example.fimevales

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

enum class ProviderType
{
    BASIC
}
class Activity4 : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_4)

        val btnMenu: Button = findViewById<Button>(R.id.Menu)
        btnMenu.setOnClickListener({
            val intent: Intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        })

        val btnRecetas: Button = findViewById<Button>(R.id.Recetas)
        btnRecetas.setOnClickListener({
            val intent: Intent = Intent(this, MainRecetasActivity::class.java)
            startActivity(intent)
        })

        val btnVales: Button = findViewById<Button>(R.id.QrVales)
        btnVales.setOnClickListener({
            val intent: Intent = Intent(this, CODIGO_QR::class.java)
            startActivity(intent)
        })




        //setuo
        val bundle = intent.extras
        val email = bundle?.getString("email")
        setup(email ?:"")
    }
    private fun setup(email:String)
    {
        val mail = findViewById<TextView>(R.id.showemail)
        mail.text  = email

        val logout = findViewById<Button>(R.id.logoutbtn)
        logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            Toast.makeText(this@Activity4,"Hasta luego", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    private fun showQR(email:String, provider:ProviderType)
    {
        val Activity_qrIntent = Intent(this, CODIGO_QR::class.java).apply {
            putExtra("email",email)
            putExtra("provider",provider.name)

        }
        startActivity(Activity_qrIntent)
    }
}