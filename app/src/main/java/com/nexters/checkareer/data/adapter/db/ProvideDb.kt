package com.nexters.checkareer.data.adapter.db

import android.content.Context
import androidx.room.Room


fun provideDb(context: Context): AppDatabase =
    Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DB_NAME).build()

fun provideDao(database: AppDatabase) = database.userDao()