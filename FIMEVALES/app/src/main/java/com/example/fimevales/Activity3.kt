package com.example.fimevales
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.fimevales.databinding.Activity3Binding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class Activity3 : AppCompatActivity() {
    private lateinit var binding: Activity3Binding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Activity3Binding.inflate(layoutInflater)
        setContentView(binding.root)



        var BTNCREAR = findViewById<Button>(R.id.GUARDAR)


        //setuo
        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")
        setup(email ?:"", provider ?:"")
    }
    private fun setup(email:String, provider: String)
    {

        val contra1 = findViewById<EditText>(R.id.password)
        val mail = findViewById<TextView>(R.id.emailAuth)
        mail.text  = email
        val save = findViewById<Button>(R.id.GUARDAR)
        save.setOnClickListener({
            var MATRICULA = findViewById<EditText>(R.id.matricula)
            var rol= findViewById<EditText>(R.id.rol)
            if(MATRICULA.text.isNotEmpty() && rol.text.isNotEmpty()) {
                db.collection("Usuarios").document(email).set(
                    hashMapOf(
                        "provider" to provider,
                        "rol" to rol.text.toString(),
                        "Matricula" to MATRICULA.text.toString()
                    )
                )
                Toast.makeText(this@Activity3,"Datos Agregados, puedes Iniciar sesión!", Toast.LENGTH_SHORT).show()

            }
        })
        val Iniciar2 = findViewById<Button>(R.id.iniciarsesion1)
        Iniciar2.setOnClickListener({
                FirebaseAuth.getInstance().signInWithEmailAndPassword(mail.text.toString(),contra1.text.toString()).addOnCompleteListener(){
                    if(it.isSuccessful)
                    {
                        showHome(it.result?.user?.email ?:"", ProviderType.BASIC)
                    }
                    else
                    {
                        showAlert()
                    }
                }

        })
    }
    private fun showAlert()
    {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error: ")
        builder.setMessage("Se ha producido un error autenticando el usuario")
        builder.setPositiveButton("Okey", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
    private fun showHome(email:String, provider:ProviderType)
    {
        val Activity_4Intent = Intent(this, Activity4::class.java).apply {
            putExtra("email",email)
            putExtra("provider",provider.name)


        }
        startActivity(Activity_4Intent)
    }

}
