package sepher.saf.adapter;

import java.util.Iterator;
import java.util.HashSet;
import java.util.Set;

/*
 * @(#)src/classes/sov/java/util/Observable.java, hs122, hs122, 20001020 1.2.1.2
 * ===========================================================================
 * Licensed Materials - Property of IBM IBM Java(tm)2 SDK, Standard Edition, v
 * 1.2 (C) Copyright IBM Corp. 1999 All Rights Reserved. Copyright 1994-1998 by
 * Sun Microsystems, Inc.,
 * ===========================================================================
 */

/*
 * @(#)Observable.java 1.25 98/03/18
 */

/**
 * This class represents an observable object, or "data" in the model-view
 * paradigm. It can be subclassed to represent an object that the application
 * wants to have observed.
 * <p> An observable object can have one or more observers. An observer may be any
 * object that implements interface <tt>Observer</tt>. After an observable
 * instance changes, an application calling the
 * <code>Observable</code>'s <code>notifyObservers</code> method causes all of its
 * observers to be notified of the change by a call to their <code>update</code>
 * method.
 * <p> The order in which notifications will be delivered is unspecified. The
 * default implementation provided in the Observerable class will notify Observers
 * in the order in which they registered interest, but subclasses may change this
 * order, use no guaranteed order, deliver notifications on separate threaads, or
 * may guarantee that their subclass follows this order, as they choose.
 * <p> Note that this notification mechanism is has nothing to do with threads and
 * is completely separate from the <tt>wait</tt> and <tt>notify</tt> mechanism of
 * class <tt>Object</tt>.
 * <p> When an observable object is newly created, its set of observers is empty.
 * Two observers are considered the same if and only if the
 * <tt>equals</tt> method returns true for them.
 * @see     java.util.Observable#notifyObservers()
 * @see     java.util.Observable#notifyObservers(java.lang.Object)
 * @see     java.util.Observer
 * @see     java.util.Observer#update(java.util.Observable, java.lang.Object)
 * @since   JDK1.0
 * @author Chris Warth
 * @version 1.25, 03/18/98
 * @updated 21-jun-2005 13:37:34
 */

public class SAFObservable {
    private boolean changed = false;

    private Set<SAFObserver> obs;

    /**
	 * Construct an Observable with zero Observers
	 */

    public SAFObservable() {
        obs = new HashSet<SAFObserver>();
    }

    /**
	 * Adds an observer to the set of observers for this object, provided that it is
	 * not the same as some observer already in the set. The order in which
	 * notifications will be delivered to multiple observers is not specified. See the
	 * class comment.
	 * 
	 * @param o    an observer to be added.
	 */
    public void addObserver(SAFObserver o) {
        if (!obs.contains(o)) {
            obs.add(o);
        }
    }

    /**
	 * Indicates that this object has no longer changed, or that it has already
	 * notified all of its observers of its most recent change, so that the
	 * <tt>hasChanged</tt> method will now return <tt>false</tt>. This method is
	 * called automatically by the
	 * <code>notifyObservers</code> methods.
	 * @see     java.util.Observable#notifyObservers()
	 * @see     java.util.Observable#notifyObservers(java.lang.Object)
	 */
    protected void clearChanged() {
        changed = false;
    }

    /**
	 * Returns the number of observers of this <tt>Observable</tt> object.
	 * @return  the number of observers of this object.
	 */
    public int countObservers() {
        return obs.size();
    }

    /**
     * Deletes an observer from the set of observers of this object.
     * 
     * @param o
     *        the observer to be deleted.
     */
    public void deleteObserver(SAFObserver o) {
        obs.remove(o);
    }

    /**
     * Clears the observer list so that this object no longer has any observers.
     */
    public void deleteObservers() {
        obs.clear();
    }

    /**
	 * Tests if this object has changed.
	 * @return  <code>true</code> if and only if the <code>setChanged</code> method
	 * has been called more recently than the
	 * <code>clearChanged</code> method on this object;
	 * <code>false</code> otherwise.
	 * @see     java.util.Observable#clearChanged()
	 * @see     java.util.Observable#setChanged()
	 */
    public boolean hasChanged() {
        return changed;
    }

    /**
	 * If this object has changed, as indicated by the
	 * <code>hasChanged</code> method, then notify all of its observers and then call
	 * the <code>clearChanged</code> method to indicate that this object has no longer
	 * changed.
	 * <p> Each observer has its <code>update</code> method called with two arguments:
	 * this observable object and <code>null</code>. In other words, this method is
	 * equivalent to:
	 * <blockquote><pre> notifyOvservers(null)</pre></blockquote>
	 * @see     java.util.Observable#clearChanged()
	 * @see     java.util.Observable#hasChanged()
	 * @see     java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
    public void notifyObservers() {
        this.notifyObservers(null);
    }

    /**
	 * If this object has changed, as indicated by the
	 * <code>hasChanged</code> method, then notify all of its observers and then call
	 * the <code>clearChanged</code> method to indicate that this object has no longer
	 * changed.
	 * <p> Each observer has its <code>update</code> method called with two arguments:
	 * this observable object and the <code>arg</code> argument.
	 * @see     java.util.Observable#clearChanged()
	 * @see     java.util.Observable#hasChanged()
	 * @see     java.util.SAFObserver#update(java.util.Observable, java.lang.Object)
	 * 
	 * @param arg    any object.
	 */
    public void notifyObservers(Object arg) {

        if (!changed)
            return;

        changed = false;

        Iterator<SAFObserver> it = this.obs.iterator();
        while (it.hasNext()) {
            ((SAFObserver) it.next()).update(this, arg);
        }
    }

    /**
     * Marks this <tt>Observable</tt> object as having been changed; the
     * <tt>hasChanged</tt> method will now return <tt>true</tt>.
     */
    protected void setChanged() {
        changed = true;
    }
}
