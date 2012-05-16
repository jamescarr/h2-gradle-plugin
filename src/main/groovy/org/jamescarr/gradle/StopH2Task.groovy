package org.jamescarr.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * Created with IntelliJ IDEA.
 * User: jamescarr
 * Date: 5/15/12
 * Time: 11:56 PM
 * To change this template use File | Settings | File Templates.
 */
class StopH2Task extends DefaultTask {
    def tcpPort
    @TaskAction
    void start(){
        org.h2.tools.Server.main("-tcpShutdown","tcp://localhost:${tcpPort}")
    }
}
