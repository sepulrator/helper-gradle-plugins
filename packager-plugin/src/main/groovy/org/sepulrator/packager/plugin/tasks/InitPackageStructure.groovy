package org.sepulrator.packager.plugin.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.tasks.TaskAction
import org.sepulrator.packager.plugin.PackagerPlugin
import org.sepulrator.packager.plugin.PackagerPluginExtension
import org.sepulrator.packager.plugin.ProjectConstants
import org.sepulrator.packager.plugin.ProjectUtils

/**
 * Created by osman on 24.2.2017.
 */
class InitPackageStructure extends DefaultTask {

    private static final Logger LOG = Logging.getLogger(InitPackageStructure.class)

    static final String DESCRIPTION = "Initialize the source folders"

    InitPackageStructure() {
        this.group = ProjectConstants.TASK_GROUP
        this.description = DESCRIPTION
    }

    @SuppressWarnings("GroovyUnusedDeclaration")
    @TaskAction
    init() {
        PackagerPluginExtension extension = ProjectUtils.getSourceFolderExtension(project)
        extension.sourceFolders.each
                { sourceFolder ->
                    initializePackageStructure(sourceFolder)
                }
    }

    def initializePackageStructure(sourceFolder) {
        def projectDirectory = ProjectUtils.getProjectDirectory(project)
        def sourceFolderDirectory = "${projectDirectory}/${sourceFolder}"
        def packageIndexFilePath = "${sourceFolderDirectory}/${ProjectConstants.PACKAGE_INDEX_FILE_NAME}"
        def sourceFolderFile = new File("${sourceFolderDirectory}")
        def packageFile = new File("${packageIndexFilePath}")
        if (sourceFolderFile.exists()) {
            if (!packageFile.exists()) {
                packageFile.createNewFile()
                LOG.info(packageIndexFilePath + " is created")
            }
        } else {
            sourceFolderFile.mkdirs()
            LOG.info(sourceFolderDirectory + " is created")
            packageFile.createNewFile()
            LOG.info(packageIndexFilePath + " is created")
        }
    }

}
