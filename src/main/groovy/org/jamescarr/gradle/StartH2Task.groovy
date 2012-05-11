package org.jamescarr.gradle

import org.apache.tools.ant.AntClassLoader
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging

public class StartH2Task extends DefaultTask{
	static final Logger LOGGER = Logging.getLogger(StartH2Task.class)
	def scripts = [:]
	String rootDir = "."

	@TaskAction
	void start(){
		LOGGER.info "Configuring Tomcat for ${getProject()}"

		ClassLoader originalClassLoader = getClass().classLoader
		URLClassLoader h2ClassLoader = createH2ClassLoader()

		try {
			Thread.currentThread().contextClassLoader = h2ClassLoader
			validateConfigurationAndStartServer()
		}
		finally {
			Thread.currentThread().contextClassLoader = originalClassLoader
		}

		
	}
	private void validateConfigurationAndStartServer(){
		org.h2.tools.Server.main("-tcp", "-web")
		scripts.each { databaseName, scripts ->
			new File("./${databaseName}.h2.db").delete()
			scripts.each { script ->
				org.h2.tools.RunScript.execute("jdbc:h2:tcp://localhost/${databaseName}", "sa", "", "${rootDir}/${script}", 'UTF-8', false)
			}
		}
	}
	private URLClassLoader createH2ClassLoader(){
		ClassLoader rootClassLoader = new AntClassLoader(getClass().classLoader, false)
		URLClassLoader pluginClassloader = new URLClassLoader(toURLArray(getBuildscriptClasspath().files), rootClassLoader)
		new URLClassLoader(toURLArray(getH2Classpath().files), pluginClassloader)
	}
}
