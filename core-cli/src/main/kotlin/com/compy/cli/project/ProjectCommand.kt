package com.compy.cli.project

import picocli.CommandLine
import java.util.concurrent.Callable

@CommandLine.Command(
    name = "project",
    description = ["Manage projects"],
    subcommands = [ProjectAdd::class, ProjectUse::class, ProjectList::class]
)
class ProjectCommand : Callable<Int> {
    override fun call(): Int {
        CommandLine(CommandLine.Model.CommandSpec.forAnnotatedObject(this)).usage(System.out)
        return 0
    }
}
