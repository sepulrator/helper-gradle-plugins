package org.sepulrator.packager.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.sepulrator.packager.plugin.tasks.GeneratePackageStructure
import org.sepulrator.packager.plugin.tasks.InitPackageStructure
import org.sepulrator.packager.plugin.tasks.RetrievePackageStructure

/**
 * Created by osman on 24.2.2017.
 */
class PackagerPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        if (project.plugins.hasPlugin(PackagerPlugin.class)) {
            return
        }

        //add tasks to the project
        addTasks(project)

        // add 'sourceFolders' DSL extension
        project.extensions.create(ProjectConstants.EXTENSION_NAME,PackagerPluginExtension)
    }

    private void addTasks(Project project) {
        project.task(type: InitPackageStructure, "init")
        project.task(type: RetrievePackageStructure, "retrieve")
        project.task(type: GeneratePackageStructure, "generate")
        project.tasks.retrieve.dependsOn project.tasks.init
        project.tasks.generate.dependsOn project.tasks.init

    }
}
