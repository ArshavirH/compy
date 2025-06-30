package com.compy.modules.project

import com.compy.kernel.project.ProjectService
import com.compy.shared.project.ProjectInfo
import com.compy.shared.project.ProjectRepository
import com.compy.shared.project.ProjectType

class DefaultProjectService(
    private val repository: ProjectRepository = ProjectRepository()
) : ProjectService {

    override fun addProject(info: ProjectInfo) {
        if (info.type != ProjectType.SPRING_BOOT) {
            throw IllegalArgumentException("Unsupported project type: ${'$'}{info.type}")
        }
        repository.add(info)
    }

    override fun useProject(name: String) {
        repository.use(name)
    }

    override fun listProjects(): List<ProjectInfo> = repository.list()

    override fun currentProject(): ProjectInfo? = repository.current()
}
