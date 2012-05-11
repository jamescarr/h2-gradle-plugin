package org.jamescarr.gradle

import static org.junit.Assert.*;

import org.junit.Test;

public class H2PluginConventionTest {

	@Test
	public void test() {
		H2PluginConvention convention = new H2PluginConvention();
		
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

}
