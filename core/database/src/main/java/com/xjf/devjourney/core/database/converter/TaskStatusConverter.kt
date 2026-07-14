package com.xjf.devjourney.core.database.converter

import androidx.room.TypeConverter
import com.xjf.devjourney.core.model.TaskStatus

class TaskStatusConverter {

    @TypeConverter
    fun fromStatus(status: TaskStatus): String = status.name

    @TypeConverter
    fun toStatus(value: String): TaskStatus = TaskStatus.valueOf(value)
}