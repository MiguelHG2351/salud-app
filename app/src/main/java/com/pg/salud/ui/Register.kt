package com.pg.salud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.core.util.PatternsCompat
import com.pg.salud.R
import com.pg.salud.databinding.RegisterBinding
import java.util.regex.Pattern
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.firestore.FirebaseFirestore

class Register : AppCompatActivity() {
    private lateinit var binding: RegisterBinding
   // private val firestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RegisterBinding.inflate(layoutInflater)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContentView(binding.root)

        val botonRegistrar: Button = findViewById(R.id.register_button)
        botonRegistrar.setOnClickListener {
            validate()
        }
        supportActionBar?.hide()
    }

    private fun validate() {
        if (verificacion() == false) {
            //Toast.makeText(this,"Ha ocurrido un error",Toast.LENGTH_LONG).show()
            return
        }
        else {
            /*
             FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(
                    binding.mailInput.text.toString(),
                    binding.passwordInput.text.toString()
                ).addOnCompleteListener {
                    firestore.collection("users").add(
                        mapOf(
                            "name" to binding.nameInput.text.toString(),
                            "username" to binding.usernameInput.text.toString(),
                            "email" to binding.mailInput.text.toString(),
                            "signature" to "Firma no configurada"
                        )
                    ).addOnSuccessListener { documentReference ->
                        println("Se agrego a firestore")
                        Toast.makeText(this, "Registrado correctamente", Toast.LENGTH_LONG).show()
                    }.addOnFailureListener { e ->
                        Toast.makeText(this, "Correo ya en uso", Toast.LENGTH_LONG).show()
                        println("Error adding document ${e.message.toString()}")
                    }

                    if (it.isSuccessful) {
                        startActivity(Intent(this, inApp::class.java))
                        finish()
                    } else {
                        println(it.exception)
                        //Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_LONG).show()
                    }
                }

             */
        }
    }

    private fun validarPassYMail(): Int {

        val email = binding.mailInput.text.toString()
        val password = binding.passwordInput.text.toString()
        // Patrón con expresiones regulares
        val passwordRegex = Pattern.compile(
            "^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=\\S+$)" +           //no white spaces
                    ".{6,}" +               //at least 6 characters
                    "$"
        )
        //Validaciones
        if (email.isEmpty()) {
            return 1 //email vacio
        } else if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
            return 2 //email no cumple
        } else if (password.isEmpty()) {
            return 3 // pass vacio
        }
        else if(password.length<6) {
            return 9
        }
        else if (!passwordRegex.matcher(password).matches()) {//numeros
            return 4
        }
        else {
            return 0 //todo ok
        }
    }

    private fun verificacion(): Boolean {
        var codigo = validarPassYMail()
        var salida = false
        when (codigo) {
            1 -> Toast.makeText(this, "Escriba un correo", Toast.LENGTH_LONG).show()
            2 -> Toast.makeText(this, "Email invalido", Toast.LENGTH_LONG).show()
            3 -> Toast.makeText(this, "Escriba una contraseña", Toast.LENGTH_LONG).show()
            4 -> Toast.makeText(this, "Contraseña muy debil", Toast.LENGTH_LONG).show()
            9 -> Toast.makeText(this, "Contraseña necesita al menos 6 caracteres", Toast.LENGTH_LONG).show()

            /*
              5 -> Toast.makeText(this, "Contraseña necesita al menos 1 simbolo", Toast.LENGTH_LONG).show()
              6 -> Toast.makeText(this, "Contraseña necesita al menos 1 minuscula", Toast.LENGTH_LONG).show()
              7 -> Toast.makeText(this, "Contraseña necesita al menos 1 mayuscula", Toast.LENGTH_LONG).show()
              8 -> Toast.makeText(this, "Contraseña necesita al menos 1 numero", Toast.LENGTH_LONG).show()
              10 ->Toast.makeText(this, "Contraseña no puede contener espacios", Toast.LENGTH_LONG).show()
              11 ->Toast.makeText(this, "Error desconocido", Toast.LENGTH_LONG).show()
            */
            0 -> salida = true
         }
        println("verificacion:" + salida)
        println("Codigo:" + codigo)
        return salida
    }

    //codigos de error:
    // 1 email vacio
    // 2 email no cumple

    // 3 pass vacio
    // 4 pass debil

}