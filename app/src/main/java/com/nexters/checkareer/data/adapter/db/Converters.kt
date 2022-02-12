package com.nexters.checkareer.data.adapter.db

import androidx.room.TypeConverter
import com.nexters.checkareer.presentation.ui.createprofile.model.CategorySelect
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class Converters {

    @TypeConverter
    fun fromString(value: String): List<CategorySelect> {
        val listType: Type = object : TypeToken<List<CategorySelect?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<CategorySelect>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

}