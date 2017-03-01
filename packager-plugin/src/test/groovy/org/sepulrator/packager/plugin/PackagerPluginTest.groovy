package org.sepulrator.packager.plugin

import static org.gradle.testkit.runner.TaskOutcome.*
import org.gradle.testkit.runner.GradleRunner
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

/**
 * Created by osman on 24.2.2017.
 */
class PackagerPluginTest extends Specification {

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
                id 'org.sepulrator.gradle.packager-plugin'
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

            packager.sourceFolders = ['src/main/groovy','src/test/groovy']
        	"""

        when:
        def result = GradleRunner.create()
                .withDebug(true)
                .withProjectDir(testProjectDir.root)
                .withArguments('init','--info')
                .withPluginClasspath()
                .build()

        then:
        //println result.output.readLines().toString()

        def initTask = result.task(':init')
        initTask.outcome == SUCCESS
        //Thread.sleep(20000);
        new File("${testProjectDir.root}/src/main/groovy").exists()
        new File("${testProjectDir.root}/src/test/groovy").exists()

        when:
        result = GradleRunner.create()
                .withDebug(true)
                .withProjectDir(testProjectDir.root)
                .withArguments('retrieve','--info')
                .withPluginClasspath()
                .build()

        then:
        //println result.output.readLines().toString()

        def retrieveTask = result.task(':retrieve')
        retrieveTask.outcome == SUCCESS
        //Thread.sleep(20000);
        new File("${testProjectDir.root}/src/main/groovy/package.index").exists()
        new File("${testProjectDir.root}/src/test/groovy/package.index").exists()

    }

}
