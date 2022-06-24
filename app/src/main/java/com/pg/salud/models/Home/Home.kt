package com.pg.salud.models.Home

import com.pg.salud.models.registro.task.Registro
import com.pg.salud.models.registro.task.Time

data class TableHome(val id: String, val tasks: List<Tarea>,
                        val registro: List<Registro>,
                        val name: String, val height: Double,
                        val remaining: Double, val objective: Double,
                        val current: Double,
                        val user: String)

data class Tarea(val time: Int, val tag: String, val description: String, val title: String)
