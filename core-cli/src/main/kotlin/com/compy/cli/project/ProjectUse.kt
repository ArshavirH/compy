package com.compy.cli.project

import com.compy.modules.project.DefaultProjectService
import picocli.CommandLine
import java.util.concurrent.Callable

@CommandLine.Command(name = "use", description = ["Select active project"])
class ProjectUse : Callable<Int> {
    @CommandLine.Parameters(index = "0", description = ["Project name"])
    lateinit var name: String

    override fun call(): Int {
        val service = DefaultProjectService()
        return try {
            service.useProject(name)
            println("✅ Using project '$name'")
            0
        } catch (e: Exception) {
            println("❌ ${'$'}{e.message}")
            1
        }
    }
}
