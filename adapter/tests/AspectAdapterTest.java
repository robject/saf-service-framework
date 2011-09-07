package sepher.saf.adapter.tests;

import junit.framework.*;

/**
 * @author Rob Vens Junit test class voor <code>AspectAdapter </code>.
 */
public class AspectAdapterTest extends TestCase {

    /**
     * Constructor voor de class
     * 
     * @param testNaam
     *        String
     */
    public AspectAdapterTest(java.lang.String testNaam) {
        super(testNaam);
    }

    /**
     * Setup objects to link the <code>AspectAdapter</code> with.
     */
    public void setUp() {

    }

    /**
     * A unit test suite for JUnit
     * 
     * @return The test suite
     */
    public static Test suite() {
        TestResult result = new TestResult();
        TestSuite suite = new TestSuite("AspectAdapterTest");
        suite.addTest(new TestSuite(AspectAdapterTest.class));
        return suite;
    }

    public void tearDown() {

    }

    /**
     * JUnit test van update Creation date: (08-11-2001 14:21:36)
     */
    public void testUpdate() {

    }
}