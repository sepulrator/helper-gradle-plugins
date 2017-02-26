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

        //project.plugins.apply(PackageManagerPlugin)
        addTasks(project)

        /*String workingDir = System.getProperty("user.dir");
        System.out.println("Current working directory : " + workingDir);
        System.out.println("Project directory : " + project.projectDir);
        */

        //System.out.println(PackagerPlugin.class.getProtectionDomain().getCodeSource().getLocation());

        // add 'sourceFolders' DSL extension
        project.extensions.create(ProjectConstants.EXTENSION_NAME,PackagerPluginExtension)

        // add new tasks for creating/cleaning the auto-value sources dir
        //project.task(type: CleanAutoValueSourcesDir, "cleanAutoValueSourcesDir")


        /*Properties properties = System.getProperties();
         for (Object key : properties.keySet()) {
         System.out.println(key + ":" + properties.get(key));
         }
         String str = "";
         StringBuffer buf = new StringBuffer();
         InputStream stream = PackageManagerPlugin.class.getResourceAsStream("/package.structure")
         try {
         BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
         if (stream != null) {
         while ((str = reader.readLine()) != null) {
         File file = new File(workingDir.replace('\\','/') + "/src/main/groovy/" + str.replace(".","/") );
         println(file.getAbsolutePath())
         //String command = "mkdir -p /" + str.replace(".","/")
         file.mkdirs();
         //Runtime.getRuntime().exec(command);
         buf.append(str + "\n" );
         }
         }
         } finally {
         try {
         stream.close();
         } catch (Throwable ignore) {}
         }
         println(buf.toString())
         */
    }

    private void addTasks(Project project) {
        project.task(type: InitPackageStructure, "init")
        project.task(type: RetrievePackageStructure, "retrieve")
        project.task(type: GeneratePackageStructure, "generate")
        project.tasks.retrieve.dependsOn project.tasks.init
        project.tasks.generate.dependsOn project.tasks.init

    }
}
