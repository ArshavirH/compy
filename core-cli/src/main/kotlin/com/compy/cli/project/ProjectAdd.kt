package com.compy.cli.project

import com.compy.modules.project.DefaultProjectService
import com.compy.shared.project.ProjectInfo
import com.compy.shared.project.ProjectType
import picocli.CommandLine
import java.nio.file.Path
import java.util.concurrent.Callable

@CommandLine.Command(name = "add", description = ["Add a project to the local index"])
class ProjectAdd : Callable<Int> {
    @CommandLine.Parameters(index = "0", description = ["Project name"])
    lateinit var name: String

    @CommandLine.Option(names = ["--path"], required = true, description = ["Path to the project"])
    lateinit var path: Path

    @CommandLine.Option(names = ["--type"], required = true, description = ["Project type"])
    lateinit var type: String

    override fun call(): Int {
        val service = DefaultProjectService()
        val projectType = ProjectType.fromString(type)
            ?: run {
                println("❌ Unsupported project type: $type")
                return 1
            }
        return try {
            service.addProject(ProjectInfo(name, path, projectType))
            println("✅ Project '$name' added")
            0
        } catch (e: Exception) {
            println("❌ ${'$'}{e.message}")
            1
        }
    }
}
