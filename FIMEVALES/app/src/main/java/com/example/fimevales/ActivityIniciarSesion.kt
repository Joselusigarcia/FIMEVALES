package com.example.fimevales
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.fimevales.databinding.ActivityIniciarsesionBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class ActivityIniciarSesion : AppCompatActivity() {
    private lateinit var binding: ActivityIniciarsesionBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIniciarsesionBinding.inflate(layoutInflater)
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

        val contra2 = findViewById<EditText>(R.id.contra3)
        val mail2 = findViewById<TextView>(R.id.correo3)
        val Iniciar2 = findViewById<Button>(R.id.iniciarsesion1)
        Iniciar2.setOnClickListener({
                FirebaseAuth.getInstance().signInWithEmailAndPassword(mail2.text.toString(),contra2.text.toString()).addOnCompleteListener(){
                    if(it.isSuccessful)
                    {
                        Toast.makeText(this@ActivityIniciarSesion,"Bienvenido!", Toast.LENGTH_SHORT).show()
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
        builder.setMessage("Se ha producido un error inciando sesión")
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
