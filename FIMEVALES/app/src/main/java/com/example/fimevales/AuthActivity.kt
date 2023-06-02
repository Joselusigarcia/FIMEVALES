package com.example.fimevales

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)


        //setup
        setup()
    }
    private fun setup()
    {
        title = "Autenticacion"
        val autenticacion  = findViewById<Button>(R.id.registrar)
        val correo = findViewById<EditText>(R.id.correo)
        val contra = findViewById<EditText>(R.id.contra)

        autenticacion.setOnClickListener({
            if(correo.text.isNotEmpty() && contra.text.isNotEmpty())
            {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(correo.text.toString(),contra.text.toString()).addOnCompleteListener(){
                    if(it.isSuccessful)
                    {
                        showHome(it.result?.user?.email ?:"", ProviderType.BASIC)
                    }
                    else
                    {
                        showAlert()
                    }
                }
            }
        })
        /*val Iniciar1 = findViewById<Button>(R.id.Iniciar)
        Iniciar1.setOnClickListener({
            if(correo.text.isNotEmpty() && contra.text.isNotEmpty())
            {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(correo.text.toString(),contra.text.toString()).addOnCompleteListener(){
                    if(it.isSuccessful)
                    {
                        showHome(it.result?.user?.email ?:"", ProviderType.BASIC)
                    }
                    else
                    {
                        showAlert()
                    }
                }
            }
        })*/
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
        val Activity_3Intent = Intent(this, Activity3::class.java).apply {
            putExtra("email",email)
            putExtra("provider",provider.name)


        }
        startActivity(Activity_3Intent)
    }

}