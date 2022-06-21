package com.pg.salud.api


interface APIRequest {
//        fun getMethod(context: Context, destination:View) {
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
//            val response = service.getUsers(this@IMC, IMC)
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
//                    val intent = Intent(context, destination::class.java)
////                    intent.putExtra("json_results", prettyJson)
////                    this@MainActivity.startActivity(intent)
////                    return@withContext prettyJson
//
//                } else {
//
//                    Log.e("RETROFIT_ERROR", response.code().toString())
//
//                }
//            }
//        }
//    }
}