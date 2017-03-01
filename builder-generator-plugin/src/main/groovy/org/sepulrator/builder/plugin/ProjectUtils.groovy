package org.sepulrator.builder.plugin

import org.gradle.api.Project

/**
 * Created by osman on 26.2.2017.
 */
class ProjectUtils {

    static String getProjectDirectory(Project project) {
        return project.projectDir.toString().replaceAll("\\\\", "/")
    }

}
