package org.sepulrator.packager.plugin.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.tasks.TaskAction
import org.sepulrator.packager.plugin.PackagerPluginExtension
import org.sepulrator.packager.plugin.ProjectConstants
import org.sepulrator.packager.plugin.ProjectUtils

/**
 * Created by osman on 26.2.2017.
 */
class GeneratePackageStructure extends DefaultTask {

    private static final Logger LOG = Logging.getLogger(GeneratePackageStructure.class)

    static final String DESCRIPTION = "Generate"

    GeneratePackageStructure() {
        this.group = ProjectConstants.TASK_GROUP
        this.description = DESCRIPTION;
    }

    @SuppressWarnings("GroovyUnusedDeclaration")
    @TaskAction
    generate() {
        PackagerPluginExtension extension = ProjectUtils.getSourceFolderExtension(project)
        extension.sourceFolders.each
                { sourceFolder ->
                    generatePackageStructure(sourceFolder)
                }
    }

    def generatePackageStructure(String sourceFolder) {

    }
}
