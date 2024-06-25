package com.torres.myapplication.logic.usercases.login

import com.torres.myapplication.data.local.database.entities.UsersDB
import com.torres.myapplication.data.local.repository.DataBaseRepository

class LoginUserpasswordUserCase(private val conn: DataBaseRepository) {

    suspend operator fun invoke(
        user: String,
        password: String
    ): Result<UsersDB> {

        return try {
            val us = conn.getUserDao().getUserByPassAndUser(user, password)
            Result.success(us)
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }
}