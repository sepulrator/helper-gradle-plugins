package org.sepulrator.builder.plugin.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.tasks.TaskAction
import org.sepulrator.builder.plugin.ProjectUtils

import java.nio.file.FileVisitResult
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.SimpleFileVisitor
import java.nio.file.attribute.BasicFileAttributes

/**
 * Created by osman on 28.2.2017.
 */
class GenerateBuilderTask extends DefaultTask {
    String fileName;

    private static final Logger LOG = Logging.getLogger(GenerateBuilderTask.class)

    @SuppressWarnings("GroovyUnusedDeclaration")
    @TaskAction
    generate() {
        def projectDirectory = ProjectUtils.getProjectDirectory(project)

        Path start = Paths.get(projectDirectory.toString())
        final List<String> files = new LinkedList<>();
        findFiles(start, files);

        if (files.size() == 0) {
            LOG.error(fileName + ": is not found")
            return
        } else if (files.size() > 1) {
            LOG.error("multiple " + fileName + ": is found")
            return
        }
        LOG.info("file found in " + files.get(0))
    }

    private void findFiles(Path start, files) {
        Files.walkFileTree(start, new SimpleFileVisitor<Path>() {

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
                files.add(file.getFileName().toString().equals(fileName));
                return FileVisitResult.CONTINUE;
            }
        })
    }
}
