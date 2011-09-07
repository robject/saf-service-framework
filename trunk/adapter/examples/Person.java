package sepher.saf.adapter.examples;

import sepher.saf.adapter.ChangingObservable;

/**
 * @author Rob Vens
 * @version 1.0
 * @created 28-mei-2005 14:40:57
 */
public class Person extends ChangingObservable {

    /**
     * The <code>name</code> of the person.
     */
    private String name = "";

    public Address m_Address;

    public void finalize() throws Throwable {
        super.finalize();
    }

    public Person() {

    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param newName
     *        The name to set.
     */
    public void setName(String newName) {
        this.name = newName;
        this.setChanged("name");
    }

}