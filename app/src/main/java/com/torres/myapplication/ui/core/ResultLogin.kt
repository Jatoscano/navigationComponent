package com.torres.myapplication.ui.core

sealed class ResultLogin {
    class Success(val msg: String) : ResultLogin()
    class Error(val code: Int, val msg: String) : ResultLogin()
}