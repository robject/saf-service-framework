package sepher.saf.adapter;

/**
 * Interface class to provide clients with a simple value interface.
 * Provides the simple value interface that acts as a glue between adapter layers. 
 * Clients typically deal with a value interface object.
 * <p>
 * Also an <code>SAFObserver </code>so implementors of this class should implement
 * <code>update</code> as well.
 * 
 * @created    3 mei 2001
 * @author Rob Vens
 * @version    1.0
 */
public interface IValue extends SAFObserver {
  
  /**
   * Gets the <code>Object</code> attribute of the <code>IValue</code> object
   * 
   * @return                an object that is the value
   */
  public Object getValue();
  /**
   * Sets the <code>Object</code> attribute of the <code>IValue</code> object
   * 
   * @param arg         the new value to set
   */
  public void setValue(Object arg);
}
