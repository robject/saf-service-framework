package sepher.saf.adapter.tests;

import sepher.saf.adapter.*;
import junit.framework.*;

/**
 * JUnit test class voor InterfaceAdapter
 */
public class InterfaceAdapterTest extends TestCase {

    /**
	 * Constructor met de naam van de test class. Creation date: (08-11-2001 11:39:00)
	 * 
	 * @param testNaam    java.lang.String
	 */
    public InterfaceAdapterTest(String testNaam) {
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
        TestSuite suite = new TestSuite("InterfaceAdapterTest");
        suite.addTest(new TestSuite(InterfaceAdapterTest.class));
        return suite;
    }

    public void tearDown() {

    }

    public void testGetSubject() {

    }

    public void testGetSubjectChannel() {

    }

    public void testGetValue() {

    }

    public void testSetSubjectSendsUpdates() {

    }

    public void testSetValue() {

    }

    public void testUpdate() {

    }
}
