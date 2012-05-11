package org.jamescarr.gradle

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

public class StartH2Task extends DefaultTask{
	
	 def scripts = [:]
	 String rootDir = "."
	 
	 @TaskAction
	 void start(){
		 org.h2.tools.Server.main("-tcp", "-web")
		 scripts.each { databaseName, scripts ->
			 new File("./${databaseName}.h2.db").delete()
			 scripts.each { script ->
				 org.h2.tools.RunScript.execute("jdbc:h2:tcp://localhost/${databaseName}", "sa", "", "${rootDir}/${script}", 'UTF-8', false)
			 }
		 }
	 }
}
