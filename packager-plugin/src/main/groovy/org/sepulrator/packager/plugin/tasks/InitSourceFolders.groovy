package org.sepulrator.packager.plugin.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.tasks.TaskAction
import org.sepulrator.packager.plugin.PackagerPluginExtension

/**
 * Created by osman on 24.2.2017.
 */
class InitSourceFolders extends DefaultTask {

    private static final Logger LOG = Logging.getLogger(InitSourceFolders.class)

    InitSourceFolders() {
    }

    @SuppressWarnings("GroovyUnusedDeclaration")
    @TaskAction
    init() {
        System.err << "createSourceFolders"
        println("createSourceFolders")
        def projectDirectory = project.projectDir.toString().replaceAll("\\\\", "/")
        PackagerPluginExtension extension = project.extensions.findByType(PackagerPluginExtension.class)
        extension.sourceFolders.each
                { sourceFolder ->
                    def sourceFolderFile = new File("${projectDirectory}/${sourceFolder}")
                    def packageFile = new File("${projectDirectory}/${sourceFolder}/package.structure")
                    if (sourceFolderFile.exists()) {
                        if (!packageFile.exists())
                            packageFile.createNewFile()
                    } else {
                        sourceFolderFile.mkdirs()
                        packageFile.createNewFile()
                    }
                }
    }

}
