package com.compy.cli

import com.compy.cli.system.SystemInfo
import picocli.CommandLine
import kotlin.system.exitProcess

@CommandLine.Command(
    subcommands = [
        SystemInfo::class
    ]
)
class App

fun main(args: Array<String>): Unit = exitProcess(
    CommandLine(App())
        .setUsageHelpAutoWidth(true)
        .setCaseInsensitiveEnumValuesAllowed(true)
        .execute(*args)
)
