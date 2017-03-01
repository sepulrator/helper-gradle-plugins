package org.sepulrator.builder.plugin

import org.gradle.testkit.runner.GradleRunner
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

import static org.gradle.testkit.runner.TaskOutcome.SUCCESS
import static org.gradle.testkit.runner.TaskOutcome.SUCCESS

/**
 * Created by osman on 1.3.2017.
 */
class BuilderGeneratorPluginTest extends Specification {

    @Rule final TemporaryFolder testProjectDir = new TemporaryFolder()
    File buildFile

    def setup() {
        buildFile = testProjectDir.newFile('build.gradle')
        println buildFile.absolutePath
    }

    def 'create specified directories and package index file'() {
        given:
        buildFile << """
            plugins {
                id 'groovy'
                id 'org.sepulrator.gradle.builder-generator-plugin' version '1.0.0'
            }

			dependencies {
                compile 'org.slf4j:slf4j-api:1.7.13'
                runtime 'org.slf4j:log4j-over-slf4j:1.7.13'
                testCompile 'org.spockframework:spock-core:1.0-groovy-2.4'
            }

            repositories {
                mavenLocal()
                mavenCentral()
            }
        	"""

        when:
        def result = GradleRunner.create()
                .withDebug(true)
                .withProjectDir(testProjectDir.root)
                .withArguments('generateBuilder','--info','-PfileName=')
                .withPluginClasspath()
                .build()

        then:
        def builderTask = result.task(':generateBuilder')
        builderTask.outcome == SUCCESS
        new File("${testProjectDir.root}").exists()



    }
}
