package sepher.saf.adapter;

/**
 * Collaborating with an <code>AspectAdapter</code> and a
 * <code>DynamicValue</code> I hold a cached value which is flushed to the
 * domain layer by setting the <code>trigger</code> to <code>true</code>.
 * When asked for my value I return the cached value, unless the cache is empty
 * and I fetch the value from the domain by asking my
 * <code>AspectAdapter</code>.
 * 
 * @author Rob Vens
 * @version 1.0.1
 * @see AspectAdapter
 * @see DynamicValue
 */
public class BufferedDynamicValue extends DynamicValue {

    /**
     * The value I have when my value has not yet been flushed. Now we can
     * nicely use <code>equals()</code> to test whether a value has been
     * assigned.
     */
    private static final Object NOTYETASSIGNED = new Object();

    /**
     * Contains a <code>Boolean</code> value, which can be set to true (flush
     * the cached value) or false (discard the cached value).
     */
    private DynamicValue triggerChannel;

    /**
     * A <code>IValue</code>, usually an <code>AspectAdapter</code> to
     * connect the value interface to the real domain object of which I present
     * the value interface and keep the cached value of one of its aspects.
     * 
     * @see AspectAdapter
     */
    private IValue subject;

    static {

    }

    /**
     * Default constructor.
     */
    public BufferedDynamicValue() {
        this.initialize();
    }

    /**
     * @param newSubject
     *        the object containing the real value
     * @param newTriggerChannel
     *        the DynamicValue object containing the Boolean
     *        value that triggers updates
     */
    public BufferedDynamicValue(final IValue newSubject,
            final IValue newTriggerChannel) {
        this.setSubject(newSubject);
        this.setTriggerChannel((DynamicValue) newTriggerChannel);
    }

    /**
     * Add the argument as an <code>Observer</code> of the receiver. Creation
     * date: (17-5-2001 21:03:51)
     * 
     * @param obs
     *        the object that wants to register itself as an observer
     */
    public final void addObserver(final SAFObserver obs) {
        if (this.countObservers() == 0) {
            this.hookupToSubject();
        }
        super.addObserver(obs);
    }

    /**
     * The receiver is an observer of the trigger, and receives an update from
     * it. This is handled by the update method, and propagated to this method.
     * <p>
     * Trigger was set to TRUE: This may mean we need to flush the cached value
     * to the real subject. In that case we need to empty the cache as well.
     * <p>
     * Trigger was set to FALSE: This may mean we need to refill the cache with
     * the real value from the real subject. Creation date: (18-5-2001 11:21:22)
     */
    private void changedTrigger() {
        if (this.triggerChannel.getValue().equals(Boolean.TRUE)) {
            // Send the buffered value to the subject
            if (this.value.equals(NOTYETASSIGNED)) {
                return;
            }
            this.unhookFromSubject();
            this.getSubject().setValue(this.value);
            this.value = NOTYETASSIGNED;
            this.hookupToSubject();
        } else {
            // The trigger has been set to FALSE.
            // Reset the buffered value
            this.value = NOTYETASSIGNED;
            this.notifyObservers("value"); //should be accompanied by a reset
                                           // parameter?
        }
    }

    /**
     * Remove the argument as an observer of the receiver. Creation date:
     * (17-5-2001 21:06:38)
     * 
     * @param obs
     *        the object that wants to unregister itself as an observer
     */
    public final void deleteObserver(final SAFObserver obs) {
        super.deleteObserver(obs);
        if (this.countObservers() == 0) {
            this.unhookFromSubject();
        }
    }

    /**
     * Return the subject of the receiver. This is an object implementing the
     * value interface. Creation date: (17-5-2001 21:13:32)
     * 
     * @return sepher.saf.adapter.IValue
     */
    public final IValue getSubject() {
        return subject;
    }

