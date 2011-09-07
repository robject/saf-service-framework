package sepher.saf.adapter;

/**
 * Implementing a <code>IValue</code> interface I am a container of objects,
 * called my value. As an <code>Observable</code> I also maintain a collection
 * of observers. The objects contained can be any <code>Object</code>,
 * including primitives in principle, although developers are expected to wrap
 * them in a real object.
 * 
 * @created 4 mei 2001
 * @author Rob Vens
 * @version 1.0
 * @updated 21-jun-2005 13:37:26
 */

public class DynamicValue extends ChangingObservable implements IValue {

    /**
     * The value <code>Object</code> that is contained.
     */
    protected Object value;

    /**
     * Default constructor for the DynamicValue object
     */
    public DynamicValue() {
        this.value = null;
    }

    /**
     * Constructor for the DynamicValue object, with an <code>Object</code> to
     * hold as the value.
     * 
     * @param subject
     *        Description of Parameter
     * @param value
     *        value
     */
    public DynamicValue(Object newValue) {
        this.value = newValue;
    }

    /**
     * Gets the Value attribute of the DynamicValue object
     * 
     * @return The Value value
     */
    public Object getValue() {
        return (this.value);
    }

    /**
     * Gets the Value attribute of the DynamicValue object
     * 
     * @return The Value value
     * @param aSubject
     */
    public Object getValueUsingSubject(IValue aSubject) {
        return (aSubject.getValue());
    }

    /**
     * Sets the value attribute of the DynamicValue object.
     * 
     * @param anObject
     *        the object which is held
     */
    public void setValue(Object anObject) {
        this.setValueSilently(anObject);
        this.setChanged("value");
    }

    /**
     * Set the value without updating observers. Creation date: (18-5-2001
     * 10:43:17)
     * 
     * @param value
     *        the Object which is to be contained
     */
    public synchronized void setValueSilently(Object newValue) {
        this.value = newValue;
    }

    /**
     * @author rob
     * @return String describing the receiver
     */
    public String toString() {

        StringBuffer sb = new StringBuffer("(");
        sb.append(super.toString());

        sb.append(" on: ");
        sb.append(this.value.toString());
        return sb.toString();
    }

    /**
     * Because I am a <code>IValue</code> I must implement an
     * <code>Observer</code> interface. Default response on receiving an
     * update is to do nothing.
     * 
     * @param sender
     *        the Observable firing the update
     * @param aspect
     *        the Object describing the kind of change
     */
    /**
     * Because I am a <code>IValue</code> I must implement an
     * <code>Observer</code> interface. Default response on receiving an
     * update is to do nothing.
     * 
     * @param sender
     *        the Observable firing the update
     * @param aspect
     *        the Object describing the kind of change
     */
    public void update(Object sender, Object aspect) {

    }
}