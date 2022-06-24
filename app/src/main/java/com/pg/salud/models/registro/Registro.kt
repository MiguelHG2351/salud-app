package com.pg.salud.models.registro.task

data class Registro(val imc: Double, val diff: Double, val weight: Double, val createdAt: Time)
data class RegistroList(val items: ArrayList<Registro>)
data class Time(val seconds: Long, val nanoseconds: Long)