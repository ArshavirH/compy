package com.compy.shared.project

enum class ProjectType {
    SPRING_BOOT,
    UNKNOWN;

    companion object {
        fun fromString(value: String): ProjectType? =
            entries.firstOrNull { it.name.equals(value.replace("-", "_"), ignoreCase = true) }
    }
}
