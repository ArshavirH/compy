package com.compy.kernel.project

import com.compy.shared.project.ProjectInfo

interface ProjectService {
    fun addProject(info: ProjectInfo)
    fun useProject(name: String)
    fun listProjects(): List<ProjectInfo>
    fun currentProject(): ProjectInfo?
}
