package com.example.mydemoapp.database

import android.content.Context
import androidx.room.*

@androidx.room.Database(
    entities = [UserEntityItem::class, PostEntityItem::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getSimpleDao(): UserDao
    abstract fun getPostDao(): PostDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        // create an instance of room database or use previously created instance
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "MyDatabase.db"
            ).allowMainThreadQueries().build()
    }
}

