package com.carfax.gradle

class H2PluginConvention {
	def scripts = [:]
	
	def h2(Closure c){
		c.resolveStrategy = Closure.DELEGATE_FIRST
		c.delegate = this
		c()
	}
	
	def methodMissing(String databaseName, args) {
		def closure = args[0]
		
		closure.resolveStrategy = Closure.DELEGATE_FIRST
		closure.delegate = new H2Database()
		closure.call()
		
		scripts[databaseName] = closure.delegate.scripts
		
	}
	
	class H2Database{
		def scripts = []
	}
}
