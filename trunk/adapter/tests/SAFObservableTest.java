/*
 * Created on 20-jun-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package sepher.saf.adapter.tests;

import sepher.saf.adapter.SAFObservable;
import junit.framework.TestCase;

/**
 * @author rob
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SAFObservableTest extends TestCase {
    SAFObservable testObservable;

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        testObservable = new SAFObservable();
    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
        testObservable = null;
    }

    public final void testSAFObservable() {
        //Test default constructor, which should result in
        //an empty set of observers.
        testObservable = new SAFObservable();
        this.assertTrue(testObservable.countObservers() == 0);
    }

    public final void testAddObserver() {
        //TODO Implement addObserver().
    }

    public final void testClearChanged() {
        //TODO Implement clearChanged().
    }

    public final void testCountObservers() {
        //TODO Implement countObservers().
    }

    public final void testDeleteObserver() {
        //TODO Implement deleteObserver().
    }

    public final void testDeleteObservers() {
        //TODO Implement deleteObservers().
    }

    public final void testHasChanged() {
        //TODO Implement hasChanged().
    }

    /*
     * Class under test for void notifyObservers()
     */
    public final void testNotifyObservers() {
        //TODO Implement notifyObservers().
    }

    /*
     * Class under test for void notifyObservers(Object)
     */
    public final void testNotifyObserversObject() {
        //TODO Implement notifyObservers().
    }

    public final void testSetChanged() {
        //TODO Implement setChanged().
    }

}
