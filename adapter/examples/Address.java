package sepher.saf.adapter.examples;
import sepher.saf.adapter.ChangingObservable;

/**
 * @author Rob Vens
 * @version 1.0
 * @created 28-mei-2005 14:40:57
 */
public class Address extends ChangingObservable {

	public Address(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

}