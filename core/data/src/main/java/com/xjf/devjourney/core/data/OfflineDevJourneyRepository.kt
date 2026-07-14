package com.xjf.devjourney.core.data

import com.xjf.devjourney.core.data.model.asEntity
import com.xjf.devjourney.core.data.model.asExternalModel
import com.xjf.devjourney.core.database.dao.TaskDao
import com.xjf.devjourney.core.database.model.TaskEntity
import com.xjf.devjourney.core.model.LearningTask
import com.xjf.devjourney.core.model.LearningTopic
import com.xjf.devjourney.core.model.StudyNote
import com.xjf.devjourney.core.model.TaskStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OfflineDevJourneyRepository @Inject constructor(
    private val taskDao: TaskDao,
) : DevJourneyRepository {
    override val topics: Flow<List<LearningTopic>> = flowOf(
        listOf(
            LearningTopic(
                id = "kotlin",
                title = "Kotlin Core",
                description = "语法、协程、Flow 与类型系统基础",
                progress = 0.35f,
                taskCount = 8,
                completedTaskCount = 3,
            ),
            LearningTopic(
                id = "compose",
                title = "Jetpack Compose",
                description = "声明式 UI、状态提升、Material 3 与导航",
                progress = 0.2f,
                taskCount = 10,
                completedTaskCount = 2,
            ),
            LearningTopic(
                id = "architecture",
                title = "App Architecture",
                description = "分层架构、Repository、离线优先与测试",
                progress = 0.1f,
                taskCount = 12,
                completedTaskCount = 1,
            ),
        ),
    )
    override val tasks: Flow<List<LearningTask>> = taskDao.observeTasks().map { entities ->
        entities.map {
            it.asExternalModel()
        }
    }
    override val notes: Flow<List<StudyNote>> = flowOf(
        listOf(
            StudyNote(
                id = "note-1",
                title = "为什么 UI 层只暴露 UiState",
                excerpt = "让 Compose 页面只关心渲染和事件，把业务变化收敛到 ViewModel。",
                tags = listOf("Compose", "Architecture"),
            ),
            StudyNote(
                id = "note-2",
                title = "Repository 的边界",
                excerpt = "Repository 负责协调本地、远程数据源，并向上层暴露稳定的数据流。",
                tags = listOf("Data", "Flow"),
            ),
        ),
    )

    override suspend fun addTask(title: String, topic: String, status: TaskStatus) {
        taskDao.upsertTask(
            TaskEntity(
                id = "task-${System.currentTimeMillis()}",
                title = title,
                topic = topic,
                status = status,
            )
        )
    }

    override suspend fun updateTask(learningTask: LearningTask) {
        taskDao.upsertTask(learningTask.asEntity())
    }

    override suspend fun deleteTask(taskId: String) {
        taskDao.deleteTaskById(taskId)
    }

    override suspend fun updateTaskStatus(taskId: String, status: TaskStatus) {
        taskDao.updateTaskStatus(taskId, status)
    }

}