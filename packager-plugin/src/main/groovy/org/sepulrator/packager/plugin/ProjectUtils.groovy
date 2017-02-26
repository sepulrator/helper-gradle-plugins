package org.sepulrator.packager.plugin

import org.gradle.api.Project

/**
 * Created by osman on 26.2.2017.
 */
class ProjectUtils {

    static String getProjectDirectory(Project project) {
        return project.projectDir.toString().replaceAll("\\\\", "/")
    }

    static PackagerPluginExtension getSourceFolderExtension(Project project) {
        return project.extensions.findByType(PackagerPluginExtension.class)
    }
}
