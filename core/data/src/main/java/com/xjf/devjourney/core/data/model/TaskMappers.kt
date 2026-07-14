package com.xjf.devjourney.core.data.model

import com.xjf.devjourney.core.database.model.TaskEntity
import com.xjf.devjourney.core.model.LearningTask

fun TaskEntity.asExternalModel(): LearningTask {
    return LearningTask(
        id = id,
        title = title,
        topic = topic,
        status = status,
    )
}

fun LearningTask.asEntity(): TaskEntity {
    return TaskEntity(
        id = id,
        title = title,
        topic = topic,
        status = status,
    )
}