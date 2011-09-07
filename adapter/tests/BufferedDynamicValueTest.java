package sepher.saf.adapter.tests;

import sepher.saf.adapter.*;
import junit.framework.*;

/**
 * Junit testclass voor BufferedDynamicValue.
 */
public class BufferedDynamicValueTest extends TestCase {

	/**
	 * Constructor met de naam van de test class.
	 * @param testNaam
	 */
	public BufferedDynamicValueTest(java.lang.String testNaam) {
		super(testNaam);
	}

	public void setUp() {

	}

	/**
	 * A unit test suite for JUnit
	 * @return    The test suite
	 */
	public static Test suite() {
		TestResult result = new TestResult();
		TestSuite suite = new TestSuite("BufferedDynamicValueTest");
		suite.addTest(new TestSuite(BufferedDynamicValueTest.class));
		return suite;
	}

	public void tearDown() {

	}

	public void testAddObserver() {

	}

	public void testDeleteObserver() {

	}

	public void testGetSubject() {

	}

	public void testGetTriggerChannel() {

	}

	public void testGetValue() {

	}

	public void testSetSubject() {

	}

	public void testSetTriggerChannel() {

	}

	public void testUpdate() {

	}
}
