package org.sepulrator.packager.plugin

import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.testfixtures.ProjectBuilder
import org.sepulrator.packager.plugin.tasks.InitSourceFolders
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import static org.hamcrest.CoreMatchers.*
import static org.hamcrest.MatcherAssert.assertThat


/**
 * Created by osman on 24.2.2017.
 */
class PackagerPluginTest2 {
    /*
    private Project project

    @BeforeMethod
    void setup() {
        project = ProjectBuilder.builder().build()
        project.plugins.apply(PackagerPlugin.class)
    }

    @Test
    void testPluginAppliesItself() {
        assertThat(project.plugins.hasPlugin(PackagerPlugin.class), is(true))
    }

    @Test
    void testReApplyDoesNotFail() {
        project.plugins.apply(PackagerPlugin.class)
    }

    @Test
    void testPluginAppliesJavaPlugin() {
        assertThat(project.plugins.hasPlugin(PackagerPlugin.class), is(true))
    }

    @Test
    void testPluginRegistersAutoValueExtensions() {
        assertThat(project.extensions.init , notNullValue())
    }

    @Test
    void testPluginTasksAreAvailable() {
        assertThat(project.tasks.initAutoValueSourcesDir, notNullValue())
    }

    @Test
    void testTaskTypes() {
        final Task initTask = project.tasks.initAutoValueSourcesDir
        assertThat(initTask, instanceOf(InitSourceFolders.class))
    }
   */



}
