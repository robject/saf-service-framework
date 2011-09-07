package sepher.saf.adapter;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

/**
 * Connects any interface (usually in a more domain centered class) to the value
 * interface of an adapter.
 * <p>
 * Example:
 * 
 * <pre><code>
 * 
 *  
 *     void Person::setName(String name)
 *     String Person::getName()
 *     new AspectAdapter(aPerson, &quot;name&quot;)
 *   
 *  
 * </code></pre>
 * 
 * @created May 3, 2001
 * @author Rob Vens
 * @see java.lang.reflect.Method
 */
public class AspectAdapter extends InterfaceAdapter {
    /**
     * The <code>setMethod</code> variable stores the Method to execute (with
     * necessary parameters) to send a setter message to the subject.
     */
    private Method setMethod;

    /**
     * The <code>getMethod</code> variable stores the Method to execute
     * (usually without parameters) to send a getter message to the subject.
     */
    private Method getMethod;

    /**
     * The <code>aspect</code> is an optional member storing a String which is
     * the method signature excluding the set or get prefix of the getters and
     * setters.
     */
    private java.lang.String aspect;

    /**
     * Constructor for the AspectAdapter object. Use this constructor if you
     * want a default connection to a value interface of a ChangingObservable.
     * 
     * @param subject
     *        The subject to adapt
     * @throws NoSuchMethodException
     *         Thrown when the method we want to adapt does not exist in the
     *         subject
     */
    public AspectAdapter(final ChangingObservable subject)
            throws NoSuchMethodException {

        this(subject, "setValue", "getValue");
    }

    /**
     * Constructor for the AspectAdapter object with only a generic aspect name
     * specified. This will generate getter and setter methods by prefixing the
     * given <code>String</code> parameter.
     * 
     * @param subject
     *        The object we want to adapt
     * @param signature
     *        The generic aspect name
     * @throws NoSuchMethodException
     *         Thrown when the method we want to adapt does not exist
     */
    public AspectAdapter(final ChangingObservable subject,
            final String signature) throws NoSuchMethodException {
        this(subject, "set" + getAspectPostFix(signature), "get"
                + getAspectPostFix(signature));
        aspect = signature;
    }

    /**
     * Constructor for the AspectAdapter object where specific get and set
     * method names are specified. Other constructors fall through to this one.
     * 
     * @param subject
     *        The object we want to connect to
     * @param setSignature
     *        The name of the set message
     * @param getSignature
     *        The name of the get message
     * @throws java.lang.NoSuchMethodException
     * @exception NoSuchMethodException
     *            Thrown when the method we want to adapt does not exist
     */
    public AspectAdapter(final ChangingObservable subject,
            final String setSignature, final String getSignature)
            throws NoSuchMethodException {
        try {
            // get the class of the subject
            Class theClass = subject.getClass();
            // parameter array for get method is empty
            Class[] getterTypes = new Class[0];
            // now get the get method from the subject class
            this.getMethod = theClass.getMethod(getSignature, getterTypes);

            // parameter array for the set method is an array with one element
            // the type of which is fetched from the subject class
            Class[] setterTypes = { this.getMethod.getReturnType() };
            this.setMethod = theClass.getMethod(setSignature, setterTypes);
        } catch (NoSuchMethodException ex) {
            // to be handled by the villain that tried to create this adapter
            throw (ex);
        }

        this.setSubject((ChangingObservable) subject);
    }

    /**
     * The argument is a String, the aspect of an object. To prefix this with
     * the get and set we must try to convert the first character to uppercase.
     * Creation date: (10-5-2001 19:11:01)
     * 
     * @return java.lang.String
     * @param signature
     *        java.lang.String
     */
    private static String getAspectPostFix(final String signature) {
        StringBuffer aspectPostfix = new StringBuffer(signature);
        char firstCharacter = signature.toUpperCase().charAt(0);
        aspectPostfix.setCharAt(0, firstCharacter);
        return new String(aspectPostfix);
    }

    /**
     * Get the value from the domain object by invoking the
     * <code>getMethod</code> on it. Return value of null is valid.
     * 
     * @param anObject
     *        the target object to be used to get the value
     * @return java.lang.Object
     * @throws InvocationTargetException
     *         on problems invoking the method
     * @throws IllegalAccessException
     *         on problems when accessing the object to get the value from
     * @throws java.lang.reflect.InvocationTargetException
     * @throws java.lang.IllegalAccessException
     */
    protected final Object getValueUsingTarget(final Object anObject)
            throws IllegalAccessException, InvocationTargetException {
        if (subject != null) {
            return this.getMethod.invoke(anObject, null);
        } else {
            return null;
        }
    }

    /**
     * Set the value of anObject through invoking the set method with newValue
     * as the argument. Creation date: (10-5-2001 16:19:18)
     * 
     * @param anObject
     *        the object that must set the new value
     * @param newValue
     *        the new value to set
     */
    protected final void setValueUsingTarget(final Object anObject,
            final Object newValue) {
        try {
            this.setMethod.invoke(anObject, new Object[] { newValue });
        } catch (NullPointerException e) { // do nothing
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * Print the receiver. Creation date: (13-11-2001 14:44:51)
     * 
     * @return String
     */
    public final String toString() {

        StringBuffer sb = new StringBuffer("(");
        if (this.getTarget() != null) {
            sb.append(this.getTarget().toString());
            sb.append(" ");
        }
        // when using an aspect path which is currently not implemented
        this.printPathOn(sb);

        sb.append(this.aspect);
        sb.append(")");
        return sb.toString();
    }

    /**
     * Test for value strings here and only update observers when the argument
     * indicates that we get an update of the aspect I am interested in.
     * Creation date: (10-5-2001 17:26:01)
     * 
     * @param sender
     *        the object that wants to notify its observers
     * @param anAspect
     *        argument containing info on the kind of change
     */

    public final void update(final Object sender, final Object anAspect) {
        if ((sender == subject && anAspect.equals(this.aspect))) {
            // make sure the changed flag is set
            // otherwise the notification is not done
            this.setChanged();
            this.notifyObservers(anAspect);
        } else {
            // effectively no-op
            super.update(sender, anAspect);
        }
    }
}