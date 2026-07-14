package com.xjf.devjourney.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.xjf.devjourney.core.database.converter.TaskStatusConverter
import com.xjf.devjourney.core.database.dao.TaskDao
import com.xjf.devjourney.core.database.model.TaskEntity

@Database(
    entities = [TaskEntity::class],
    version = 1,
    exportSchema = true,
)
@TypeConverters(TaskStatusConverter::class)
abstract class DevJourneyDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}