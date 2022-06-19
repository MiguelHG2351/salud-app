package com.pg.salud

import android.content.Intent
//import com.google.firebase.auth.FirebaseAuth
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.pg.salud.databinding.LoginBinding

class Login : AppCompatActivity() {
    private lateinit var binding: LoginBinding

   // private val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginBinding.inflate(layoutInflater)

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener {
            // check login credentials
            val name = binding.Name.text.toString()
            val passowrd = binding.Password.text.toString()

           /* if(!name.isEmpty()&&!passowrd.isEmpty()){
                firebaseAuth.signInWithEmailAndPassword(name, passowrd)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d("Login", "signInWithEmail:success")
                            val user = firebaseAuth.currentUser
                            val intent = Intent(this, inApp::class.java)
                            Toast.makeText(baseContext, "Bienvenido/a",
                                Toast.LENGTH_SHORT).show()
                            startActivity(intent)
                            finish()
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w("Login", "signInWithEmail:failure", task.exception)
                            Toast.makeText(baseContext, "Correo o contraseña incorrecta",
                                Toast.LENGTH_SHORT).show()

                        }
                    }
            }
            else{
                Toast.makeText(baseContext, "No dejar campos vacios",
                    Toast.LENGTH_SHORT).show()
            }
            */
        }
        //Abre pantalla de registro
        binding.buttonRegister.setOnClickListener {
            startActivity(Intent(this,Register::class.java))

        }

        supportActionBar?.hide()
    }

}