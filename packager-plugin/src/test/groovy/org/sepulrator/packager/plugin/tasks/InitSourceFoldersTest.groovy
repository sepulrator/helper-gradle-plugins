package org.sepulrator.packager.plugin.tasks

import org.gradle.api.GradleException
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.sepulrator.packager.plugin.PackagerPlugin
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

/**
 * Created by osman on 24.2.2017.
 */
class InitSourceFoldersTest {
    /*
    private Project project

    private InitSourceFolders task

    @BeforeMethod
    void setup() {
        project = ProjectBuilder.builder().build()
        project.plugins.apply(PackagerPlugin.class)
        //project.eva

        task = project.tasks.init() as InitSourceFolders
    }

    @Test(expectedExceptions = GradleException.class, expectedExceptionsMessageRegExp = "The configured autoValueSourcesDir.*")
    void testCreateSourceFolders() {
        project.packager.sourceFolders = "src/main/java"
        task.init()
    }

    @Test
    void testCreateSourceFoldersBuildFail() {

        task.init()
        assertThat(project.sourceSets.autoValue, notNullValue())

        File javaDir = project.sourceSets.autoValue.java.srcDirs.first() as File
        assertThat(javaDir.name, equalTo("java"))
    }
    */
}
