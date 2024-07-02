package com.torres.myapplication.data.local.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.torres.myapplication.data.local.database.dao.UsersDAO
import com.torres.myapplication.data.local.database.entities.UsersDB
import kotlinx.coroutines.InternalCoroutinesApi


@Database(
    entities = [UsersDB::class],
    version = 1
)
abstract class DataBaseRepository : RoomDatabase() {
    abstract fun getUserDao(): UsersDAO

    @OptIn(InternalCoroutinesApi::class)
    companion object {

        @Volatile
        private var dbConnection: DataBaseRepository? = null

        fun getDBConnection(context: Context): DataBaseRepository {

            /*
            if (dbConnection == null){

                synchronized(this){
                    dbConnection =
                        Room.databaseBuilder(
                            context,
                            DataBaseRepository::class.java,
                            "Datos"
                        ).build()
                }
            }

            return dbConnection!!
             */


            return dbConnection?:synchronized(this){
                    val instance =
                        Room.databaseBuilder(
                            context,
                            DataBaseRepository::class.java,
                            "Datos"
                        ).build()
                dbConnection = instance
                dbConnection!!
                }
        }
    }
}