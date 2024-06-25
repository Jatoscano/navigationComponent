package com.torres.myapplication.logic.usercases.login

import com.torres.myapplication.data.local.database.entities.UsersDB
import com.torres.myapplication.data.local.repository.DataBaseRepository

class SaveUserpasswordUserCase(private val conn: DataBaseRepository) {

    suspend operator fun invoke(
        user: String,
        password: String
    ): Boolean {

        return try {
            val us = conn.getUserDao().saveUser(
                listOf(
                    UsersDB(name = user, password = password)
                )
            )
            true
        } catch (ex: Exception) {
            false
        }
    }
}