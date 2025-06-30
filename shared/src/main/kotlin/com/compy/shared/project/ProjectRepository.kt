package com.compy.shared.project

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class ProjectRepository(private val file: Path = defaultFile()) {
    private val mapper = jacksonObjectMapper()

    companion object {
        fun defaultFile(): Path =
            Paths.get(System.getProperty("user.home"), ".compy", "projects.json")
    }

    private fun readIndex(): ProjectIndex {
        if (!Files.exists(file)) return ProjectIndex()
        return mapper.readValue(Files.readAllBytes(file), ProjectIndex::class.java)
    }

    private fun writeIndex(index: ProjectIndex) {
        Files.createDirectories(file.parent)
        Files.writeString(file, mapper.writeValueAsString(index))
    }

    fun add(project: ProjectInfo) {
        val index = readIndex()
        if (index.projects.containsKey(project.name)) {
            throw IllegalArgumentException("Project '${'$'}{project.name}' already exists")
        }
        index.projects[project.name] = project
        writeIndex(index)
    }

    fun list(): List<ProjectInfo> = readIndex().projects.values.toList()

    fun use(name: String) {
        val index = readIndex()
        if (!index.projects.containsKey(name)) {
            throw IllegalArgumentException("Project '$name' not found")
        }
        index.current = name
        writeIndex(index)
    }

    fun current(): ProjectInfo? {
        val index = readIndex()
        val currentName = index.current ?: return null
        return index.projects[currentName]
    }
}
