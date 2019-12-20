package com.example.loginapi.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.loginapi.data.db.entities.User

/**
 * Base de données
 */
@Database(
    entities = [User::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    // On utilise cette db pour obtenir le UserDao puis à partir de ce UserDao on peut enregistrer
    // le user dans la db
    abstract fun getUserDao(): UserDao

    companion object {
        // disponible partout
        @Volatile
        private var instance: AppDatabase? = null
        // utilisé pour ne pas créer 2 instances de la db
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance?:buildDatabase(context).also {
                instance = it
            }
        }

        // construit la db
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "MyDatabase.db"
            ).build()
    }
}