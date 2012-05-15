package org.jamescarr.gradle

import static org.junit.Assert.*;

import org.junit.Test;

public class H2PluginConventionTest {
	H2PluginConvention convention = new H2PluginConvention();

	
	@Test
	public void "script and database parsing from convention"() {
		convention.h2 {
			quickvin {
				scripts = ['a/b/c.sql', 'd/e/f.sql']
			}
			corevin {
				scripts = ['z/x/y.sql']
			}
		}
		
		
		assert convention.scripts['quickvin'] ==  ['a/b/c.sql', 'd/e/f.sql']
		assert convention.scripts['corevin'] ==  ['z/x/y.sql']
	}
	
	@Test
	void "web and tcp port parsing from convention"(){
		
		convention.h2 {
			webPort = 9011
			tcpPort = 2211
			quickvin {
				scripts = ['a/b/c.sql', 'd/e/f.sql']
			}
		}
		
		assert convention.ports.web ==  9011
		assert convention.ports.tcp ==  2211
	}
	
	@Test
	void "verify web and tcp ports have defaults set"(){
		convention.h2 {
			
			quickvin {
				scripts = ['a/b/c.sql', 'd/e/f.sql']
			}
		}
		
		assert convention.ports.web ==  8082
		assert convention.ports.tcp ==  9092
	}

}
