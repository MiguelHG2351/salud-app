package com.pg.salud.ui.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import com.pg.salud.Login
import com.pg.salud.databinding.ProfileBinding

class Profile: Fragment() {
    private var _binding: ProfileBinding? = null
    private val binding get() = _binding!!
   // private val firebaseAuth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    //buscar correo del usuario actual
   // val correoActual = FirebaseAuth.getInstance().currentUser?.email

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       /* binding.socialTwitter.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.twitter.com"))
            startActivity(browserIntent)
        }*/
        binding.closeSession.setOnClickListener {
         //   firebaseAuth.signOut()
            startActivity(Intent(context, Login::class.java))
            activity?.finish()
        }
        binding.botonUpdate.setOnClickListener{
                updateSignature()
        }



        //poner su correo en el segundo campo (username)
        //binding.UserName.setText(correoActual)

        //buscar usuarios en la db
        db.collection("users").get().addOnSuccessListener { result ->
            for (user in result) {
                //extraer email de usuario en iteracion
                val category = user.data.get("email").toString()
                //si el email coincide con el del actual se ponen sus datos en pantalla
              /*  if(category.equals(correoActual)){
                  binding.NombreUsuario.setText(user.data.get("name").toString())
                  binding.firma.setText(user.data.get("signature").toString())
                }*/
            }

        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateSignature(){
      /*  //buscar usuarios en la db
        db.collection("users").get().addOnSuccessListener { result ->
            for (user in result) {
                val category = user.data.get("email").toString()
                if(category.equals(correoActual)){
                    binding.NombreUsuario.setText(user.data.get("name").toString())
                    binding.firma.setText(user.data.get("signature").toString())
                }
            }

        }*/
    }



}