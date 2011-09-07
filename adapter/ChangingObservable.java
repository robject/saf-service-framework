package sepher.saf.adapter;

/**
 * Extends the <code>SAFObservable</code> functionality by updating
 * <code>Observer</code>s when <code>setChanged</code> has been called. This class
 * is subclassed by domain classes containing business functionality.
 * <p> These classes are linked with other components by the event mechanism
 * implemented here. Observers that are contained in the collection of observers
 * are Adapters as a rule.
 * @created    7 mei 2001
 * @see                 java.util.Observable#setChanged
 * @see                 java.util.Observable#notifyObservers
 * @author Rob Vens
 * @version 1.0
 * @updated 21-jun-2005 13:37:24
 */
public class ChangingObservable extends SAFObservable {

    /**
     * Default constructor
     */
    public ChangingObservable() {

    }

    /**
	 * This method overruled the superclass <code>SAFObservable</code> method to
	 * include
	 * <code>notifyObservers</code> so that all observers are automatically notified.
	 * The argument can be used to include specific information about the change. This
	 * object is propagated to the observers and can be used by them to tune their
	 * response, for example to check whether it is usefull for them to respond at all,
	 * if they are only interested in specific kinds of changes.
	 * <p> This method is final because subclasses are not supposed to have any
	 * explicit knowledge about <code>Observer</code>s.
	 * <p> Usually a <code>String</code> containing the name of the changed attribute.
	 * The kind of Object is to be documented in the developers style guidelines,
	 * ranging from the simplest String to complex Change objects that contain state
	 * and history.
	 * @see             SAFObservable#notifyObservers
	 * @see             SAFObservable#setChanged
	 * 
	 * @param arg    an Object representing information about the changed value.
	 */
    protected synchronized final void setChanged(Object arg) {
        // only set the changed flag
        super.setChanged();
        this.notifyObservers(arg);
    }

    /**
     * Remove the registered observers. This should be overruled by subclasses
     * to do any cleanup required for the garbage collector to do its work.
     * <p>
     * Always call <code>super.untie()</code> when you overrule this method!
     * The rationale behind this method is to remove any explicit knowledge
     * about observers from subclasses. Creation date: (17-5-2001 20:23:53)
     */
    public void untie() {
        this.deleteObservers();
    }
}
