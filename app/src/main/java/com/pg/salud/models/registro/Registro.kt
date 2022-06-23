package com.pg.salud.models.registro.task

data class Registro(val username: String, val name: String, val email: String, val signature: String)
data class RegistroList(val items: ArrayList<Registro>)