package com.compy.cli.project

import com.compy.modules.project.DefaultProjectService
import picocli.CommandLine
import java.util.concurrent.Callable

@CommandLine.Command(name = "ls", description = ["List registered projects"])
class ProjectList : Callable<Int> {
    override fun call(): Int {
        val service = DefaultProjectService()
        val projects = service.listProjects()
        val current = service.currentProject()?.name
        if (projects.isEmpty()) {
            println("No projects registered")
            return 0
        }
        projects.forEach { p ->
            val mark = if (p.name == current) "*" else "-"
            println("$mark ${'$'}{p.name} (${p.type}) : ${'$'}{p.path}")
        }
        return 0
    }
}
