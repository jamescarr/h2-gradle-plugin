# Gradle H2 Plugin

![H2 Logo](http://www.h2database.com/html/images/h2-logo.png)

This plugin provides the capability of running and populating an embedded H2 database as part of a gradle build,
perfect for integration testing with embedded web containers.

## Usage

```groovy
apply plugin: 'h2'

h2 {
	tcpPort = 9092
	webPort = 8082
	
	databaseName {
		scripts = [
		     'src/test/resources/cars.sql'
		   , 'src/test/resources/init-data.sql'
		]
	}
}

``

the inner closure databaseName will create a database with that name and populate it with scripts defined by the
script variable. You can have multiple database closures that will create multiple databases.

```groovy
apply plugin: 'h2'

h2 {
	tcpPort = 9092
	webPort = 8082
	
	one {
		scripts = [
		     'src/test/resources/cars.sql'
		   , 'src/test/resources/init-data.sql'
		]
	}
	
	two {
		scripts = [
		     'src/test/resources/boats.sql'
		   , 'src/test/resources/init-data.sql'
		]
	}
}

```


### Using with jettyRun

```groovy
jettyRun.doFirst << h2start

```

### Using with tomcatRun

```groovy
tomcatRun.doFirst << h2start

```