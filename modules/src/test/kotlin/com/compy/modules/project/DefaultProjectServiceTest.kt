package com.compy.modules.project

import com.compy.shared.project.ProjectInfo
import com.compy.shared.project.ProjectRepository
import com.compy.shared.project.ProjectType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Path

class DefaultProjectServiceTest {
    private fun tempRepo(): Pair<DefaultProjectService, Path> {
        val tempDir = Files.createTempDirectory("projtest")
        val repoPath = tempDir.resolve("projects.json")
        val repo = ProjectRepository(repoPath)
        val service = DefaultProjectService(repo)
        return service to tempDir
    }

    @Test
    fun `add and list project`() {
        val (service, dir) = tempRepo()
        service.addProject(ProjectInfo("demo", dir, ProjectType.SPRING_BOOT))
        val list = service.listProjects()
        assertEquals(1, list.size)
        assertEquals("demo", list[0].name)
    }

    @Test
    fun `add unsupported type`() {
        val (service, _) = tempRepo()
        assertThrows(IllegalArgumentException::class.java) {
            service.addProject(ProjectInfo("bad", Path.of("."), ProjectType.UNKNOWN))
        }
    }

    @Test
    fun `use unknown project`() {
        val (service, _) = tempRepo()
        assertThrows(IllegalArgumentException::class.java) {
            service.useProject("missing")
        }
    }

    @Test
    fun `use project`() {
        val (service, dir) = tempRepo()
        service.addProject(ProjectInfo("demo", dir, ProjectType.SPRING_BOOT))
        service.useProject("demo")
        val current = service.currentProject()
        assertNotNull(current)
        assertEquals("demo", current!!.name)
    }
}
