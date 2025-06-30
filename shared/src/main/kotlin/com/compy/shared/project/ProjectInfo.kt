package com.compy.shared.project

import java.nio.file.Path

data class ProjectInfo(
    val name: String,
    val path: String,
    val type: ProjectType
) {
    constructor(name: String, path: Path, type: ProjectType) : this(name, path.toAbsolutePath().toString(), type)
}
