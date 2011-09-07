package sepher.saf.adapter;

import java.lang.reflect.*;

/**
 * Abstract class that implements functionality to connect to objects and adapt
 * to its interface. The <code>IValue</code> interface it implements is
 * redirected to another object (the subject). Also it lazily registers itself
 * as an <code>Observer</code> of the subject if two conditions are true:
 * <p>
 * 1. it currently has <code>Observer</code> s registered 2. the subject it
 * adapts sends updates
 * <p>
 * 
 * @author Rob Vens
 * @version 1.0.0
 * @todo We need to think about the difference between <code>Objects</code> or
 *       <code>ChangingObservable</code> s as subjects. It would be most
 *       convenient if we could switch between these two when necessary (see
 *       Coad: Java Design). The use of <code>ChangingObservable</code> is
 *       mandatory when we want to get updates.
 */
public abstract class InterfaceAdapter extends ChangingObservable implements
        IValue {

    /**
     * Boolean to set whether the object whose interface is adapted sends
     * updates i.e. whether I am registered as an Observer.
     */
    protected Boolean subjectSendsUpdates = new Boolean(false);

    /**
     * The object we are adapting.
     */
    protected ChangingObservable subject;

    /**
     * Upon reception of update, propagate the update to the subject. The
     * channel technique is to provide run-time switching of subjects. If you
     * don't want to use this, you should use <code>subject</code> directly.
     * 
     * @see #subject
     */
    protected DynamicValue subjectChannel;

    /**
     * Constructor for the InterfaceAdapter object. Subclasses are expected to
     * send super().
     */
    public InterfaceAdapter() {
        super();
        this.initialize();
    }

    /**
     * The subject has changed.
     */
    protected final void changedSubject() {
        this.setSubject((ChangingObservable) this.getSubjectChannel()
                .getValue());
    }

    /**
     * Does the subject send updates? Creation date: (13-11-2001 15:24:08)
     * 
     * @return boolean
     */
    public final Boolean doesSubjectSendUpdates() {
        return this.subjectSendsUpdates;
    }

    /**
     * Gets the Subject attribute of the InterfaceAdapter object. Use
     * <code>subjectChannel</code> if you want to get to the
     * <code>subject</code> when it is changeable.
     * 
     * @return a <code>boolean</code> indicating whether the receiver has
     *         registered as an <code>Observer</code> in order to get updates
     *         when the subject changes
     * @see #getSubjectChannel
     */
    public final ChangingObservable getSubject() {
        return this.subject;
    }

    /**
     * Gets the SubjectChannel attribute of the InterfaceAdapter object
     * 
     * @return the subject that is adapted
     */
    public final IValue getSubjectChannel() {
        return subjectChannel;
    }

    /**
     * Get the object that is the target for the receivers' change of value.
     * Creation date: (13-11-2001 14:55:34)
     * 
     * @return the <code>DynamicValue</code> that contains the object to adapt
     */
    public final Object getTarget() {
        return this.getTargetUsingSubject(this.subject);
    }

    /**
     * Stub method for nested access through an access path. This is a
     * collection of objects that need to be traversed as a chain to get the
     * real subject. This is not yet implemented. Creation date: (20-5-2001
     * 12:41:18)
     * 
     * @param subject
     *        the subject used to find the target object
     * @return the object that is the target
     */
    protected final Object getTargetUsingSubject(java.lang.Object subject) {
        return this.subject;
    }

    /**
     * Answer the value returned by invoking the retrieval or get method on the
     * receivers target. Creation date: (10-5-2001 16:30:33)
     * 
     * @return Object
     */
    public final Object getValue() {
        try {
            return this.getValueUsingTarget(subject);
        } catch (InvocationTargetException e) {
            return null;
        } catch (IllegalAccessException e) {
            return null;
        }
    }

    /**
     * Answer the value contained by the target. To get to the target use the
     * subject channel as currently defined. Creation date: (10-5-2001 16:33:42)
     * 
     * @param subject
     *        Object that is to be used to access the value
     * @return Object that is the value we want to get
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    protected final Object getValueUsingSubject(Object subject)
            throws IllegalAccessException, InvocationTargetException {
        return this.getValueUsingTarget(this.getTargetUsingSubject(subject));
    }

    /**
     * Abstract method defining the interface for subclasses to get the value.
     * Creation date: (10-5-2001 16:35:41)
     * 
     * @param subject
     *        Object to use for getting the value
     * @return Object that is the value we want to get
     * @throws java.lang.reflect.InvocationTargetException
     * @throws java.lang.IllegalAccessException
     */
    protected abstract Object getValueUsingTarget(Object subject)
            throws IllegalAccessException, InvocationTargetException;

    /**
     * Re-establish the relation of the receiver as an observer of the subject.
     */
    protected void hookupToSubject() {

        if (subjectSendsUpdates.booleanValue() && (subject != null)) {
            subject.addObserver(this);
        }
    }

    /**
     * Subclasses are expected to send <code>super.initialize()</code>.
     * Creation date: (10-5-2001 17:20:34)
     */
    protected void initialize() {
        subjectSendsUpdates = new Boolean(false);
    }

    /**
     * Do nothing for now because we do not yet implement access paths. Creation
     * date: (13-11-2001 14:58:12)
     * 
     * @param param
     *        java.lang.StringBuffer
     */
    public void printPathOn(StringBuffer param) {
    }

    /**
     * Sets the <code>subject</code> attribute of the
     * <code>InterfaceAdapter</code> object. Send an update since it is
     * probable that the value has changed as well. If this
     * <code>InterfaceAdapter</code> has a subject channel, delegate setting
     * the new subject to it so that others depending on the same subject
     * channel value model will be informed automatically. Note: for an
     * <code>InterfaceAdapter</code> which will change subjects frequenntly,
     * or a group of <code>InterfaceAdapter</code> s which should all share a
     * subject and change at the same time, <code>subjecChannel</code>
     * provides a convenient interface,
     * 
     * @param subject
     */
    protected void setSubject(ChangingObservable subject) {
        IValue sc = this.getSubjectChannel();
        if (sc != null) {
            sc.setValue(subject);
        } else {

            this.setSubjectPrivately(subject);
        }
    }

    /**
     * Set or change the IValue we observe to provide the lates subject. In the
     * rare cases where the subject channel needs to be reinitialized an update
     * message is sent on the assumption that the value has changed. Note: an
     * <code>InterfaceAdapter</code> which will not change subjects does not
     * need to use a subject channel. It is more efficient and convenient to set
     * the subject using <code>setSubject</code>. Creation date: (13-11-2001
     * 15:14:36)
     * 
     * @param aValueInterface
     *        sepher.saf.adapter.DynamicValue
     */
    public void setSubjectChannel(DynamicValue aValueInterface) {
        if (this.subjectChannel != null) {
            this.subjectChannel.deleteObserver(this);
        }
        this.subjectChannel = aValueInterface;
        if (this.subjectChannel != null) {
            this.subjectChannel.addObserver(this);
        }
        this.changedSubject();

    }

    /**
     * Sets the Subject attribute of the InterfaceAdapter object.
     * 
     * @param subject
     *        The object I am adapting.
     * @since 05-05-2001
     */
    private void setSubjectPrivately(ChangingObservable subject) {
        if ((this.subject != null) && (this.countObservers() >= 0)) {
            this.unhookFromSubject();
        }
        ;
        this.subject = subject;
        if ((this.subject != null) && (this.countObservers() >= 0)) {
            this.hookupToSubject();
        }
        ;
        this.setChanged("value");
    }

    /**
     * Sets the SubjectSendsUpdates attribute, that determines whether I am an
     * observer of my subject, and will respond to updates.
     * 
     * @param subjectSendsUpdates
     *        Boolean value indicating whether I am an Observer of my subject.
     */
    public void setSubjectSendsUpdates(Boolean subjectSendsUpdates) {
        if (this.subject != null && (this.countObservers() >= 0)) {
            this.unhookFromSubject();
        }
        ;
        this.subjectSendsUpdates = subjectSendsUpdates;
        if (this.subject != null && (this.countObservers() >= 0)) {
            this.hookupToSubject();
        }
        ;
    }

    /**
     * Set the value and notify observers. Creation date: (10-5-2001 16:06:24)
     * 
     * @param value
     *        java.lang.Object
     */
    public void setValue(Object value) {
        this.setValuePrivately(value);
        if (!this.subjectSendsUpdates.booleanValue()) {
            this.setChanged("value");
        }
    }

    /**
     * Set the value using the receivers target. Creation date: (10-5-2001
     * 16:12:41)
     * 
     * @param value
     *        java.lang.Object
     */
    protected void setValuePrivately(Object value) {
        this.setValueUsingTarget(this.subject, value);
    }

    /**
     * Abstract method. Creation date: (10-5-2001 16:15:28)
     * 
     * @param target
     *        java.lang.Object
     * @param value
     */
    protected abstract void setValueUsingTarget(Object target, Object value);

    /**
     * Remove any dependencies between myself and the subject.
     */
    protected void unhookFromSubject() {

        if (subjectSendsUpdates.booleanValue() && (subject != null)) {
            this.subject.deleteObserver(this);
        }
    }

    /**
     * Nothing special here but included for completeness. Creation date:
     * (17-5-2001 20:41:49)
     */
    public void untie() {
        this.untieParts();
        super.untie();
    }

    /**
     * Help method to remove ourselves as an observer of our subject. Creation
     * date: (13-11-2001 15:01:18)
     */
    private void untieParts() {
        if (this.subject != null && (this.countObservers() > 0)) {
            this.unhookFromSubject();
        }
        if (this.subjectChannel != null) {
            this.subjectChannel.deleteObserver(this);
        }

    }

    /**
     * This is received from the <code>Observable</code> object I registered
     * with as an <code>Observer</code>. Update results in propagating the
     * update to my observers, that is when the update originates from my
     * subject.
     * 
     * @param sender
     *        The sender of this update, of which I am an Observer.
     * @param arg
     *        Parameter sent with the update, for extra info.
     */
    public void update(Object sender, java.lang.Object arg) {
        if (sender == this.subjectChannel) {
            this.changedSubject();
        }
    }
}
