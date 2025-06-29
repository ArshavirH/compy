package com.compy.shared

object SystemInfoService {
    fun getSystemInfo(): Map<String, String> = mapOf(
        "OS" to "${System.getProperty("os.name")} ${System.getProperty("os.version")}",
        "Architecture" to System.getProperty("os.arch"),
        "Java Version" to System.getProperty("java.version"),
        "Available processors" to Runtime.getRuntime().availableProcessors().toString()
    )
}
