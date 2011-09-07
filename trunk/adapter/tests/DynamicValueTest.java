package sepher.saf.adapter.tests;

import sepher.saf.adapter.*;
import junit.framework.*;

/**
 * JUnit test class voor DynamicValue
 */
public class DynamicValueTest extends TestCase {
	private boolean updateReceived = false;
	private Object updateAspect = null;
	DynamicValue theDynamicValue = null;
	anObserver theObserver = null;
	/**
	 * Inner class to implement an interface class for testing
	 */
	private class anObserver implements SAFObserver {

		/**
		 * Constructor for anObserver
		 */
		public anObserver() {
			super();
		}
		public void update(Object sender, java.lang.Object aspect) {
			updateReceived = true;
			updateAspect = aspect;
		}

	}

	/**
	 * Constructor met de naam van de test class.
	 * Creation date: (08-11-2001 11:39:29)
	 * @param testNaam java.lang.String
	 */
	public DynamicValueTest(String testNaam) {
		super(testNaam);
	}
/**
 * Insert the method's description here.
 * Creation date: (11/21/01 2:42:29 PM)
 */
public static void main(java.lang.String[] args) {
	// Insert code to start the application here.
	junit.textui.TestRunner.run(suite());
}
/**
 * Set up the objects needed for testing.
 * This consists of an instance of the class we want to test and
 * an Observer that is added to the list of observers.
 */
	public void setUp() {
		this.theDynamicValue = new DynamicValue();
		this.theObserver = new anObserver();
		this.theDynamicValue.addObserver (theObserver);

	}
	/**
	 * A unit test suite for JUnit
	 * @return    The test suite
	 */
	public static Test suite() {
		TestResult result = new TestResult();
		TestSuite suite = new TestSuite("DynamicValueTest");
		suite.addTest(new TestSuite(DynamicValueTest.class));
		return suite;
	}
	public void tearDown() {
		this.theDynamicValue.untie();
		this.theDynamicValue = null;
		this.theObserver = null;

	}
	public void testGetValue() {

	}
	public void testGetValueUsingSubject() {

	}
	public void testSetValue() {
		this.updateReceived = false;
		this.updateAspect = null;
		
		theDynamicValue.setValue("waarde");
		this.assertEquals(this.theDynamicValue.getValue(), ("waarde"));
		this.assertTrue(this.updateReceived);
		this.assertTrue("test", ( (String) this.updateAspect).equals ("value" ));

	}
	public void testSetValueSilently() {

	}
	public void testUpdate() {

	}
}