    /**
     * Access to the trigger channel, which is usually shared between several
     * adapters. Creation date: (17-5-2001 21:14:08)
     * 
     * @return sepher.saf.adapter.DynamicValue
     */
    public final DynamicValue getTriggerChannel() {
        return triggerChannel;
    }

    /**
     * Return the cached value if there is one - otherwise fetch the value from
     * the subject. Note: when fetching from the subject the cached value is not
     * updated. This is only done with <code>setValue()</code>. Creation
     * date: (17-5-2001 21:17:31)
     * 
     * @return Object
     */
    public final Object getValue() {
        if (this.value.equals(NOTYETASSIGNED)) {
            return this.subject.getValue();
        } else {
            return this.value;
        }
    }

    /**
     * Register the receiver as an <code>Observer</code> of the subject. Do we
     * need to handle an illegal cast here? Creation date: (17-5-2001 20:49:50)
     * 
     * @throws ClassCastException
     */
    protected final void hookupToSubject()
	  throws ClassCastException {
        ((ChangingObservable) this.subject).addObserver(this);
    }

    /**
     * Always sent in the constructors.
     * This method is ***supposed*** to be overruled in subclasses that
     * should send super.initialize() in the overrules. That is why it
     * must not be declared as final.
     */
    protected void initialize() {
        //super.initialize();
        this.value = NOTYETASSIGNED;
    }

    /**
     * Set the subject we need to adapt. Creation date: (17-5-2001 20:56:04)
     * 
     * @param newSubject
     *        sepher.saf.adapter.IValue
     */
    public final void setSubject(final IValue newSubject) {
        if ((this.subject != null) && (this.countObservers() > 0)) {
            this.unhookFromSubject();
        }
        this.subject = newSubject;
        this.setValue(NOTYETASSIGNED);
        if ((this.subject != null) && (this.countObservers() > 0)) {
            this.hookupToSubject();
        }
    }

    /**
     * Hook up the receiver to a trigger, which is a <code>DynamicValue</code>
     * containing a <code>Boolean</code>. Creation date: (17-5-2001 21:14:08)
     * 
     * @param newTriggerChannel
     *        sepher.saf.adapter.DynamicValue
     */
    public final void setTriggerChannel(final DynamicValue newTriggerChannel) {
        if (this.triggerChannel != null) {
            this.triggerChannel.deleteObserver(this);
        }
        triggerChannel = newTriggerChannel;
        if (this.triggerChannel != null) {
            this.triggerChannel.addObserver(this);
        }
    }

    /**
     * Remove the receiver as an <code>Observer</code> of the subject.
     * Creation date: (17-5-2001 20:49:50)
     * 
     * @throws ClassCastException
     */
    protected final void unhookFromSubject()
	  throws ClassCastException {
        ((ChangingObservable) this.subject).deleteObserver(this);
    }

    /**
     * Remove the receiver from all objects where it registered as an observer.
     * This method is ***supposed*** to be overruled in subclasses that should
     * send super.untie() in the overrules. That is why it must not be declared
     * as final.
     * Creation date: (17-5-2001 20:44:48)
     */
    public void untie() {
        if (this.triggerChannel != null) {
            this.triggerChannel.deleteObserver(this);
        }
        if (this.countObservers() > 0) {
            this.unhookFromSubject();
        }
        super.untie();
    }

    /**
     * Handle updates from the objects I registered with as an observer.
     * Special case when this object is the triggerChannel. Otherwise simply
     * propagate the update to my observers.
     * @param anAspect Object
     * @param sender java.util.Observable
     */
    public final void update(final Object anAspect,
            final SAFObservable sender) {
        if (sender == this.triggerChannel) {
            this.changedTrigger();
        } else if (sender.equals(this.subject)) {
            if (this.value.equals(NOTYETASSIGNED)) {
                this.notifyObservers(anAspect);
            } else {
                // effectively does nothing
                super.update(sender, anAspect);
            }
        }
    }
}
