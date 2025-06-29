package com.compy.cli.system

import com.compy.shared.SystemInfoService
import picocli.CommandLine
import java.util.concurrent.Callable

private const val COMMAND_NAME = "info"

@CommandLine.Command(
    name = COMMAND_NAME, description = ["Shows information about a System"]
)
class SystemInfo : Callable<Int> {

    override fun call(): Int {
        println("🔍 System Information:")
        SystemInfoService.getSystemInfo().forEach { (key, value) ->
            println("  $key: $value")
        }
        return 0
    }
}
