package org.sepulrator.packager.plugin.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.tasks.TaskAction
import org.sepulrator.packager.plugin.PackagerPluginExtension
import org.sepulrator.packager.plugin.ProjectConstants
import org.sepulrator.packager.plugin.ProjectUtils

import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Stream

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

    def generatePackageStructure(String sourceFolderName) {
        def projectDirectory = ProjectUtils.getProjectDirectory(project)
        def sourceFolderDirectory = "${projectDirectory}/${sourceFolderName}"
        def packageIndexFilePath = "${sourceFolderDirectory}/${ProjectConstants.PACKAGE_INDEX_FILE_NAME}"
        File packagerFile = new File(packageIndexFilePath);

        def list = new ArrayList<String>();
        packagerFile.eachLine {
            line ->
                int level = getLevel(line)
                if (level < list.size()) {
                    while (list.size() > level)
                        list.pop()
                }

                if (line.endsWith(ProjectConstants.PACKAGE_DIR_POSTFIX)) {
                    String dirName = getDirectory(line)

                    list.push(dirName)

                    File dirFolder = new File(base + File.separator + getDirectoryFullName(list))
                    if (!dirFolder.exists()) {
                        dirFolder.mkdirs()
                    }

                } else {
                    String sourceFileName = getSourceName(line)
                    list.push(sourceFileName)
                    File sourceFolder = new File(base + File.separator + getDirectoryFullName(list))
                    if (!sourceFolder.exists()) {
                        sourceFolder.createNewFile()
                    }
                }

        }
    }

    static void main(String[] args) {
        String base = "C:\\Users\\osman\\IdeaProjects\\helper-gradle-plugins\\packager-plugin\\src\\main\\groovy"
        File dir = new File(base);
        File packagerFile = new File(base + File.separator + ProjectConstants.PACKAGE_INDEX_FILE_NAME);


        def list = new ArrayList<String>();
        packagerFile.eachLine {
            line ->
                int level = getLevel(line)
                if (level < list.size()) {
                    while (list.size() > level)
                        list.pop()
                }

                if (line.endsWith(ProjectConstants.PACKAGE_DIR_POSTFIX)) {
                    String dirName = getDirectory(line)

                    list.push(dirName)

                    File dirFolder = new File(base + File.separator + getDirectoryFullName(list))
                    if (!dirFolder.exists()) {
                        dirFolder.mkdirs()
                    }

                } else {
                    String sourceFileName = getSourceName(line)
                    list.push(sourceFileName)
                    File sourceFolder = new File(base + File.separator + getDirectoryFullName(list))
                    if (!sourceFolder.exists()) {
                        sourceFolder.createNewFile()
                    }
                }
        }

    }


    static String getDirectoryFullName(List dirList) {
        StringBuilder sb = new StringBuilder()
        dirList.each { item ->
            sb.append(item)
            sb.append(File.separator)
        }
        return sb.toString()
    }

    static String getDirectory(String line) {
        def foundStartIndex = line.lastIndexOf(ProjectConstants.PACKAGE_FILE_NAME_PREFIX) + ProjectConstants.PACKAGE_FILE_NAME_PREFIX.length()
        return line.substring(foundStartIndex, line.length() - 1)
    }

    static String getSourceName(String line) {
        def foundStartIndex = line.lastIndexOf(ProjectConstants.PACKAGE_FILE_NAME_PREFIX) + ProjectConstants.PACKAGE_FILE_NAME_PREFIX.length()
        return line.substring(foundStartIndex, line.length())
    }

    static int getLevel(String line) {
        def foundStartIndex = line.lastIndexOf(ProjectConstants.PACKAGE_FILE_NAME_PREFIX)
        return foundStartIndex / ProjectConstants.PACKAGE_INDENT.length()
    }
}
