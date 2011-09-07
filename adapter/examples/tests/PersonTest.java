/*
 * Created on 28-mei-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package sepher.saf.adapter.examples.tests;

import sepher.saf.adapter.examples.Person;
import junit.framework.TestCase;

/**
 * @author rob
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PersonTest extends TestCase {

    private Person person;

    /**
	 * @see TestCase#setUp()
	 * Exception
	 */
    protected void setUp() throws Exception {
        super.setUp();
        this.person = new Person();
    }

    /**
	 * @see TestCase#tearDown()
	 * Exception
	 */
    protected void tearDown() throws Exception {
        super.tearDown();
        this.person = null;
    }

    /**
     * 
     */
    public void testPerson() {
        //TODO Implement Person().
        assertEquals(person.getName(), "");
    }
    /**
     * 
     */
    public void testSetGetName(){
        this.person.setName("Janssen");
        assertEquals(person.getName(),"Janssen");
    }

}
