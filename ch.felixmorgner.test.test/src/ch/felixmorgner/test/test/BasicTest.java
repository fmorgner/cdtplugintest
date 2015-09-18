package ch.felixmorgner.test.test;

import org.eclipse.cdt.codan.core.test.CheckerTestCase;
import org.eclipse.core.runtime.Plugin;

public class BasicTest extends CheckerTestCase {

	@Override
	protected Plugin getPlugin() {
		return Activator.getDefault();
	}
	
	@Override
	protected String getSourcePrefix() {		
		return "src";
	}

	//	dummy() {
	//    char buffer[20];
	//	}
	public void testNonConst() {
		loadCodeAndRun(getAboveComment());
		checkErrorLine(2);
	}
	
	//  dummy() {
	//    int const foo{};
	//  }
	public void testConst() {
		loadCodeAndRun(getAboveComment());
		checkNoErrors();
	}

}
