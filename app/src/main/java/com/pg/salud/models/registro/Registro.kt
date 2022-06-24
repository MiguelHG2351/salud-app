package com.pg.salud.models.registro.task

data class Registro(val imc: Double, val diff: Double, val weight: Double, val createdAt: Time)
data class RegistroList(val id: String, val task: Any?,
                        val registro: List<Registro>,
                        val name: String, val height: Double,
                        val remaining: Double, val objective: Int,
                        val current: Double,
                        val user: String)
data class Time(val seconds: Long, val nanoseconds: Long)

data class Table(val name: String, val user: String, val objective: Double, val remaining: Double, val current: Double, val height: Double)

data class RegistroForm(val id: String, val diff: Double, val height: Double, val weight: Double)