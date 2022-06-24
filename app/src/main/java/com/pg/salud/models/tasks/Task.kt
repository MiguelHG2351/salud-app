package com.pg.salud.models.tasks

data class TaskRequest(val id: String, val tag: String, val title: String, val description: String, val time: Int)
