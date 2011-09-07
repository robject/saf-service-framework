package sepher.saf.adapter;

/*
 * @(#)src/classes/sov/java/util/Observer.java, hs122, hs122, 20001020 1.2.1.2
 * ===========================================================================
 * Licensed Materials - Property of IBM
 * IBM Java(tm)2 SDK, Standard Edition, v 1.2
 *
 * (C) Copyright IBM Corp. 1999 All Rights Reserved.
 * Copyright 1994-1998 by Sun Microsystems, Inc.,
 * ===========================================================================
 */
 
/*
 * @(#)Observer.java	1.14 98/06/29
 *
 */
/**
 * A class can implement the <code>Observer</code> interface when it
 * wants to be informed of changes in observable objects.
 *
 * @author  Chris Warth
 * @version 1.14, 06/29/98
 * @see     java.util.Observable
 * @since   JDK1.0
 */
public interface SAFObserver {
/**
 * This method is called whenever the observed object is changed. An
 * application calls an <tt>Observable</tt> object's
 * <code>notifyObservers</code> method to have all the object's
 * observers notified of the change.
 *
 * @param   o     the observable object.
 * @param   arg   an argument passed to the <code>notifyObservers</code>
 *                 method.
 */
void update(Object o, Object arg);
}
