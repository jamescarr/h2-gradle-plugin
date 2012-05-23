# Gradle H2 Plugin

![H2 Logo](http://www.h2database.com/html/images/h2-logo.png)

[![Build Status](https://secure.travis-ci.org/jamescarr/h2-gradle-plugin.png?branch=master)](http://travis-ci.org/jamescarr/h2-gradle-plugin)

This plugin provides the capability of running and populating an embedded H2 database as part of a gradle build,
perfect for integration testing with embedded web containers.

## Usage

To use the h2 plugin, include the following in your buildscript:

```groovy
apply plugin: 'h2'

buildscript {
    repositories {
        add(new org.apache.ivy.plugins.resolver.URLResolver()) {
            name = 'GitHub'
            addArtifactPattern 'http://cloud.github.com/downloads/[organisation]/[module]/[module]-[revision].[ext]'
        }
        mavenCentral() // or alternatively your own maven resolver
    }

    dependencies {
        classpath 'jamescarr:h2-gradle-plugin:0.8.2'
        classpath 'com.h2database:h2:1.3.164'  // choose your own version
    }
}

```
### Defining h2 configuration and database named 'example'

```groovy

h2 {
	tcpPort = 9092
	webPort = 8082
	
	example {
		scripts = [
		     'src/test/resources/cars.sql'
		   , 'src/test/resources/init-data.sql'
		]
	}
}

```

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
jettyRun.doFirst {
  h2start.execute()
}

```

### Using with tomcatRun

```groovy
tomcatRun.doFirst {
  h2start.execute()
}

```

More to come.
