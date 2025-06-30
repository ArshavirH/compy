package com.compy.shared.project

data class ProjectIndex(
    var current: String? = null,
    val projects: MutableMap<String, ProjectInfo> = mutableMapOf()
)
