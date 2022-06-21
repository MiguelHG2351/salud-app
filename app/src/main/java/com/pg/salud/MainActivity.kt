package com.pg.salud

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.pg.salud.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val db = FirebaseFirestore.getInstance()

    @SuppressLint("RestrictedApi")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        //fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(binding.root)
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_IMC, R.id.navigation_notifications,R.id.navigation_profile
            )
        )
        supportActionBar?.hide()
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener{ controller, destination, arguments ->
//            if(destination.id == R.id.wordDetailsFragment) {
//                actionBar?.hide()
//            } else {
//                actionBar?.show()
//            }
            if(destination.label == "IMC") {
//                getMethod()
                println("IMC :D")
            }
        }

    }
    override fun onBackPressed() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Salir")
        builder.setMessage("¿Desea cerrar Bookavo?")
            .setPositiveButton(
                "Salir"
            ) { dialog, which ->
                Toast.makeText(applicationContext, "Saliste", Toast.LENGTH_SHORT).show()
                finish()
            }
            .setNegativeButton(
                "Cancelar"
            ) { dialog, which ->
                //Toast.makeText(applicationContext, "Cancelado", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            .setCancelable(false)
            .show()
    }
    fun showAlertDialog(view: View){
        val alert: AlertDialog.Builder = AlertDialog.Builder(this)
        alert.setTitle("Actualizar firma")
        alert.setMessage("Introduzca su firma nueva :")

        //edit text
        val input = EditText(this)
        alert.setView(input)


        alert.setPositiveButton("Guardar", DialogInterface.OnClickListener { dialog, whichButton ->
            val value = input.text.toString()
            changeSignature(value)

            //Toast.makeText(this, value, Toast.LENGTH_SHORT).show()
            return@OnClickListener
        })

        alert.setNegativeButton("Cancelar",
            DialogInterface.OnClickListener { dialog, which ->
                return@OnClickListener
            })
        alert.show()
    }

    fun showAlertDialog2(view: View){
        val alert: AlertDialog.Builder = AlertDialog.Builder(this)
        alert.setTitle("Cambio de nombre")
        alert.setMessage("Introduzca su nuevo nombre :")

        //edit text
        val input = EditText(this)
        alert.setView(input)


        alert.setPositiveButton("Guardar", DialogInterface.OnClickListener { dialog, whichButton ->
            val value = input.text.toString()
            changeUsername(value)

            //Toast.makeText(this, value, Toast.LENGTH_SHORT).show()
            return@OnClickListener
        })

        alert.setNegativeButton("Cancelar",
            DialogInterface.OnClickListener { dialog, which ->
                return@OnClickListener
            })
        alert.show()
    }
    private fun changeSignature(firma:String){
        //buscar correo del usuario actual
        val correoActual = FirebaseAuth.getInstance().currentUser?.email
        //buscar usuarios en la db
        db.collection("users").get().addOnSuccessListener { result ->
            for (user in result) {
                //extraer email de usuario en iteracion
                val category = user.data.get("email").toString()
                val id = user.id.toString()
                //si el email coincide con el del actual se ponen sus datos en pantalla
                if(category.equals(correoActual)){
                    db.collection("users").document(id).update("signature", firma)
                }
            }
        }
    }

    private fun changeUsername(nombre:String){
        //buscar correo del usuario actual
        val correoActual = FirebaseAuth.getInstance().currentUser?.email
        //buscar usuarios en la db
        db.collection("users").get().addOnSuccessListener { result ->
            for (user in result) {
                //extraer email de usuario en iteracion
                val category = user.data.get("email").toString()
                val id = user.id.toString()
                //si el email coincide con el del actual se ponen sus datos en pantalla
                if(category.equals(correoActual)){
                    db.collection("users").document(id).update("name", nombre)
                }
            }
        }
    }
//    fun getMethod() {
//
//        // Create Retrofit
//        val retrofit = Retrofit.Builder()
//            .baseUrl("http://dummy.restapiexample.com")
//            .build()
//
//        // Create Service
//        val service = retrofit.create(APIServices::class.java)
//
//        CoroutineScope(Dispatchers.IO).launch {
//            /*
//             * For @Query: You need to replace the following line with val response = service.getEmployees(2)
//             * For @Path: You need to replace the following line with val response = service.getEmployee(53)
//             */
//
//            // Do the GET request and get response
//            val response = service.getUsers()
//
//            withContext(Dispatchers.Main) {
//                if (response.isSuccessful) {
//
//                    // Convert raw JSON to pretty JSON using GSON library
//                    val gson = GsonBuilder().setPrettyPrinting().create()
//                    val prettyJson = gson.toJson(
//                        JsonParser.parseString(
//                            response.body()
//                                ?.string() // About this thread blocking annotation : https://github.com/square/retrofit/issues/3255
//                        )
//                    )
//                    Log.d("Pretty Printed JSON :", prettyJson)
//
////                    val intent = Intent(this@MainActivity, IMC::class.java)
////                    intent.putExtra("json_results", prettyJson)
////                    FragmentRegistroImcBinding.inflate(layoutInflater, )
//                } else {
//                    Log.e("RETROFIT_ERROR", response.code().toString())
//
//                }
//            }
//        }
//    }
}