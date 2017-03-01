package org.sepulrator.packager.plugin.tasks

import org.gradle.testkit.runner.GradleRunner
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import org.sepulrator.packager.plugin.AbstractSpecification

import static org.gradle.testkit.runner.TaskOutcome.SUCCESS
import static org.gradle.testkit.runner.TaskOutcome.SUCCESS

/**
 * Created by osman on 27.2.2017.
 */
class InitPackageStructureTest extends AbstractSpecification {

    def 'create directories and package index file'() {
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
        def result = runTasksSuccessfully('init')

        then:
        //println result.output.readLines().toString()
        def initTask = result.task(':init')
        initTask.outcome == SUCCESS
        //Thread.sleep(20000);
        new File("${projectDir}/src/main/groovy").exists()
        new File("${projectDir}/src/test/groovy").exists()
        new File("${projectDir}/src/main/groovy/package.index").exists()
        new File("${projectDir}/src/test/groovy/package.index").exists()


    }

}
