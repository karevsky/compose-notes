package com.karevksy.domain.database.utils

import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {

    @TypeConverter
    fun toArrayList(json: String): ArrayList<String> {
        return Json.decodeFromString(json)
    }

    @TypeConverter
    fun toJson(items: ArrayList<String>): String {
        return Json.encodeToString(items)
    }
}