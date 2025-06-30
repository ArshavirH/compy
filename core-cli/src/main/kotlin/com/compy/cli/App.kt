package com.compy.cli

import com.compy.cli.system.SystemInfo
import com.compy.cli.project.ProjectCommand
import picocli.CommandLine
import kotlin.system.exitProcess

@CommandLine.Command(
    subcommands = [
        SystemInfo::class,
        ProjectCommand::class
    ]
)
class App

fun main(args: Array<String>): Unit = exitProcess(
    CommandLine(App())
        .setUsageHelpAutoWidth(true)
        .setCaseInsensitiveEnumValuesAllowed(true)
        .execute(*args)
)
