package sepher.saf.adapter.tests;

import sepher.saf.adapter.*;
import junit.framework.*;

/**
 * JUnit test class voor ChangingObservable
 */
public class ChangingObservableTest extends TestCase {

    private anObservable theObservable;

    private anObserver theObserver;

    private boolean updateReceived = false;

    /**
     * Private class that implements the <code>Observer</code> interface to
     * use in testing. The rationale behind this is that we want to test private
     * interfaces.
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
        }
    }

    /**
     * Private class that implements the <code>Observable</code> interface to
     * use in testing.
     */
    private class anObservable extends ChangingObservable {

        /**
         * Constructor for anObservable
         */
        public anObservable() {
            super();
        }

        /**
         * Overrule setChanged to make it testable
         */
        public void testSetChanged(Object aspect) {
            super.setChanged(aspect);
        }

    }

    /**
	 * Constructor met de naam van de test class. Creation date: (08-11-2001 11:39:53)
	 * 
	 * @param testNaam    java.lang.String
	 */
    public ChangingObservableTest(String testNaam) {
        super(testNaam);
    }

    /**
     * Insert the method's description here. Creation date: (11/21/01 2:42:29
     * PM)
     */
    public static void main(java.lang.String[] args) {
        // Insert code to start the application here.
        junit.textui.TestRunner.run(suite());
    }

    /**
     * Create an <code>Observable</code> and an <code>Observer</code>.
     */
    public void setUp() {
        theObservable = new anObservable();
        theObserver = new anObserver();

    }

    /**
	 * A unit test suite for JUnit
	 * @return    The test suite
	 */
    public static Test suite() {
        TestResult result = new TestResult();
        TestSuite suite = new TestSuite("ChangingObservableTest");
        suite.addTest(new TestSuite(ChangingObservableTest.class));
        return suite;
    }

    public void tearDown() {

    }

    /**
     * Tests the add observer method inherited from the superclass
     */
    public void testAddObserver() {
        // first make sure there are no observers
        theObservable.untie();
        theObservable.addObserver(theObserver);
        assertTrue(theObservable.countObservers() == 1);

    }

    /**
     * Test the setChanged method
     */
    public void testSetChanged() {
        updateReceived = false;
        // doesn't matter if already done
        theObservable.addObserver(theObserver);
        theObservable.testSetChanged("value");
        assertTrue(updateReceived);

    }

    /**
     * Test the untie method
     */
    public void testUntie() {
        theObservable.addObserver(new anObserver());
        theObservable.untie();
        assertTrue(theObservable.countObservers() == 0);
    }
}
