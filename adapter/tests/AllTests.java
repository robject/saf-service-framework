package sepher.saf.adapter.tests;

import junit.framework.*;

public class AllTests extends TestSuite {

    public AllTests() {

    }

    /**
     * @return junit.framework.Test
     */
    public static Test suite() {

        TestSuite suite = new TestSuite("All JUnit Tests for package adapter");
        suite.addTest(AspectAdapterTest.suite());
        suite.addTest(BufferedDynamicValueTest.suite());
        suite.addTest(ChangingObservableTest.suite());
        suite.addTest(DynamicValueTest.suite());
        suite.addTest(InterfaceAdapterTest.suite());

        return suite;
    }

    /**
     * @param args
     */
    public static void main(final String[] args) {
        /*
         * Comment out the relevant UI you want to use.
         */
        junit.textui.TestRunner.run(suite());
        //junit.swingui.TestRunner.run (suite());
    }
}