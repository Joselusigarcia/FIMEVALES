package com.example.fimevales

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.fimevales.databinding.CodigoQrBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

class CODIGO_QR : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation")
    private lateinit var binding: CodigoQrBinding
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CodigoQrBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var ivCodigoQR: ImageView = findViewById(R.id.ivCodigoQR)
        var etDatos: EditText = findViewById(R.id.etDatos)
        var btnGenerar: Button = findViewById(R.id.btnGenerar)

        btnGenerar.setOnClickListener(View.OnClickListener {

            try {

                var barcodeEncoder: BarcodeEncoder = BarcodeEncoder()
                var bitmap: Bitmap = barcodeEncoder.encodeBitmap(
                    etDatos.text.toString(),
                    BarcodeFormat.QR_CODE,
                    750,
                    750

                )
                    ivCodigoQR.setImageBitmap(bitmap)

            }catch (e: Exception){

                e.printStackTrace()
            }


        })
        //setuo
        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")
        setup(email ?:"", provider ?:"")

    }

    private fun setup(email:String,provider: String)
    {
        val mail = findViewById<TextView>(R.id.showemail4)
        mail.text  = email
    }
}

