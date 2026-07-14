package com.xjf.devjourney.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.xjf.devjourney.core.model.TaskStatus

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey val id: String,
    val title: String,
    val topic: String,
    val status: TaskStatus,
)