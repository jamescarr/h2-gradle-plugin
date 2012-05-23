package org.jamescarr.gradle

import org.apache.tools.ant.AntClassLoader
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging

public class StartH2Task extends DefaultTask{
	static final Logger LOGGER = Logging.getLogger(StartH2Task.class)
	def scripts = [:]
	def ports = [:]
	
	String rootDir = "."

	@TaskAction
	void start(){
		validateConfigurationAndStartServer()
		
	}
	private void validateConfigurationAndStartServer(){
		org.h2.tools.Server.main("-tcp", "-web", "-tcpPort", "${ports.tcp}", "-webPort", "${ports.web}")
		scripts.each { databaseName, scripts ->
			new File("./${databaseName}.h2.db").delete()
			scripts.each { script ->
				org.h2.tools.RunScript.execute("jdbc:h2:tcp://localhost:${ports.tcp}/${databaseName}", "sa", "", "${rootDir}/${script}", 'UTF-8', false)
			}
		}
	}
	
}
