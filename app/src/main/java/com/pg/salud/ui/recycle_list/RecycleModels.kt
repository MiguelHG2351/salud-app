package com.pg.salud.ui.recycle_list

data class RecyclerList(val items: ArrayList<RecyclerData>)
data class RecyclerData(val name : String, val description: String, val owner: Owner)
data class Owner(val avatar_url: String)
data class UserList (val items: ArrayList<User>)
data class User (val name: String, val username: String, val signature: String, val email: String)