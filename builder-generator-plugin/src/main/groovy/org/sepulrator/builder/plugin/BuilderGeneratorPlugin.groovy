package org.sepulrator.builder.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.sepulrator.builder.plugin.tasks.GenerateBuilderTask

/**
 * Created by osman on 28.2.2017.
 */
class BuilderGeneratorPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        if (project.plugins.hasPlugin(BuilderGeneratorPlugin.class)) {
            return
        }
        //add tasks to the project
        addTasks(project)

    }

    private void addTasks(Project project) {
        project.task(type: GenerateBuilderTask, "generateBuilder")

    }
}
