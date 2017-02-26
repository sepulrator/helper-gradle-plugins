package org.sepulrator.packager.plugin.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.tasks.TaskAction
import org.sepulrator.packager.plugin.PackagerPlugin
import org.sepulrator.packager.plugin.PackagerPluginExtension
import org.sepulrator.packager.plugin.ProjectConstants
import org.sepulrator.packager.plugin.ProjectUtils

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Created by osman on 26.2.2017.
 */
class RetrievePackageStructure extends DefaultTask {

    private static final Logger LOG = Logging.getLogger(RetrievePackageStructure.class)

    static final String DESCRIPTION = "Retrieve"

    RetrievePackageStructure() {
        this.group = ProjectConstants.TASK_GROUP
        this.description = DESCRIPTION;
    }

    @SuppressWarnings("GroovyUnusedDeclaration")
    @TaskAction
    retrieve() {
        PackagerPluginExtension extension = ProjectUtils.getSourceFolderExtension(project)
        extension.sourceFolders.each
                { sourceFolder ->
                    retrievePackageStructure(sourceFolder)
                }
    }

    def retrievePackageStructure(String sourceFolder) {
        def projectDirectory = ProjectUtils.getProjectDirectory(project)
        def sourceFolderDirectory = "${projectDirectory}/${sourceFolder}"
        File sourceFolderFile = new File(sourceFolderDirectory)
        def packageIndexFilePath = "${sourceFolderDirectory}/${ProjectConstants.PACKAGE_INDEX_FILE_NAME}"
        File packagerFile = new File(packageIndexFilePath);
        if(!packagerFile.exists()) {
            packagerFile.createNewFile()
        }
        Files.write(Paths.get(packagerFile.path), getDirectoryTree(sourceFolderFile).getBytes());
        LOG.info(ProjectConstants.PACKAGE_INDEX_FILE_NAME + " has been refreshed")
    }

    def static filterDirectory = new FileFilter() {
        @Override
        boolean accept(File pathname) {
            return pathname.isDirectory()
        }
    }

    def static String getDirectoryTree(File folder) {
        if (!folder.isDirectory()) {
            throw new IllegalArgumentException(folder.toString() + " is not a Directory");
        }
        int indent = 0;
        StringBuilder sb = new StringBuilder();
        folder.listFiles(filterDirectory).each {
            file -> printDirectoryTree(file, indent, sb);
        }
        return sb.toString();
    }

    def static void printDirectoryTree(File folder, int indent,
                                           StringBuilder sb) {
        if (!folder.isDirectory()) {
            println folder.toString()
            throw new IllegalArgumentException("folder is not a Directory");
        }
        sb.append(getIndentString(indent));
        sb.append(ProjectConstants.PACKAGE_FILE_PREFIX);
        sb.append(folder.getName());
        sb.append(ProjectConstants.PACKAGE_DIR_POSTFIX);
        sb.append("\n");
        folder.listFiles().each {
            file ->
                if (file.isDirectory()) {
                    printDirectoryTree(file, indent + 1, sb);
                } else {
                    printFile(file, indent + 1, sb);
                }
        }
    }

    def static void printFile(File file, int indent, StringBuilder sb) {
        sb.append(getIndentString(indent));
        sb.append(ProjectConstants.PACKAGE_FILE_PREFIX);
        sb.append(file.getName());
        sb.append("\n");
    }

    def static String getIndentString(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(ProjectConstants.PACKAGE_INDENT);
        return sb.multiply(indent).toString();
    }

    static void main(String[] args) {
        String base = "C:\\Users\\osman\\IdeaProjects\\helper-gradle-plugins\\packager-plugin\\src\\main\\groovy"
        File dir = new File(base);
        File packagerFile = new File(base+ File.separator + ProjectConstants.PACKAGE_INDEX_FILE_NAME);
        if(!packagerFile.exists()) {
            packagerFile.createNewFile()
        }
        Files.write(Paths.get(packagerFile.path), getDirectoryTree(dir).getBytes());
    }

}
