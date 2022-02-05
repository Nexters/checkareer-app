package com.nexters.checkareer.data.adapter.db

import android.content.Context
import androidx.room.Room


fun provideDb(context: Context): AppDataBase =
    Room.databaseBuilder(context, AppDataBase::class.java, AppDataBase.DB_NAME).build()

fun provideDao(database: AppDataBase) = database.userDao()